package net.plavcak.tutorial.tutorial.jpa.versioning.envers.repository;

import net.plavcak.tutorial.tutorial.jpa.versioning.envers.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends
    JpaRepository<Customer, Long>, RevisionRepository<Customer, Long, Long> {

}
