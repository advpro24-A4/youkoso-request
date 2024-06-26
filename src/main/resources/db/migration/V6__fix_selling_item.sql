DROP TABLE IF EXISTS selling_items;

CREATE TABLE IF NOT EXISTS selling_items
(
    id         UUID DEFAULT uuid_generate_v4() NOT NULL,
    request_id UUID,
    quantity   INTEGER,
    CONSTRAINT pk_selling_items PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS selling_items
    ADD CONSTRAINT FK_SELLING_ITEMS_ON_REQUEST FOREIGN KEY (request_id) REFERENCES requests (id);