package net.plavcak.tutorial.tutorial.envers.repository;

import net.plavcak.tutorial.tutorial.envers.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
