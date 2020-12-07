package net.plavcak.tutorial.tutorial.jpa.versioning.envers.service;

import com.arakelian.faker.model.Person;
import com.arakelian.faker.service.RandomAddress;
import com.arakelian.faker.service.RandomPerson;
import java.math.BigDecimal;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.plavcak.tutorial.tutorial.jpa.versioning.envers.model.Customer;
import net.plavcak.tutorial.tutorial.jpa.versioning.envers.model.CustomerOrder;
import net.plavcak.tutorial.tutorial.jpa.versioning.envers.model.Product;
import net.plavcak.tutorial.tutorial.jpa.versioning.envers.repository.CustomerOrderRepository;
import net.plavcak.tutorial.tutorial.jpa.versioning.envers.repository.CustomerRepository;
import net.plavcak.tutorial.tutorial.jpa.versioning.envers.repository.ProductRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShopService {

    private final CustomerRepository customerRepository;
    private final CustomerOrderRepository customerOrderRepository;
    private final ProductRepository productRepository;

    public Customer customer() {
        return customerRepository.save(randomCustomer());
    }

    public Customer randomCustomer() {
        Person person = RandomPerson.get().next();
        return new Customer()
            .setFirstName(person.getFirstName())
            .setLastName(person.getLastName())
            .setAddress(RandomAddress.get().next().getStreet());
    }

    public Product randomProduct() {
        return new Product().setPrice(BigDecimal.valueOf(RandomUtils.nextDouble(12, 1000)))
            .setName(RandomStringUtils.randomAlphabetic(20));
    }

    public Customer updateCustomer(Customer customer) {
        log.info("* Update Customer");
        return customerRepository.saveAndFlush(customer);
    }

    public CustomerOrder order(Customer customer) {
        log.info("* Create Customer Order");
        return customerOrderRepository.save(new CustomerOrder().setCustomer(customer));
    }

    public CustomerOrder order(Customer customer, Product... products) {
        return customerOrderRepository.save(new CustomerOrder()
            .setCustomer(customer)
            .setProducts(Arrays.asList(products)));
    }

    public Product product(String name, double price) {
        log.info("* Create Product {} - EUR {}", name, price);
        return productRepository.save(new Product().setName(name).setPrice(BigDecimal.valueOf(price)));
    }

    public Product updateProduct(Product product) {
        log.info("* Update Product");
        return productRepository.save(product);
    }

    public CustomerOrder addProduct(CustomerOrder customerOrder, Product product) {
        log.info("* Add Product to Order");
        customerOrder.getProducts().add(product);
        return customerOrderRepository.save(customerOrder);
    }

    public void removeAll() {
        customerOrderRepository.deleteAllInBatch();
        productRepository.deleteAllInBatch();
        customerRepository.deleteAllInBatch();
    }
}
