CREATE TYPE NOTE_STATE as ENUM ('ARCHIVED', 'ACTIVE');
CREATE TABLE notes
(
    id BIGINT PRIMARY KEY,
    title VARCHAR(50),
    body VARCHAR(2000),
    status NOTE_STATE
);

CREATE TABLE categories
(
    id BIGINT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE notes_categories
(
    id_note BIGINT NOT NULL,
    id_category BIGINT NOT NULL,
    PRIMARY KEY (id_note, id_category),
    CONSTRAINT fk_notes FOREIGN KEY (id_note) REFERENCES notes (id),
    CONSTRAINT fk_categories FOREIGN KEY (id_category) REFERENCES categories (id)
);
