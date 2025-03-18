package co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.mapper;


import co.com.pragma.backend_challenge.plaza.domain.util.annotation.Generated;
import co.com.pragma.backend_challenge.plaza.domain.util.pagination.PaginationData;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.util.PaginationJpa;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Generated
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaginationJpaMapper {
    PaginationJpa toJpa(PaginationData paginationData);
}
