package ar.org.proyungas.usuarios.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import ar.org.proyungas.usuarios.models.entity.User;


/**
 * The Interface UsuarioDao.
 * using @RepositoryRestResource for automated CRUD
 * path="usuarios" parameter
 * states the endpoint in which CRUD will be exported
 */
@RepositoryRestResource(path="usuarios")
public interface UsuarioDao extends PagingAndSortingRepository<User, Integer>{
	
	/**
	 * Find by username.
	 *
	 * @param username the username
	 * @return the usuario
	 * implemented by a JPA in-built query
	 * endpoint for this will be for instance:
	 * /api/usuarios/usuarios/search/findByUsername?username=admin
	 */
	@RestResource(path="buscar-username")
	public User findByUsername(String username);
	
	/**
	 * Obtener por username.
	 *
	 * @param username the username
	 * @return the usuario
	 * customized query using hibernate SQL
	 * also customized endpoint using @RestResource
	 * instead of /api/usuarios/usuarios/search/obtenerPorUsername?username=admin
	 * use the overriden endpoint:
	 * /api/usuarios/usuarios/search/get-by-username?username=admin
	 */
//	@RestResource(path="get-by-username")
//	@Query("from Usuario where username=?1")
//	public User obtenerPorUsername(@Param("username") String username);

}
