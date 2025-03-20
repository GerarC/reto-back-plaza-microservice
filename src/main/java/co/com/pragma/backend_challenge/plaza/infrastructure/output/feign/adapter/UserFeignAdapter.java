package co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.adapter;

import co.com.pragma.backend_challenge.plaza.domain.model.User;
import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.UserPersistencePort;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.client.UserFeign;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.mapper.response.ShortUserResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFeignAdapter implements UserPersistencePort {
    private final UserFeign userFeign;
    private final ShortUserResponseMapper shortUserResponseMapper;

    @Override
    public boolean isOwner(String id) {
        return userFeign.isOwner(id).isOwner();
    }

    @Override
    public User getUser(String id) {
        return shortUserResponseMapper.toDomain(
                userFeign.getUserShortResponse(id)
        );
    }
}
