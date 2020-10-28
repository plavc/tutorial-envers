package net.plavcak.tutorial.tutorial.envers;

import static org.assertj.core.api.Assertions.assertThat;

import com.arakelian.faker.service.RandomAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import net.plavcak.tutorial.tutorial.envers.model.Customer;
import net.plavcak.tutorial.tutorial.envers.model.CustomerOrder;
import net.plavcak.tutorial.tutorial.envers.model.Product;
import net.plavcak.tutorial.tutorial.envers.repository.CustomerOrderRevisionRepository;
import net.plavcak.tutorial.tutorial.envers.repository.CustomerRevisionRepository;
import net.plavcak.tutorial.tutorial.envers.repository.ProductRevisionRepository;
import net.plavcak.tutorial.tutorial.envers.service.ShopService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;

@Slf4j
@Profile("postgres")
@SpringBootTest
class ScenarioTest {

    @Autowired
    private ShopService shopService;

    @Autowired
    private CustomerRevisionRepository customerRevisionRepository;

    @Autowired
    private ProductRevisionRepository productRevisionRepository;

    @Autowired
    private CustomerOrderRevisionRepository customerOrderRevisionRepository;

    private final Map<Long, Product> products = new HashMap<>();
    private final List<Long> productIds = new ArrayList<>();
    private Customer customer;
    private CustomerOrder customerOrder;
    private String customerAddress;
    private String customerOriginalAddress;

    @BeforeEach
    void prepareData() {
        log.info("--- Create Products");
        addProduct(shopService.product("Keyboard", 95.00));
        addProduct(shopService.product("Mouse", 64.99));
        addProduct(shopService.product("USB-C Hub", 24.19));
        addProduct(shopService.product("Dell Latitude 4500", 918.00));

        log.info("--- Create Customer");
        customer = shopService.customer();

        log.info("--- Create Customer Order");
        customerOrder = shopService.order(customer);

        log.info("--- Add Product");
        customerOrder = shopService.addProduct(customerOrder, products.get(productIds.get(0)));

        log.info("--- Customer update address");
        customerOriginalAddress = customer.getAddress();
        customerAddress = RandomAddress.get().next().getStreet();
        customer = shopService.updateCustomer(customer.setAddress(customerAddress));

        log.info("--- Add Product");
        customerOrder = shopService.addProduct(customerOrder, products.get(productIds.get(1)));

        log.info("--- Update Product");
        Product keyboard = shopService.updateProduct(products.get(productIds.get(0)).setName("Keyboard 2"));
        addProduct(keyboard);
    }

    @Test
    void validateHorizontalDimension() {
        log.info("--- Get Customer Order revisions");
        Revisions<Long, CustomerOrder> revisions = customerOrderRevisionRepository
            .findRevisions(customerOrder.getId());

        assertThat(revisions).hasSize(3);

        // validate last revision
        Revision<Long, CustomerOrder> lastRevision = customerOrderRevisionRepository
            .findLastChangeRevision(customerOrder.getId())
            .orElse(null);
        assertThat(lastRevision).isNotNull();
        assertThat(lastRevision.getEntity().getCustomer().getAddress()).isEqualTo(customerAddress);

        // validate first revision
        Revision<Long, CustomerOrder> firstRevision = revisions.getContent().get(0);
        assertThat(firstRevision).isNotNull();
        assertThat(firstRevision.getEntity().getCustomer().getAddress()).isEqualTo(customerOriginalAddress);
    }

    void addProduct(Product product) {
        products.put(product.getId(), product);
        productIds.add(product.getId());
    }
}
