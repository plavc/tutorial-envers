package net.plavcak.tutorial.tutorial.jpa.versioning.triggers.repository;

import net.plavcak.tutorial.tutorial.jpa.versioning.triggers.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
