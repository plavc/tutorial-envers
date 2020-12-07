package net.plavcak.tutorial.tutorial.jpa.versioning.envers;

import static org.assertj.core.api.Assertions.assertThat;

import net.plavcak.tutorial.tutorial.jpa.versioning.envers.model.Product;
import net.plavcak.tutorial.tutorial.jpa.versioning.envers.service.ShopService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"postgres", "sql-log"})
@SpringBootTest(properties = "org.hibernate.envers.audit_strategy=org.hibernate.envers.strategy.ValidityAuditStrategy")
class InsertValidityAuditStrategyTest extends InsertTest {

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
