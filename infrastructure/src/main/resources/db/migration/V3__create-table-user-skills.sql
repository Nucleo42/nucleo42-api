CREATE TABLE user_skills (
    user_id UUID NOT NULL,
    skill_id INT NOT NULL,

    PRIMARY KEY (user_id, skill_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (skill_id) REFERENCES skills(id) ON DELETE CASCADE
);