CREATE TABLE projects (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    vacancies INTEGER NOT NULL,
    goal VARCHAR(255) NOT NULL
);