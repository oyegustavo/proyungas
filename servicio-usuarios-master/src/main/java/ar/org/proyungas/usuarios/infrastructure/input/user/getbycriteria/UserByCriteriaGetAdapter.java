package ar.org.proyungas.usuarios.infrastructure.input.user.getbycriteria;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.org.proyungas.usuarios.application.user.getbycriteria.UserByCriteriaFinder;
import ar.org.proyungas.usuarios.application.user.getbycriteria.UserByCriteriaFinderQuery;
import ar.org.proyungas.usuarios.shared.utils.PageResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.Explode;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserByCriteriaGetAdapter {

    private final UserByCriteriaFinder userByCriteriaFinder;
    private final UserByCriteriaGetMapper mapper;
    
    @Operation(summary = "List Users", tags = "users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Data sent ok"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content),
            @ApiResponse(responseCode = "503", description = "Service unavailable", content = @Content)
    })
    @GetMapping
    public ResponseEntity<PageResult<UserByCriteriaGetResponse>> perform(
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @Parameter(
                    explode = Explode.TRUE,
                    in = ParameterIn.QUERY,
                    name = "queryParams",
                    schema = @Schema(type = "object"),
                    examples = {
                            @ExampleObject(name = "fullname contains operation", value = "{\"fullname\":\"contains:Alf\"}"),
                            @ExampleObject(name = "username equals operation", value = "{\"username\":\"equals:12-34567876-9\"}"),
                            @ExampleObject(name = "enabled equals operation", value = "{\"enabled\":\"equals:true\"}")
                    },
                    description = "Enabled filters: descríption, category, enabled")
            @RequestParam Map<String, String> filters) {


        log.info("Calling GET /users with page {}, size {} and filters {}", page, size, filters);

        UserByCriteriaFinderQuery query = buildQuery(page, size, filters);

        return ResponseEntity.ok(mapper.toResponse(userByCriteriaFinder.perform(query)));
    }

    private UserByCriteriaFinderQuery buildQuery(Integer page, Integer size, Map<String, String> filters) {
        return UserByCriteriaFinderQuery.builder()
                .size(size)
                .page(page)
                .filters(filters)
                .build();
    }
}
