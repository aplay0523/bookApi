package com.library.book_api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.library.book_api.controller.entity.Category;
import com.library.book_api.repository.CategoryRepository;
import com.library.book_api.repository.RelationRepository;
import com.library.book_api.service.CategoryService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class categoryTest {

    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private RelationRepository relationRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void 카테고리_저장() {
        Category category = new Category(1L, "문학");

        categoryService.saveCategory(category);

        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    void 카테고리_수정() {
        Category category = new Category(1L, "문학");

        String categoryName = "과학";

        when(categoryRepository.findByCategoryId(category.getCategoryId())).thenReturn(Optional.of(category));

        assertThat(category.getCategoryName()).isEqualTo(categoryName);

        verify(categoryRepository, times(1)).findByCategoryId(1L);
    }

    @Test
    void 카테고리_삭제() {
        Category category = new Category(1L, "문학");

        when(categoryRepository.findByCategoryId(category.getCategoryId())).thenReturn(Optional.of(category));
        when(relationRepository.existsByCategory(category)).thenReturn(false);

        categoryService.delCategory(category.getCategoryId());

        verify(categoryRepository, times(1)).findByCategoryId(1L);
        verify(relationRepository, times(1)).existsByCategory(category);
        verify(categoryRepository, times(1)).delete(category);
    }
}
