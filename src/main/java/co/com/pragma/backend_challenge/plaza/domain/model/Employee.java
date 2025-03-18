package co.com.pragma.backend_challenge.plaza.domain.model;

public class Employee {
    private String id;
    private Restaurant restaurant;

    public Employee(EmployeeBuilder builder) {
        this.id = builder.id;
        this.restaurant = builder.restaurant;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public static EmployeeBuilder builder(){
        return new EmployeeBuilder();
    }

    public static class EmployeeBuilder{
        private String id;
        private Restaurant restaurant;

        public EmployeeBuilder id(String id) {
            this.id = id;
            return this;
        }

        public EmployeeBuilder restaurant(Restaurant restaurant) {
            this.restaurant = restaurant;
            return this;
        }

        public Employee build(){
            return new Employee(this);
        }
    }
}
