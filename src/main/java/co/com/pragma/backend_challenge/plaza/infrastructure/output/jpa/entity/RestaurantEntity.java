package co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "restaurant")
public class RestaurantEntity {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "restaurant_id", nullable = false)
    private String id;

    @Column(name = "nit", nullable = false, unique = true)
    private String nit;

    @Column(name = "owner_id", nullable = false)
    private String ownerId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "logo_url", nullable = false)
    private String logoUrl;
}
