package id.ac.ui.cs.advprog.youkoso.model;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class RequestTest {
    Request request;

    @BeforeEach
    public void setUp() {
        this.request = new Request();
        this.request.setId("a5c376a3-4817-44da-b8cf-cdd117f5e731");
        this.request.setQuantity(5);
        this.request.setPrice(100.0);
        this.request.setProduct("Product Name");

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
        this.voucher = null;
    }
}
