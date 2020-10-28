package net.plavcak.tutorial.tutorial.envers;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;
import net.plavcak.tutorial.tutorial.envers.model.Customer;
import net.plavcak.tutorial.tutorial.envers.model.CustomerOrder;
import net.plavcak.tutorial.tutorial.envers.repository.CustomerOrderRevisionRepository;
import net.plavcak.tutorial.tutorial.envers.service.ShopService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.history.Revision;
import org.springframework.data.history.RevisionMetadata.RevisionType;
import org.springframework.data.history.Revisions;

@Slf4j
@SpringBootTest
class CustomerOrderTest {

    @Autowired
    private ShopService shopService;

    @Autowired
    private CustomerOrderRevisionRepository revisionRepository;

    @Test
    void createCustomerWithOrderAndCheckRevision() {
        Customer customer = shopService.customer();
        CustomerOrder customerOrder = shopService.order(customer);

        log.info("--- Find revisions for customer order {}", customerOrder);
        Revisions<Long, CustomerOrder> revisions = revisionRepository.findRevisions(customerOrder.getId());
        assertThat(revisions).hasSize(1);

        Revision<Long, CustomerOrder> revision = revisions.getLatestRevision();
        assertThat(revision.getMetadata().getRevisionType()).isEqualTo(RevisionType.INSERT);
    }
}
