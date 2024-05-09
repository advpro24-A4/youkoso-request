package id.ac.ui.cs.advprog.youkoso.model.builder;

import id.ac.ui.cs.advprog.youkoso.model.Request;
public class RequestBuilder {
    private String requestId;
    private int requestQuantity;
    private double requestPrice;
    private String requestProduct;


    public RequestBuilder requestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public RequestBuilder requestQuantity(int requestQuantity) {
        this.requestQuantity = requestQuantity;
        return this;
    }

    public RequestBuilder requestPrice(double requestPrice) {
        this.requestPrice = requestPrice;
        return this;
    }

    public RequestBuilder requestProduct(String requestProduct) {
        this.requestProduct = requestProduct;
        return this;
    }

    public Request build() {
        Request request = new Request();
        request.setId(this.requestId);
        request.setQuantity(this.requestQuantity);
        request.setPrice(this.requestPrice);
        request.setProduct(this.requestProduct);
        return request;
    }
}
