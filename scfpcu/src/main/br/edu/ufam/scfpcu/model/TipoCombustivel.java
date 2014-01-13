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
 * TipoCombustivel generated by hbm2java
 */
@Entity
@Table(name = "tipo_combustivel", catalog = "scfpcu")
public class TipoCombustivel implements java.io.Serializable {

	private Integer idCombustivel;
	private String tipoCombustivel;
	private Set<Veiculo> veiculos = new HashSet<Veiculo>(0);
	private Set<Veiculo> veiculos_1 = new HashSet<Veiculo>(0);
	private Set<Gastos> gastoses = new HashSet<Gastos>(0);
	private Set<Gastos> gastoses_1 = new HashSet<Gastos>(0);

	public TipoCombustivel() {
	}

	public TipoCombustivel(String tipoCombustivel) {
		this.tipoCombustivel = tipoCombustivel;
	}
	public TipoCombustivel(String tipoCombustivel, Set<Veiculo> veiculos,
			Set<Veiculo> veiculos_1, Set<Gastos> gastoses,
			Set<Gastos> gastoses_1) {
		this.tipoCombustivel = tipoCombustivel;
		this.veiculos = veiculos;
		this.veiculos_1 = veiculos_1;
		this.gastoses = gastoses;
		this.gastoses_1 = gastoses_1;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_combustivel", unique = true, nullable = false)
	public Integer getIdCombustivel() {
		return this.idCombustivel;
	}

	public void setIdCombustivel(Integer idCombustivel) {
		this.idCombustivel = idCombustivel;
	}

	@Column(name = "tipo_combustivel", nullable = false, length = 20)
	@NotNull
	@Length(max = 20)
	public String getTipoCombustivel() {
		return this.tipoCombustivel;
	}

	public void setTipoCombustivel(String tipoCombustivel) {
		this.tipoCombustivel = tipoCombustivel;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoCombustivel")
	public Set<Veiculo> getVeiculos() {
		return this.veiculos;
	}

	public void setVeiculos(Set<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoCombustivel")
	public Set<Veiculo> getVeiculos_1() {
		return this.veiculos_1;
	}

	public void setVeiculos_1(Set<Veiculo> veiculos_1) {
		this.veiculos_1 = veiculos_1;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoCombustivel")
	public Set<Gastos> getGastoses() {
		return this.gastoses;
	}

	public void setGastoses(Set<Gastos> gastoses) {
		this.gastoses = gastoses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoCombustivel")
	public Set<Gastos> getGastoses_1() {
		return this.gastoses_1;
	}

	public void setGastoses_1(Set<Gastos> gastoses_1) {
		this.gastoses_1 = gastoses_1;
	}

}