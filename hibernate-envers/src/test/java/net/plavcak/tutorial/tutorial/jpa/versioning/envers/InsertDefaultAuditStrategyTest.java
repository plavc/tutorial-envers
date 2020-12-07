package net.plavcak.tutorial.tutorial.jpa.versioning.envers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"postgres", "sql-log"})
@SpringBootTest(properties = "org.hibernate.envers.audit_strategy=org.hibernate.envers.strategy.DefaultAuditStrategy")
class InsertDefaultAuditStrategyTest extends InsertTest {

    @Test
    @Override
    void insertSingleEntity() {
        super.insertSingleEntity();
    }

    @Test
    @Override
    void insertEntityWithRelationships() {
        super.insertEntityWithRelationships();
    }
}
