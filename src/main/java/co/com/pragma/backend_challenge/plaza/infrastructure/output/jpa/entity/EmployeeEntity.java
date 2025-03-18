package co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class EmployeeEntity {
    @Id
    @Column(name = "employee_id", nullable = false)
    private String id;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id", nullable = false)
    private RestaurantEntity restaurant;
}
