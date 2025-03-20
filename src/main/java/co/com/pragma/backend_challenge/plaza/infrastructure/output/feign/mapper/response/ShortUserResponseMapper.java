package co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.mapper.response;

import co.com.pragma.backend_challenge.plaza.domain.model.User;
import co.com.pragma.backend_challenge.plaza.domain.util.annotation.Generated;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.dto.response.ShortUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Generated
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShortUserResponseMapper {

    User toDomain(ShortUserResponse response);
    List<User> toDomains(List<ShortUserResponse> responses);
}
