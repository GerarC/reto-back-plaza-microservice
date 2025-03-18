package co.com.pragma.backend_challenge.plaza.domain.spi.persistence;

import co.com.pragma.backend_challenge.plaza.domain.model.Dish;
import co.com.pragma.backend_challenge.plaza.domain.util.filter.DishFilter;
import co.com.pragma.backend_challenge.plaza.domain.util.pagination.DomainPage;
import co.com.pragma.backend_challenge.plaza.domain.util.pagination.PaginationData;

public interface DishPersistencePort {
    Dish saveDish(Dish dish);
    Dish findById(Long id);
    DomainPage<Dish> findAll(DishFilter filter, PaginationData paginationData);
}
