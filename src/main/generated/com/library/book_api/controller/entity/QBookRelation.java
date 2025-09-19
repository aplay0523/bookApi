package com.library.book_api.controller.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBookRelation is a Querydsl query type for BookRelation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBookRelation extends EntityPathBase<BookRelation> {

    private static final long serialVersionUID = 1115335112L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBookRelation bookRelation = new QBookRelation("bookRelation");

    public final QBook book;

    public final QCategory category;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QBookRelation(String variable) {
        this(BookRelation.class, forVariable(variable), INITS);
    }

    public QBookRelation(Path<? extends BookRelation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBookRelation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBookRelation(PathMetadata metadata, PathInits inits) {
        this(BookRelation.class, metadata, inits);
    }

    public QBookRelation(Class<? extends BookRelation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.book = inits.isInitialized("book") ? new QBook(forProperty("book"), inits.get("book")) : null;
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category")) : null;
    }

}

