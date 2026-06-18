package ar.org.proyungas.usuarios.infrastructure.adapter.output.persistence.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "usuarios")
public class UserEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name ="cuit_cuil", unique=true,length=20, nullable = false)
	private String username;
	
	@Column(name ="nombre_completo", length=200, nullable = false)
	private String fullname;
	
	@Column(name ="email", length=254, nullable = false)
	private String email;
	
	@Column(length=100)
	private String password;
	
	@Column(name ="estado")
	private Boolean enabled;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="users_roles",joinColumns=@JoinColumn(name="usuario_id"),
	inverseJoinColumns=@JoinColumn(name="rol_id"),
	uniqueConstraints = {@UniqueConstraint(columnNames= {"usuario_id","rol_id"})})
	private List<RoleEntity> roles;
}
