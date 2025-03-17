package co.com.pragma.backend_challenge.plaza.domain.model;

import co.com.pragma.backend_challenge.plaza.domain.util.enums.DishState;

public class Dish {
    private Long id;
    private Restaurant restaurant;
    private String name;
    private String description;
    private Long price;
    private DishCategory category;
    private String imageUrl;
    private DishState state;

    public Dish(DishBuilder builder) {
        this.id = builder.id;
        this.restaurant = builder.restaurant;
        this.name = builder.name;
        this.description = builder.description;
        this.price = builder.price;
        this.category = builder.category;
        this.imageUrl = builder.imageUrl;
        this.state = builder.state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public DishCategory getCategory() {
        return category;
    }

    public void setCategory(DishCategory category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public DishState getState() {
        return state;
    }

    public void setState(DishState state) {
        this.state = state;
    }

    public static DishBuilder builder(){
        return new DishBuilder();
    }

    public static class DishBuilder {
        private Long id;
        private Restaurant restaurant;
        private String name;
        private String description;
        private Long price;
        private DishCategory category;
        private String imageUrl;
        private DishState state;

        public DishBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public DishBuilder restaurant(Restaurant restaurantId) {
            this.restaurant = restaurantId;
            return this;
        }

        public DishBuilder name(String name) {
            this.name = name;
            return this;
        }

        public DishBuilder description(String description) {
            this.description = description;
            return this;
        }

        public DishBuilder price(Long price) {
            this.price = price;
            return this;
        }

        public DishBuilder category(DishCategory category) {
            this.category = category;
            return this;
        }

        public DishBuilder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public DishBuilder state(DishState state) {
            this.state = state;
            return this;
        }

        public Dish build(){
            return new Dish(this);
        }
    }
}
