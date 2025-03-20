package co.com.pragma.backend_challenge.plaza.domain.spi.persistence;

import co.com.pragma.backend_challenge.plaza.domain.model.User;

public interface UserPersistencePort {
    boolean isOwner(String id);
    User getUser(String id);
}
