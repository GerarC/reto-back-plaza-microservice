package co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.adapter;

import co.com.pragma.backend_challenge.plaza.domain.model.Restaurant;
import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.RestaurantPersistencePort;
import co.com.pragma.backend_challenge.plaza.domain.util.pagination.DomainPage;
import co.com.pragma.backend_challenge.plaza.domain.util.pagination.PaginationData;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.entity.RestaurantEntity;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.mapper.PaginationJpaMapper;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.mapper.RestaurantEntityMapper;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.repository.RestaurantRepository;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.util.PaginationJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantJpaAdapter implements RestaurantPersistencePort {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;
    private final PaginationJpaMapper paginationJpaMapper;

    @Override
    public Restaurant findById(String id) {
        return  restaurantEntityMapper.toDomain(
                restaurantRepository.findById(id).orElse(null)
        );
    }

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {
        RestaurantEntity entity = restaurantEntityMapper.toEntity(restaurant);
        return  restaurantEntityMapper.toDomain(
                restaurantRepository.save(entity)
        );
    }

    @Override
    public Restaurant findByNit(String nit) {
        return  restaurantEntityMapper.toDomain(
                restaurantRepository.findByNit(nit).orElse(null)
        );
    }

    @Override
    public DomainPage<Restaurant> findAll(PaginationData paginationData) {
        PaginationJpa paginationJpa = paginationJpaMapper.toJpa(paginationData);
        Page<RestaurantEntity> page = restaurantRepository.findAll(paginationJpa.createPageable());
        return restaurantEntityMapper.toDomains(page);
    }
}
