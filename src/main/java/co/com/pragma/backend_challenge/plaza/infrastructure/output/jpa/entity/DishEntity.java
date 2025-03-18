package co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.entity;

import co.com.pragma.backend_challenge.plaza.domain.util.enums.DishState;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dish")
public class DishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dish_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private Long price;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "dish_category_id", referencedColumnName = "dish_category_id", nullable = false)
    private DishCategoryEntity category;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "dish_state", nullable = false)
    private DishState state;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id", nullable = false)
    private RestaurantEntity restaurant;

}
