CREATE TABLE project_members (
    project_id UUID NOT NULL,
    member_id UUID NOT NULL,
    role_id INTEGER NOT NULL,

    PRIMARY KEY (project_id, member_id, role_id),
    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
    FOREIGN KEY (member_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);