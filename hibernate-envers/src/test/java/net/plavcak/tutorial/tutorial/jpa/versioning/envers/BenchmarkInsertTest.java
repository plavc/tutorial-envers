package net.plavcak.tutorial.tutorial.jpa.versioning.envers;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;
import net.plavcak.tutorial.tutorial.jpa.versioning.envers.repository.CustomerRepository;
import net.plavcak.tutorial.tutorial.jpa.versioning.envers.service.ShopService;
import net.plavcak.tutorial.tutorial.jpa.versioning.envers.util.CustomStopWatch;
import org.junit.jupiter.api.RepetitionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

@Slf4j
public abstract class BenchmarkInsertTest {

    static final int SIZE = 10000;

    static final StopWatch stopWatch = new CustomStopWatch();

    @Autowired
    protected ShopService shopService;

    @Autowired
    protected CustomerRepository customerRepository;

    protected static boolean warmUp;

    void warmUp() {
        if (!warmUp) {
            log.info("Warm up - inserting 1000 customers");
            stopWatch.start("WarmUp: Insert 1000 customers");
            for(int i = 0; i < 1000; i++) {
                shopService.customer();
            }
            stopWatch.stop();

            log.info("{}", stopWatch.prettyPrint());
            warmUp = true;
        }
        shopService.removeAll();
    }

    void createCustomers(RepetitionInfo testInfo) {
        log.info("Start performance test - insert {} customers", SIZE);
        int counter = 0;
        stopWatch.start("Benchmark: Inserting " + SIZE + " Customers "
            + testInfo.getCurrentRepetition() + "/" + testInfo.getTotalRepetitions());

        for(int i = 0; i < SIZE; i++) {
            shopService.customer();
            counter++;
        }
        stopWatch.stop();
        log.info("{}", stopWatch.prettyPrint());

        assertThat(counter).isEqualTo(SIZE);
    }

    void createCustomerOrder(RepetitionInfo testInfo) {
        log.info("Start performance test - insert {} customers", SIZE);
        int counter = 0;
        stopWatch.start("Benchmark: Inserting " + SIZE + " Customer Orders "
            + testInfo.getCurrentRepetition() + "/" + testInfo.getTotalRepetitions());

        for(int i = 0; i < SIZE; i++) {
            createCustomerOrder();
            counter++;
        }
        stopWatch.stop();
        log.info("{}", stopWatch.prettyPrint());

        assertThat(counter).isEqualTo(SIZE);
    }

    private void createCustomerOrder() {
        shopService.order(shopService.randomCustomer(), shopService.randomProduct(), shopService.randomProduct());
    }
}
