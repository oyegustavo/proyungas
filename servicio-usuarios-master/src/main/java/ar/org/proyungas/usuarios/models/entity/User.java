package ar.org.proyungas.usuarios.models.entity;

import java.io.Serializable;
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

import lombok.Data;

@Entity
@Table(name="usuarios")
@Data
public class User implements Serializable{

	private static final long serialVersionUID = 4002221912401133094L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name ="cuit_cuil", unique=true,length=20)
	private String username;
	
	@Column(length=100)
	private String password;
	
	@Column(name ="estado")
	private Boolean enabled;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="users_roles",joinColumns=@JoinColumn(name="usuario_id"),
	inverseJoinColumns=@JoinColumn(name="rol_id"),
	uniqueConstraints = {@UniqueConstraint(columnNames= {"usuario_id","rol_id"})})
	private List<Role> roles;
	
}
