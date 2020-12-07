package net.plavcak.tutorial.tutorial.jpa.versioning.triggers.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Entity
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Customer extends AbstractModel {

    @Id
    @Column(columnDefinition = "serial")
    private Long id;

    private String firstName;

    private String lastName;

    private String address;

    @OneToMany
    @JoinTable(name = "customer_customer_order")
    private List<CustomerOrder> customerOrders;
}
