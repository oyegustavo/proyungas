package ar.org.proyungas.usuarios.application.update;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import ar.org.proyungas.usuarios.domain.models.User;
import ar.org.proyungas.usuarios.domain.output.RoleByIdFinderOutputPort;
import ar.org.proyungas.usuarios.domain.output.UserByIdFinderOutputPort;
import ar.org.proyungas.usuarios.domain.output.UserSaveOutputPort;
import ar.org.proyungas.usuarios.shared.infrastructure.output.restclient.ErrorCode;
import ar.org.proyungas.usuarios.shared.infrastructure.output.restclient.InvalidUserException;
import ar.org.proyungas.usuarios.shared.infrastructure.output.restclient.UserBadRequestException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class UserUpdateUseCase implements UserUpdater{
	
	private final UserUpdateMapper mapper;
	private final UserSaveOutputPort outputPort;
	private final UserByIdFinderOutputPort userByIdFinderOutputPort;
	private final RoleByIdFinderOutputPort roleByIdFinderOutputPort;

	@Override
	public void perform(UserUpdateCommand command) {
		
        log.info("Updating User with ID: {}", command.getId());
        
        validateRoles(command.getRoles());
        
        User existing = userByIdFinderOutputPort.peform(command.getId());

        if (!existing.getEnabled()) {
            log.info("The User {} is not active", command.getId());
            throw new InvalidUserException(ErrorCode.INVALID_USER_ERROR);
        }
        try {
            outputPort.perform(mapper.toDomain(command, existing));
        } catch (DataIntegrityViolationException e) {
            log.error("DataIntegrityViolationException - {}", e.getLocalizedMessage());
            throw new UserBadRequestException(ErrorCode.USER_BAD_REQUEST);
        }
    }
	
	private void validateRoles(List<RoleUpdateCommand> roles) {
		for (RoleUpdateCommand role : roles) {
			roleByIdFinderOutputPort.perform(role.getId());
		}
	}

}
