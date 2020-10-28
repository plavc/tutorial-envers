package net.plavcak.tutorial.tutorial.envers.service;

import com.arakelian.faker.model.Person;
import com.arakelian.faker.service.RandomAddress;
import com.arakelian.faker.service.RandomPerson;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.plavcak.tutorial.tutorial.envers.model.Customer;
import net.plavcak.tutorial.tutorial.envers.model.CustomerOrder;
import net.plavcak.tutorial.tutorial.envers.model.Product;
import net.plavcak.tutorial.tutorial.envers.repository.CustomerOrderRepository;
import net.plavcak.tutorial.tutorial.envers.repository.CustomerRepository;
import net.plavcak.tutorial.tutorial.envers.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShopService {

    private final CustomerRepository customerRepository;
    private final CustomerOrderRepository customerOrderRepository;
    private final ProductRepository productRepository;

    public Customer customer() {
        log.info("* Create Customer");
        Person person = RandomPerson.get().next();
        return customerRepository.save(new Customer()
            .setFirstName(person.getFirstName())
            .setLastName(person.getLastName())
            .setAddress(RandomAddress.get().next().getStreet()));
    }

    public Customer updateCustomer(Customer customer) {
        log.info("* Update Customer");
        return customerRepository.saveAndFlush(customer);
    }

    public CustomerOrder order(Customer customer) {
        log.info("* Create Customer Order");
        return customerOrderRepository.save(new CustomerOrder().setCustomer(customer));
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
}
