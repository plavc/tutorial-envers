package net.plavcak.tutorial.tutorial.jpa.versioning.envers;

import static org.assertj.core.api.Assertions.assertThat;

import net.plavcak.tutorial.tutorial.jpa.versioning.envers.model.CustomerOrder;
import net.plavcak.tutorial.tutorial.jpa.versioning.envers.model.Product;
import net.plavcak.tutorial.tutorial.jpa.versioning.envers.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class InsertTest {

    @Autowired
    private ShopService shopService;

    void insertSingleEntity() {
        Product product = shopService.product("Keyboard", 95.00);
        assertThat(product).isNotNull();
    }

    void insertEntityWithRelationships() {
        CustomerOrder customerOrder = shopService.order(shopService.randomCustomer(),
            shopService.randomProduct(), shopService.randomProduct());
        assertThat(customerOrder).isNotNull();
    }
}
