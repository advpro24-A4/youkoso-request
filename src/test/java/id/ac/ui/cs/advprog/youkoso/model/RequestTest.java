package id.ac.ui.cs.advprog.youkoso.model;

import id.ac.ui.cs.advprog.youkoso.model.builder.RequestBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;


import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class RequestTest {
    Request request;

    @BeforeEach
    public void setUp() {
        this.request = new RequestBuilder()
                .requestId(UUID.fromString("a5c376a3-4817-44da-b8cf-cdd117f5e731"))
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

    @Test
    void testConvertPriceToIDR_USD() {
        this.request = new RequestBuilder()
                .requestPrice(10.0)
                .requestCurrency("USD")
                .build();
        double expectedPriceInIDR = 10.0 * 14000;
        assertEquals(expectedPriceInIDR, this.request.convertPriceToIDR());
    }

    @Test
    void testConvertPriceToIDR_JPY() {
        this.request = new RequestBuilder()
                .requestPrice(10.0)
                .requestCurrency("JPY")
                .build();
        double expectedPriceInIDR = 10.0 * 130;
        assertEquals(expectedPriceInIDR, this.request.convertPriceToIDR());
    }

    @Test
    void testConvertPriceToIDR_IDR() {
        this.request = new RequestBuilder()
                .requestPrice(10.0)
                .requestCurrency("IDR")
                .build();
        double expectedPriceInIDR = 10.0;
        assertEquals(expectedPriceInIDR, this.request.convertPriceToIDR());
    }

    @Test
    void testConvertPriceToIDR_OtherCurrency() {
        this.request = new RequestBuilder()
                .requestPrice(10.0)
                .requestCurrency("EUR") // Use a currency that is not "USD", "JPY", or "IDR"
                .build();
        double expectedPriceInIDR = 0.0;
        assertEquals(expectedPriceInIDR, this.request.convertPriceToIDR());
    }

    @AfterEach
    void tearDown() {
        this.request = null;
    }
}
