package net.plavcak.tutorial.tutorial.jpa.versioning.envers;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@ActiveProfiles({"postgres"})
@SpringBootTest(properties = "spring.jpa.properties.hibernate.integration.envers.enabled=true")
class BenchmarkInsertEnversEnabledTest extends BenchmarkInsertTest {

    @Override
    @BeforeEach
    void warmUp() {
        super.warmUp();
    }

    @Override
    @RepeatedTest(10)
    void createCustomers(RepetitionInfo testInfo) {
        super.createCustomers(testInfo);
    }

    @Override
    @RepeatedTest(10)
    void createCustomerOrder(RepetitionInfo testInfo) {
        super.createCustomerOrder(testInfo);
    }
}
