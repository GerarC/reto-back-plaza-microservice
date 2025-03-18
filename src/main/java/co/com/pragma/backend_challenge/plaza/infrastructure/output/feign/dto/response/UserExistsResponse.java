package co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserExistsResponse {
    boolean exists;
}
