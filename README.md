# 상세정리
* [노션 정리](https://quilled-slime-0a4.notion.site/book-api-26f9271921f48012bf9be8387b9112a2?pvs=74)

## 1. 개발 환경
 * Version: Java 17
 * IDE: IntelliJ
 * Framework: Spring Boot 3.5.5
 * ORM: Spring Data JPA
 * Build: Gradle
 * 의존성 관리: Lombok
## 2. 기술 스택
 * Server: WSL2 Ubuntu, Docker
 * Database: H2
 * Backend: Spring Boot Web(RESTful API)
 * Security: Spring Security
 * Swagger 3.0: SpringDoc OpenAPI 2.7.0
 * Test: JUnit 5, Mockito, Postman


# Swagger API List
### 도서 등록
* url : /post /api/book
### 도서 상태변경
* url : /patch /api/book-status/{bookId} 
### 도서 카테고리 변경
* url : /patch /api/book-category/{bookId}
### 도서 검색
* url : /get /api/books
### 도서 삭제
* url : /delete /api/book/{bookId}
### 카테고리 수정
* url : /put /api/category/{categoryId}
### 카테고리 삭제
* url : /delete /api/category/{categoryId}
### 카테고리 검색
* url : /get /api/category
### 카테고리 등록
* url : /post /api/category
