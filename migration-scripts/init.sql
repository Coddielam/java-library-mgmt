-- CREATE TYPE book_status AS ENUM ('AVAILABLE', 'UNAVAILABLE', 'LOST');

CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    title TEXT,
    isbn TEXT,
    -- book_status book_status DEFAULT 'AVAILABLE',
    book_status TEXT DEFAULT 'AVAILABLE',
    author_name TEXT
);

CREATE TABLE patrons (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    address TEXT,
    city TEXT,
    country TEXT
);

CREATE TABLE borrowings (
    id SERIAL PRIMARY KEY,
    borrowed_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    due_date TIMESTAMP NOT NULL,
    return_date TIMESTAMP,
    book_id INT REFERENCES books(id) ON DELETE CASCADE,
    patron_id INT REFERENCES patrons(id) ON DELETE CASCADE
);

INSERT INTO patrons (name, address, city, country)
VALUES ('Andy', '1 Main Stl.', 'Boston', 'US');

INSERT INTO books (title, isbn, author_name)
VALUES ('Dear Diary', '978-3-16-148410-0', 'Andrew A.');

INSERT INTO books (title, isbn, author_name)
VALUES ('My Pokemon Collection', '978-3-16-148410-0', 'Bernard B.');