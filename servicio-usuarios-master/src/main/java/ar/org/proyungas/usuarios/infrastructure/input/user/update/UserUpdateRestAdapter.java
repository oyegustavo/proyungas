package ar.org.proyungas.usuarios.infrastructure.input.user.update;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.org.proyungas.usuarios.application.update.UserUpdateCommand;
import ar.org.proyungas.usuarios.application.update.UserUpdater;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
@AllArgsConstructor
public class UserUpdateRestAdapter {
    private final UserUpdater userUpdater;

    private final UserUpdateRestMapper mapper;

    @Operation(summary = "User Update", tags = "users")
    @ApiResponses(value = { @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
            @ApiResponse(responseCode = "503", description = "Service unavailable", content = @Content) })

    @PutMapping
    public ResponseEntity<Void> perform(
                    @RequestBody @Valid UserUpdateRequest request
                    ) {
    	log.info("Calling PUT /users with request {}", request);
        UserUpdateCommand command = mapper.toCommand(request);
        userUpdater.perform(command);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
