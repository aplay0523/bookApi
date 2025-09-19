package com.library.book_api.controller.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBookStatus is a Querydsl query type for BookStatus
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBookStatus extends EntityPathBase<BookStatus> {

    private static final long serialVersionUID = 1066795710L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBookStatus bookStatus = new QBookStatus("bookStatus");

    public final QBook book;

    public final StringPath reason = createString("reason");

    public final NumberPath<Long> statusId = createNumber("statusId", Long.class);

    public final StringPath statusName = createString("statusName");

    public QBookStatus(String variable) {
        this(BookStatus.class, forVariable(variable), INITS);
    }

    public QBookStatus(Path<? extends BookStatus> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBookStatus(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBookStatus(PathMetadata metadata, PathInits inits) {
        this(BookStatus.class, metadata, inits);
    }

    public QBookStatus(Class<? extends BookStatus> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.book = inits.isInitialized("book") ? new QBook(forProperty("book"), inits.get("book")) : null;
    }

}

