package es.enxenio.ilga.pdmapping.modelo.entidades.informantes;

import es.enxenio.ilga.pdmapping.modelo.entidades.lugares.Lugar;
import es.enxenio.ilga.pdmapping.modelo.entidades.municipios.Municipio;
import es.enxenio.ilga.pdmapping.modelo.entidades.xuizos.Xuizo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Informante {
	@Id
	@SequenceGenerator(name = "InformanteIdGenerator", sequenceName = "id_informante")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "InformanteIdGenerator")
	@Column(name = "id_informante")
	private Long id;

	@Column(name = "ano_nacemento")
	private Integer anoNacemento;

	@Enumerated(EnumType.STRING)
	private Sexo sexo;

	@Enumerated(EnumType.STRING)
	private Estudo estudos;

	@Enumerated(EnumType.STRING)
	private Ocupacion ocupacion;

	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "lugar_nacemento")
	private Lugar lugarNacemento;

	@Column(name = "outro_nacemento")
	private String outroLugarNacemento;

	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "lugar_residencia")
	private Lugar lugarResidencia;

	@Column(name = "outro_residencia")
	private String outroLugarResidencia;

	@Column(name = "nacemento_pais_coincide")
	private Boolean lugarNacementoPaisCoincide;

	@OneToMany(mappedBy = "informante")
	private List<Xuizo> xuizos = new ArrayList<Xuizo>();

	@Column(name = "viviu_fora_galicia")
	private Boolean viviuForaDeGalicia;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "informante_outras_residencias",
			joinColumns = { @JoinColumn(name = "informante", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "municipio", nullable = false, updatable = false) })
	private List<Municipio> outrosLugaresResidencia = new ArrayList<Municipio>();

	@Column(name = "lingua_nativa")
	@Enumerated(EnumType.STRING)
	private Lingua linguaNativa;

	@Column(name = "lingua_falada")
	@Enumerated(EnumType.STRING)
	private Lingua linguaFalada;

	@Column(name = "lugares_desprazamento")
	private String lugaresDesprazamento;

	@Column(name = "grao_diferenza_fala")
	@Enumerated(EnumType.STRING)
	private Grao graoDiferenzaFala;

	@Column(name = "mellor_galego")
	private String mellorGalego;

	@Column(name = "peor_galego")
	private String peorGalego;

	@Column(name = "diferente_galego")
	private String diferenteGalego;

	public Informante() {
		super();
	}

	public Informante(Long id, Integer anoNacemento, Sexo sexo, Estudo estudos, Ocupacion ocupacion,
					  String outroEnquisa, String outroNacemento,
					  Boolean lugarNacementoPaisCoincide, Boolean viviuForaDeGalicia,
					  Lingua linguaNativa, Lingua linguaFalada, String lugaresDesprazamento,
					  Grao graoDiferenzaFala, String mellorGalego, String peorGalego, String diferenteGalego) {
		this();
		this.id = id;
		this.anoNacemento = anoNacemento;
		this.sexo = sexo;
		this.estudos = estudos;
		this.ocupacion = ocupacion;
		this.outroLugarResidencia = outroEnquisa;
		this.outroLugarNacemento = outroNacemento;
		this.lugarNacementoPaisCoincide = lugarNacementoPaisCoincide;
		this.viviuForaDeGalicia = viviuForaDeGalicia;
		this.linguaNativa = linguaNativa;
		this.linguaFalada = linguaFalada;
		this.lugaresDesprazamento = lugaresDesprazamento;
		this.graoDiferenzaFala = graoDiferenzaFala;
		this.mellorGalego = mellorGalego;
		this.peorGalego = peorGalego;
		this.diferenteGalego = diferenteGalego;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Estudo getEstudos() {
		return estudos;
	}

	public void setEstudos(Estudo estudos) {
		this.estudos = estudos;
	}

	public Ocupacion getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(Ocupacion ocupacion) {
		this.ocupacion = ocupacion;
	}

	public Lugar getLugarNacemento() {
		return lugarNacemento;
	}

	public void setLugarNacemento(Lugar lugarNacemento) {
		this.lugarNacemento = lugarNacemento;
	}

	public Lugar getLugarResidencia() {
		return lugarResidencia;
	}

	public void setLugarResidencia(Lugar lugarResidencia) {
		this.lugarResidencia = lugarResidencia;
	}

	public List<Xuizo> getXuizos() {
		return xuizos;
	}

	public void setXuizos(List<Xuizo> xuizos) {
		this.xuizos = xuizos;
	}

	public String getOutroLugarNacemento() {
		return outroLugarNacemento;
	}

	public void setOutroLugarNacemento(String outroLugarNacemento) {
		this.outroLugarNacemento = outroLugarNacemento;
	}

	public String getOutroLugarResidencia() {
		return outroLugarResidencia;
	}

	public void setOutroLugarResidencia(String outroLugarResidencia) {
		this.outroLugarResidencia = outroLugarResidencia;
	}

	public Integer getAnoNacemento() {
		return anoNacemento;
	}

	public void setAnoNacemento(Integer anoNacemento) {
		this.anoNacemento = anoNacemento;
	}

	public Boolean getLugarNacementoPaisCoincide() {
		return lugarNacementoPaisCoincide;
	}

	public void setLugarNacementoPaisCoincide(Boolean lugarNacementoPaisCoincide) {
		this.lugarNacementoPaisCoincide = lugarNacementoPaisCoincide;
	}

	public Boolean getViviuForaDeGalicia() {
		return viviuForaDeGalicia;
	}

	public void setViviuForaDeGalicia(Boolean viviuForaDeGalicia) {
		this.viviuForaDeGalicia = viviuForaDeGalicia;
	}

	public List<Municipio> getOutrosLugaresResidencia() {
		return outrosLugaresResidencia;
	}

	public void setOutrosLugaresResidencia(List<Municipio> outrosLugaresResidencia) {
		this.outrosLugaresResidencia = outrosLugaresResidencia;
	}

	public Lingua getLinguaNativa() {
		return linguaNativa;
	}

	public void setLinguaNativa(Lingua linguaNativa) {
		this.linguaNativa = linguaNativa;
	}

	public Lingua getLinguaFalada() {
		return linguaFalada;
	}

	public void setLinguaFalada(Lingua linguaFalada) {
		this.linguaFalada = linguaFalada;
	}

	public String getLugaresDesprazamento() {
		return lugaresDesprazamento;
	}

	public void setLugaresDesprazamento(String lugaresDesprazamento) {
		this.lugaresDesprazamento = lugaresDesprazamento;
	}

	public Grao getGraoDiferenzaFala() {
		return graoDiferenzaFala;
	}

	public void setGraoDiferenzaFala(Grao graoDiferenzaFala) {
		this.graoDiferenzaFala = graoDiferenzaFala;
	}

	public String getMellorGalego() {
		return mellorGalego;
	}

	public void setMellorGalego(String mellorGalego) {
		this.mellorGalego = mellorGalego;
	}

	public String getPeorGalego() {
		return peorGalego;
	}

	public void setPeorGalego(String peorGalego) {
		this.peorGalego = peorGalego;
	}

	public String getDiferenteGalego() {
		return diferenteGalego;
	}

	public void setDiferenteGalego(String diferenteGalego) {
		this.diferenteGalego = diferenteGalego;
	}
}


