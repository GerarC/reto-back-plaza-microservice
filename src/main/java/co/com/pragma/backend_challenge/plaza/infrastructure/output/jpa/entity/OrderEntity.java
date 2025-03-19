package co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.entity;

import co.com.pragma.backend_challenge.plaza.domain.util.enums.OrderState;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderDishEntity> dishes;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id", nullable = false)
    private RestaurantEntity restaurant;

    @Column(name = "state", nullable = false)
    private OrderState state;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private EmployeeEntity assignedEmployee;

    @Column(name = "security_pin", nullable = false)
    private String securityPin;
}

