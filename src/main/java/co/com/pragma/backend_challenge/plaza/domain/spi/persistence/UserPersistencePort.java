package co.com.pragma.backend_challenge.plaza.domain.spi.persistence;

public interface UserPersistencePort {
    boolean isOwner(String id);
}
