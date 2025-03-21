package co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.entity;

import co.com.pragma.backend_challenge.plaza.domain.util.enums.OrderState;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_dish_id", nullable = false)
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private String customerId;

    @OneToMany(orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<OrderDishEntity> dishes;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id", nullable = false)
    private RestaurantEntity restaurant;

    @Column(name = "state", nullable = false)
    private OrderState state;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private EmployeeEntity assignedEmployee;

    @Column(name = "security_pin", nullable = false)
    private String securityPin;
}

