package co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.mapper;

import co.com.pragma.backend_challenge.plaza.domain.model.order.Order;
import co.com.pragma.backend_challenge.plaza.domain.util.annotation.Generated;
import co.com.pragma.backend_challenge.plaza.domain.util.pagination.DomainPage;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;

@Generated
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderEntityMapper {
    OrderEntity toEntity(Order dish);
    List<OrderEntity> toEntities(List<Order> dishes);
    Order toDomain(OrderEntity entity);
    List<Order> toDomains(List<OrderEntity> dishEntities);


    @Mapping(target = "page", source = "number")
    @Mapping(target = "pageSize", source = "size")
    @Mapping(target = "totalPages", source = "totalPages")
    @Mapping(target = "count", source = "numberOfElements")
    @Mapping(target = "totalCount", source = "totalElements")
    @Mapping(target = "content", source = "content")
    DomainPage<Order> toDomains(Page<OrderEntity> dishEntities);
}
