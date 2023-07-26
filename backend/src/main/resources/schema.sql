
CREATE TABLE notes
(
    id BIGINT PRIMARY KEY,
    body VARCHAR(20000),
    archived BOOL
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
