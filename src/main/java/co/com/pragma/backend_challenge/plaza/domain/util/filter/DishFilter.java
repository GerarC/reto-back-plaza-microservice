package co.com.pragma.backend_challenge.plaza.domain.util.filter;

import co.com.pragma.backend_challenge.plaza.domain.util.enums.DishState;

public class DishFilter {
    private String restaurantId;
    private String category;
    private DishState state;

    public DishFilter(DishFilterBuilder builder) {
        this.restaurantId = builder.restaurantId;
        this.category = builder.category;
        this.state = builder.state;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public DishState getState() {
        return state;
    }

    public void setState(DishState state) {
        this.state = state;
    }

    public static DishFilterBuilder builder(){
        return new DishFilterBuilder();
    }

    public static class DishFilterBuilder{
        private String restaurantId;
        private String category;
        private DishState state;

        public DishFilterBuilder restaurantId(String restaurantId) {
            this.restaurantId = restaurantId;
            return this;
        }

        public DishFilterBuilder category(String category) {
            this.category = category;
            return this;
        }

        public DishFilterBuilder state(DishState state) {
            this.state = state;
            return this;
        }

        public DishFilter build(){
            return new DishFilter(this);
        }
    }
}
