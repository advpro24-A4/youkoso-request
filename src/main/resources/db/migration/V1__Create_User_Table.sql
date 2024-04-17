CREATE TABLE "user"
(
    id       VARCHAR(255) NOT NULL,
    role     VARCHAR(31),
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE "user"
    ADD CONSTRAINT uc_user_email UNIQUE (email);