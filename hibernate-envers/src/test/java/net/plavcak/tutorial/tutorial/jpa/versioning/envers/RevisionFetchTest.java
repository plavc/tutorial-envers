package net.plavcak.tutorial.tutorial.jpa.versioning.envers;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;
import net.plavcak.tutorial.tutorial.jpa.versioning.envers.model.Customer;
import net.plavcak.tutorial.tutorial.jpa.versioning.envers.model.CustomerOrder;
import net.plavcak.tutorial.tutorial.jpa.versioning.envers.repository.CustomerOrderRepository;
import net.plavcak.tutorial.tutorial.jpa.versioning.envers.service.ShopService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.history.Revision;
import org.springframework.data.history.RevisionMetadata.RevisionType;
import org.springframework.data.history.Revisions;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@SpringBootTest
@ActiveProfiles({"postgres", "sql-log"})
class RevisionFetchTest {

    @Autowired
    private ShopService shopService;

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Test
    void createCustomerWithOrderAndCheckRevision() {
        Customer customer = shopService.customer();
        CustomerOrder customerOrder = shopService.order(customer);

        log.info("--- Find revisions for customer order {}", customerOrder);
        Revisions<Long, CustomerOrder> revisions = customerOrderRepository.findRevisions(customerOrder.getId());
        assertThat(revisions).hasSize(1);

        Revision<Long, CustomerOrder> revision = revisions.getLatestRevision();
        assertThat(revision.getMetadata().getRevisionType()).isEqualTo(RevisionType.INSERT);
    }
}
