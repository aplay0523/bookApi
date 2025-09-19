package com.library.book_api.controller.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter@Setter
@Table(name = "book", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "book_id")
    private Long bookId;

    @Column( name = "title")
    private String title;

    @Column( name = "author")
    private String author;

    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn( name = "status_id")
    private BookStatus bookStatus;

    // 도서 저장 또는 삭제 시 관계도 동일하게 진행
    @OneToMany( mappedBy = "book", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval=true)
    private List<BookRelation> bookRelation = new ArrayList<>();

    // 신규 BookRelation 만들어 추가
    public void addCategory(Category category) {
        BookRelation relation = new BookRelation(this, category);
        this.bookRelation.add(relation);
    }

    // category와 연결된 bookRelation 찾아서 삭제
    public void removeCategory(Category category) {
        this.bookRelation.removeIf(rel -> rel.getCategory().equals(category));
    }
}
