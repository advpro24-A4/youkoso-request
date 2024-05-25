package id.ac.ui.cs.advprog.youkoso.repository;

import id.ac.ui.cs.advprog.youkoso.model.Request;
import id.ac.ui.cs.advprog.youkoso.model.SellingItem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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


    @BeforeEach
    void setUp() {
        Request request = new Request();
        request.setQuantity(1);
        request.setPrice(1000);
        request.setProduct("Product");
        request.setCurrency("IDR");
        requestRepository.save(request);

        SellingItem sellingItem = new SellingItem();
        sellingItem.setQuantity(1);
        sellingItem.setRequest(request);
        sellingItemRepository.save(sellingItem);
    }

    @Test
    void testCreateAndFindSellingItem() {
        SellingItem sellingItem = new SellingItem();
        sellingItem.setQuantity(1);
        sellingItem.setRequest(requestRepository.findAll().get(0));
        sellingItemRepository.save(sellingItem);

        Optional<SellingItem> foundSellingItem = sellingItemRepository.findSellingItemById(sellingItem.getId());
        assertThat(foundSellingItem.isPresent()).isTrue();
        assertThat(foundSellingItem.get()).isEqualTo(sellingItem);
    }


}
