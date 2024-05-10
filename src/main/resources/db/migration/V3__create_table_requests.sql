CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE requests
(
    id UUID DEFAULT uuid_generate_v4() NOT NULL,
    quantity             INTEGER,
    price                DOUBLE PRECISION,
    product_request_name VARCHAR(255),
    CONSTRAINT pk_requests PRIMARY KEY (id)
);

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