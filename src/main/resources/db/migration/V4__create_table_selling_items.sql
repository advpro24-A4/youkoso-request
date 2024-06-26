CREATE TABLE selling_items
(
    id         INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    request_id UUID,
    quantity   INTEGER,
    CONSTRAINT pk_selling_items PRIMARY KEY (id)
);

ALTER TABLE requests
    ADD currency VARCHAR(255);

ALTER TABLE selling_items
    ADD CONSTRAINT FK_SELLING_ITEMS_ON_REQUEST FOREIGN KEY (request_id) REFERENCES requests (id);