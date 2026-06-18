package ar.org.proyungas.usuarios.infrastructure.input.user.create;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import ar.org.proyungas.usuarios.application.create.UserCreator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;

@RestController
@RequestMapping("/usuarios/create")
@Slf4j
@AllArgsConstructor
public class UserCreateRestAdapter {

	private final UserCreator userCreate;

	private final UserCreateRestMapper mapper;

	@Operation(summary = "User Create", tags = "usuarios/create")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Created"),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
			@ApiResponse(responseCode = "503", description = "Service unavailable", content = @Content) })

	@PostMapping
	public ResponseEntity<UserCreateResponse> perform(@RequestBody @Valid UserCreateRequest request) {
		log.info("Starting executing service POST /v2/parameters/private/crime-types - REQUEST: {}", request);
		return new ResponseEntity<>(mapper.toResponse(userCreate.perform(mapper.toCommand(request))),
				HttpStatus.CREATED);
	}
}
