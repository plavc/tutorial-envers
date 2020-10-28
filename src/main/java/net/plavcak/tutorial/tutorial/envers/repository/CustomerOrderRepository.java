package net.plavcak.tutorial.tutorial.envers.repository;

import net.plavcak.tutorial.tutorial.envers.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

}
