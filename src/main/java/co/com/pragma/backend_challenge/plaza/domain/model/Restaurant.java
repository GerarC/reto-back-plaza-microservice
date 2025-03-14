package co.com.pragma.backend_challenge.plaza.domain.model;

public class Restaurant {
    private String id;
    private String nit;
    private String ownerId;
    private String name;
    private String address;
    private String phone;
    private String logoUrl;

    public Restaurant(RestaurantBuilder builder) {
        this.id = builder.id;
        this.nit = builder.nit;
        this.ownerId = builder.ownerId;
        this.name = builder.name;
        this.address = builder.address;
        this.phone = builder.phone;
        this.logoUrl = builder.logoUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public static RestaurantBuilder build(){
        return new RestaurantBuilder();
    }

    public static class RestaurantBuilder {
        private String id;
        private String nit;
        private String ownerId;
        private String name;
        private String address;
        private String phone;
        private String logoUrl;

        public RestaurantBuilder id(String id) {
            this.id = id;
            return this;
        }

        public RestaurantBuilder nit(String nit) {
            this.nit = nit;
            return this;
        }

        public RestaurantBuilder ownerId(String ownerId) {
            this.ownerId = ownerId;
            return this;
        }

        public RestaurantBuilder address(String address) {
            this.address = address;
            return this;
        }

        public RestaurantBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public RestaurantBuilder name(String name) {
            this.name = name;
            return this;
        }

        public RestaurantBuilder logoUrl(String urlLogo) {
            this.logoUrl = urlLogo;
            return this;
        }

        public Restaurant build(){
            return new Restaurant(this);
        }
    }
}
