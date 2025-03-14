package co.com.pragma.backend_challenge.plaza.domain.spi;

import co.com.pragma.backend_challenge.plaza.domain.model.DishCategory;

public interface DishCategoryPersistecePort {
    DishCategory findByDescription(String description);
    DishCategory saveCategory(String description);
}
