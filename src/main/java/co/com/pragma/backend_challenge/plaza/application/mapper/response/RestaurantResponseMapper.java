package co.com.pragma.backend_challenge.plaza.application.mapper.response;

import co.com.pragma.backend_challenge.plaza.application.dto.request.RestaurantRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.response.PageResponse;
import co.com.pragma.backend_challenge.plaza.application.dto.response.RestaurantResponse;
import co.com.pragma.backend_challenge.plaza.domain.model.Restaurant;
import co.com.pragma.backend_challenge.plaza.domain.util.annotation.Generated;
import co.com.pragma.backend_challenge.plaza.domain.util.pagination.DomainPage;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Generated
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantResponseMapper {
    RestaurantResponse toResponse(Restaurant restaurant);
    List<RestaurantResponse> toResponses(List<Restaurant> restaurants);
    PageResponse<RestaurantResponse> toResponses(DomainPage<Restaurant> restaurantsPage);
}
