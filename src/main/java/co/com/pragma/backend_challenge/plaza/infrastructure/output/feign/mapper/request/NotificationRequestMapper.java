package co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.mapper.request;

import co.com.pragma.backend_challenge.plaza.domain.model.messaging.Notification;
import co.com.pragma.backend_challenge.plaza.domain.util.annotation.Generated;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.dto.request.NotificationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Generated
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NotificationRequestMapper {
    NotificationRequest toRequest(Notification response);
    List<NotificationRequest> toDomains(List<Notification> responses);
}
