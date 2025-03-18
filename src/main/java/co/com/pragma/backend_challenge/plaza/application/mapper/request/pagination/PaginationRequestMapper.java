package co.com.pragma.backend_challenge.plaza.application.mapper.request.pagination;

import co.com.pragma.backend_challenge.plaza.application.dto.request.pagination.PaginationRequest;
import co.com.pragma.backend_challenge.plaza.domain.util.annotation.Generated;
import co.com.pragma.backend_challenge.plaza.domain.util.pagination.PaginationData;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Generated
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaginationRequestMapper {
    PaginationData toDomain(PaginationRequest request);
}
