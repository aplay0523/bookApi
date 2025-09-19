package com.library.book_api.controller.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "status", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
public class BookStatus {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "status_id")
    private Long statusId;

    @Column( name = "status_name")
    private String statusName;

    @Column( name = "reason")
    private String reason;

    @OneToOne
    private Book book;

}
