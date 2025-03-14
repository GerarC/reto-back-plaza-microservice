package co.com.pragma.backend_challenge.plaza.domain.spi;

public interface UserPersistencePort {
    boolean isOwner(String id);
}
