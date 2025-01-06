CREATE TABLE authors (
    id SERIAL PRIMARY KEY,
    author_name TEXT
);

CREATE TYPE book_status AS ENUM ('AVAILABLE', 'UNAVAILABLE');

CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    title TEXT,
    isbn TEXT,
    book_status book_status DEFAULT 'AVAILABLE',
    authorId INT REFERENCES authors(id)
);

INSERT INTO authors (author_name)
VALUES ('Andy');

INSERT INTO books (title, isbn, authorId)
VALUES ('Dear Diary', '978-3-16-148410-0', 1);

INSERT INTO books (title, isbn, authorId)
VALUES ('My Pokemon Collection', '978-3-16-148410-0', 1);