-- CREATE TYPE book_status AS ENUM ('AVAILABLE', 'UNAVAILABLE', 'LOST');

CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    title TEXT,
    isbn TEXT,
    -- book_status book_status DEFAULT 'AVAILABLE',
    book_status TEXT DEFAULT 'AVAILABLE',
    author_name TEXT
);

INSERT INTO books (title, isbn, author_name)
VALUES ('Dear Diary', '978-3-16-148410-0', 'Andrew A.');

INSERT INTO books (title, isbn, author_name)
VALUES ('My Pokemon Collection', '978-3-16-148410-0', 'Bernard B.');