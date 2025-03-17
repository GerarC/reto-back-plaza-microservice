package co.com.pragma.backend_challenge.plaza.domain.model;

public class DishCategory {
    private Long id;
    private String description;

    public DishCategory(DishCategoryBuilder builder) {
        this.id = builder.id;
        this.description = builder.description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static DishCategoryBuilder builder(){
        return new DishCategoryBuilder();
    }

    public static class DishCategoryBuilder{
        private Long id;
        private String description;

        public DishCategoryBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public DishCategoryBuilder description(String description) {
            this.description = description;
            return this;
        }

        public DishCategory build(){
            return new DishCategory(this);
        }
    }
}
