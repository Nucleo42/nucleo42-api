CREATE TABLE project_technologies (
    project_id UUID NOT NULL,
    technology_id INTEGER NOT NULL,

    PRIMARY KEY (project_id, technology_id),
    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
    FOREIGN KEY (technology_id) REFERENCES technologies(id) ON DELETE CASCADE
);