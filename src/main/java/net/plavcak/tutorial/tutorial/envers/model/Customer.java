package net.plavcak.tutorial.tutorial.envers.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Data
@Entity
@Accessors(chain = true)
@Audited(withModifiedFlag = true)
@EqualsAndHashCode(callSuper = true)
public class Customer extends AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String firstName;

    private String lastName;

    private String address;

    @OneToMany
    @NotAudited
    @JoinTable(name = "customer_customer_order")
    private List<CustomerOrder> customerOrders;
}
