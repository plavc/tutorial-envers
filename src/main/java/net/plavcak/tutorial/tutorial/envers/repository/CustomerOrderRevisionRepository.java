package net.plavcak.tutorial.tutorial.envers.repository;

import net.plavcak.tutorial.tutorial.envers.model.CustomerOrder;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderRevisionRepository extends RevisionRepository<CustomerOrder, Long, Long> {

}
