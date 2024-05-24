package id.ac.ui.cs.advprog.youkoso.repository;

import id.ac.ui.cs.advprog.youkoso.model.builder.RequestBuilder;
import id.ac.ui.cs.advprog.youkoso.model.Request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import java.util.Iterator;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RequestRepositoryTest {

    @Autowired
    private RequestRepository requestRepository;

    Request request;

    @BeforeEach
    void setUp() {
        this.request = new RequestBuilder()
                .requestId(null)
                .requestQuantity(1)
                .requestPrice(1000)
                .requestProduct("Product")
                .requestCurrency("IDR")
                .build();

    }

    @Test
    void testCreateAndFindRequest() {
        requestRepository.save(request);
        Optional<Request> foundRequest = requestRepository.findRequestById(request.getId());
        assertTrue(foundRequest.isPresent());
        assertEquals(request, foundRequest.get());
    }


}
