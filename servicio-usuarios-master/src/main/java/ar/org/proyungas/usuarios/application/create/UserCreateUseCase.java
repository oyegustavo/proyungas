package ar.org.proyungas.usuarios.application.create;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import ar.org.proyungas.usuarios.domain.output.UserSaveOutputPort;
import ar.org.proyungas.usuarios.shared.infrastructure.output.restclient.ErrorCode;
import ar.org.proyungas.usuarios.shared.infrastructure.output.restclient.UserBadRequestException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class UserCreateUseCase implements UserCreator{
	
	private final UserCreateMapper mapper;
	private final UserSaveOutputPort outputPort;

	@Override
	public UserCreateResult perform(UserCreateCommand command) {
		log.info("Starting performing CrimeTypeCreateUseCase with data: {}", command);

		try {
			return mapper.toResult(outputPort.perform(mapper.toDomain(command)));
		} catch (DataIntegrityViolationException e) {
			log.error("DataIntegrityViolationException - {}", e.getLocalizedMessage());
			throw new UserBadRequestException(ErrorCode.BAD_REQUEST_ERROR);
		}
	}
}
