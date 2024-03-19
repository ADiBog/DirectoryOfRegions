CREATE TABLE IF NOT EXISTS regions
(
    id
    BIGINT
    AUTO_INCREMENT
    PRIMARY
    KEY,
    name
    VARCHAR
(
    255
) NOT NULL,
    short_name VARCHAR
(
    100
) NOT NULL,
    UNIQUE
(
    name
),
    UNIQUE
(
    short_name
)
    );