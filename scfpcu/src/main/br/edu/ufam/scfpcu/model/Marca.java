package br.edu.ufam.scfpcu.model;
// Generated Jan 9, 2014 11:42:56 PM by Hibernate Tools 3.2.4.GA

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

/**
 * Marca generated by hbm2java
 */
@Entity
@Table(name = "marca", catalog = "scfpcu")
public class Marca implements java.io.Serializable {

	private Integer idMarca;
	private String marca;
	private Set<Modelo> modelos = new HashSet<Modelo>(0);
	private Set<Modelo> modelos_1 = new HashSet<Modelo>(0);

	public Marca() {
	}

	public Marca(String marca) {
		this.marca = marca;
	}
	public Marca(String marca, Set<Modelo> modelos, Set<Modelo> modelos_1) {
		this.marca = marca;
		this.modelos = modelos;
		this.modelos_1 = modelos_1;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_marca", unique = true, nullable = false)
	public Integer getIdMarca() {
		return this.idMarca;
	}

	public void setIdMarca(Integer idMarca) {
		this.idMarca = idMarca;
	}

	@Column(name = "marca", nullable = false, length = 20)
	@NotNull
	@Length(max = 20)
	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "marca")
	public Set<Modelo> getModelos() {
		return this.modelos;
	}

	public void setModelos(Set<Modelo> modelos) {
		this.modelos = modelos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "marca")
	public Set<Modelo> getModelos_1() {
		return this.modelos_1;
	}

	public void setModelos_1(Set<Modelo> modelos_1) {
		this.modelos_1 = modelos_1;
	}

}