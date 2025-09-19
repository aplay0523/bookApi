package com.library.book_api.service;

import com.library.book_api.common.ErrorCode;
import com.library.book_api.common.exception.CustomException;
import com.library.book_api.controller.entity.Category;
import com.library.book_api.controller.entity.QCategory;
import com.library.book_api.controller.vo.response.CategoryResponseDto;
import com.library.book_api.repository.CategoryRepository;
import com.library.book_api.repository.RelationRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final RelationRepository relationRepository;
    private final JPAQueryFactory jpaQueryFactory;

    private final QCategory qCategory = QCategory.category;

    // 카테고리 검색
    public List<CategoryResponseDto> searchCategory(String categoryName) {
        return jpaQueryFactory
                .select(Projections.constructor(
                        CategoryResponseDto.class,
                        qCategory.categoryId,
                        qCategory.categoryName
                ))
                .from(qCategory)
                .where(categoryName != null ? qCategory.categoryName.containsIgnoreCase(categoryName) : null)
                .fetch();
    }

    // 카테고리 저장
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    // 카테고리 수정
    public void updateCategory(Long categoryId, String categoryName) {
        Category category = categoryRepository.findByCategoryId(categoryId)
                .orElseThrow(() -> new CustomException(ErrorCode.BAD_REQUEST, "등록된 정보가 없습니다."));
        category.setCategoryName(categoryName);
    }

    // 카테고리 삭제
    public void delCategory(Long categoryId) {

        Category category = categoryRepository.findByCategoryId(categoryId)
                        .orElseThrow(() -> new CustomException(ErrorCode.BAD_REQUEST, "등록된 정보가 없습니다."));

        boolean b = relationRepository.existsByCategory(category);

        if(b) {
            throw new RuntimeException("이미 사용중인 카테고리 입니다.");
        } else {
            categoryRepository.delete(category);
        }
    }
}
