package id.ac.ui.cs.advprog.youkoso.repository;

import id.ac.ui.cs.advprog.youkoso.model.Request;
import id.ac.ui.cs.advprog.youkoso.model.SellingItem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.UUID;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class SellingItemRepositoryTest {
    @Autowired
    private SellingItemRepository sellingItemRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Test
    void testSaveSellingItem() {
        UUID requestId = UUID.fromString("a5c376a3-4817-44da-b8cf-cdd117f5e731");

        Optional<Request> request = requestRepository.findRequestById(requestId);
    }

    @AfterEach
    void tearDown() {
        sellingItemRepository.deleteAll();
        requestRepository.deleteAll();
    }

}
