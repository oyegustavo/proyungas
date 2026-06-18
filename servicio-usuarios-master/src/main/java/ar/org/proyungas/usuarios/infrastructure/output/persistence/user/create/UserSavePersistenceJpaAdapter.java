package ar.org.proyungas.usuarios.infrastructure.output.persistence.user.create;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Component;

import ar.org.proyungas.usuarios.domain.models.User;
import ar.org.proyungas.usuarios.domain.output.UserSaveOutputPort;
import ar.org.proyungas.usuarios.infrastructure.adapter.output.persistence.entity.UserEntity;
import ar.org.proyungas.usuarios.infrastructure.output.persistence.repository.UserRepository;
import ar.org.proyungas.usuarios.shared.infrastructure.output.restclient.DatabaseConnectionException;
import ar.org.proyungas.usuarios.shared.infrastructure.output.restclient.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@AllArgsConstructor
public class UserSavePersistenceJpaAdapter implements UserSaveOutputPort{

	private final UserPersistenceMapper userPersistenceMapper;
	private final UserRepository userRepository;
	
	@Override
	public User perform(User user) {
		log.info("Starting perform UserSavePersistenceJpaAdapter with data: {}", user);

		try {
			UserEntity entity = userPersistenceMapper.toEntity(user);

			return userPersistenceMapper.toDomain(userRepository.save(entity));

		} catch (DataAccessResourceFailureException e) {
			log.error("Database connection error while performing CrimeTypeSavePersistenceAdapter with data: {}", user,
					e);

			throw new DatabaseConnectionException(ErrorCode.DATABASE_ERROR);
		}
	}

}
