package id.ac.ui.cs.advprog.youkoso.model;

import id.ac.ui.cs.advprog.youkoso.model.builder.RequestBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;



public class RequestTest {
    Request request;

    @BeforeEach
    public void setUp() {
        this.request = new RequestBuilder()
                .requestId("a5c376a3-4817-44da-b8cf-cdd117f5e731")
                .requestQuantity(5)
                .requestPrice(100.0)
                .requestProduct("Product Name")
                .build();

    }

    @Test
    public void testQuantity() {
        assertEquals(5, this.request.getQuantity());
    }

    @Test
    public void testPrice() {
        assertEquals(100.0, this.request.getPrice(), 0.01);
    }

    @Test
    public void testProduct() {
        assertEquals("Product Name", this.request.getProduct());
    }

    @AfterEach
    void tearDown() {
        this.request = null;
    }
}
