CREATE TABLE task (
    id BIGINT NOT NULL AUTO_INCREMENT,
    task_name VARCHAR(255),
    scheduled_time DATETIME,
    status VARCHAR(255) DEFAULT "PENDING",
    PRIMARY KEY (id)
)