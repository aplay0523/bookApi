package com.library.book_api;

import static org.assertj.core.api.Assertions.assertThat;

import com.library.book_api.controller.entity.Book;
import com.library.book_api.controller.entity.BookRelation;
import com.library.book_api.controller.entity.Category;
import com.library.book_api.controller.entity.QBook;
import com.library.book_api.controller.entity.QBookRelation;
import com.library.book_api.controller.entity.QBookStatus;
import com.library.book_api.controller.entity.QCategory;
import com.library.book_api.repository.BookRepository;
import com.library.book_api.repository.CategoryRepository;
import com.library.book_api.repository.RelationRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class dslTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RelationRepository relationRepository;
    @Autowired
    private EntityManager entityManager;

    private JPAQueryFactory jpaQueryFactory;
    private final QBook qBook = QBook.book;
    private final QBookRelation qBookRelation = QBookRelation.bookRelation;
    private final QCategory qCategory = QCategory.category;
    private final QBookStatus qBookStatus = QBookStatus.bookStatus;

    @BeforeEach
    void setUp() {
        jpaQueryFactory = new JPAQueryFactory(entityManager);
        BookRelation bookRelation = new BookRelation();
        List<BookRelation> bookRelationList = new ArrayList<>();

        // 데이터 세팅
        List<Category> categories = List.of(
                new Category(null, "문학"),
                new Category(null, "과학"),
                new Category(null, "소설")
        );
        categoryRepository.saveAll(categories);

        List<Book> books = List.of(
                new Book(null, "문학은 문학이다", "문학인", null, bookRelationList),
                new Book(null, "과학은 과학이다", "과학인", null, bookRelationList)
        );
        bookRepository.saveAll(books);

        List<BookRelation> relations = List.of(
                new BookRelation(null, books.get(0), categories.get(0))
        );
        relationRepository.saveAll(relations);

        entityManager.flush();
        entityManager.clear();
    }

    @Test
    void 도서_조회() {
        List<Book> books = jpaQueryFactory.selectFrom(qBook)
                .leftJoin(qBook.bookRelation, qBookRelation).fetchJoin()
                .leftJoin(qBookRelation.category, qCategory).fetchJoin()
                .leftJoin(qBook.bookStatus, qBookStatus).fetchJoin()
                .where(qBook.title.containsIgnoreCase("문학"))
//                .where(qBook.author.containsIgnoreCase("문학인"))
//                .where(qCategory.categoryName.eq("문학"))
                .distinct()
                .fetch();

        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("문학은 문학이다");
    }

    @Test
    void 카테고리_조회() {
        List<Category> categories = jpaQueryFactory
                .select(qCategory)
                .from(qCategory)
                .where(qCategory.categoryName.containsIgnoreCase("문학"))
                .fetch();

        assertThat(categories).hasSize(1);
        assertThat(categories.get(0).getCategoryName()).isEqualTo("문학");
    }
}
