package co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.adapter;

import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.UserPersistencePort;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.client.UserFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFeignAdapter implements UserPersistencePort {
    private final UserFeign userFeign;
    @Override
    public boolean isOwner(String id) {
        return userFeign.isOwner(id).isOwner();
    }
}
