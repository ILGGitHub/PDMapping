package es.enxenio.ilga.pdmapping.web.rest.custom;

import es.enxenio.ilga.pdmapping.modelo.entidades.informantes.Estudo;
import es.enxenio.ilga.pdmapping.modelo.entidades.informantes.Grao;
import es.enxenio.ilga.pdmapping.modelo.entidades.informantes.Lingua;
import es.enxenio.ilga.pdmapping.modelo.entidades.informantes.Ocupacion;
import es.enxenio.ilga.pdmapping.modelo.entidades.informantes.Sexo;

import java.util.ArrayList;
import java.util.List;

public class JSONInformante {
	private Long id;
	private Integer anoNacemento;
	private Sexo sexo;
	private Estudo estudos;
	private Ocupacion ocupacion;
	private String lugarResidencia;
	private String outroLugarResidencia;
	private String lugarNacemento;
	private String outroLugarNacemento;
	private Boolean lugarNacementoPaisCoincide;
	private Boolean viviuForaDeGalicia;
	private List<String> outrosLugaresDeResidencia = new ArrayList<String>();
	private Lingua linguaNativa;
	private Lingua linguaFalada;
	private String lugaresDesprazamento;
	private Grao graoDiferenzaFala;
	private String mellorGalego;
	private String peorGalego;
	private String diferenteGalego;

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

	public String getLugarResidencia() {
		return lugarResidencia;
	}

	public void setLugarResidencia(String lugarResidencia) {
		this.lugarResidencia = lugarResidencia;
	}

	public String getLugarNacemento() {
		return lugarNacemento;
	}

	public void setLugarNacemento(String lugarNacemento) {
		this.lugarNacemento = lugarNacemento;
	}

	public String getOutroLugarResidencia() {
		return outroLugarResidencia;
	}

	public void setOutroLugarResidencia(String outroLugarResidencia) {
		this.outroLugarResidencia = outroLugarResidencia;
	}

	public String getOutroLugarNacemento() {
		return outroLugarNacemento;
	}

	public void setOutroLugarNacemento(String outroLugarNacemento) {
		this.outroLugarNacemento = outroLugarNacemento;
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

	public List<String> getOutrosLugaresDeResidencia() {
		return outrosLugaresDeResidencia;
	}

	public void setOutrosLugaresDeResidencia(List<String> outrosLugaresDeResidencia) {
		this.outrosLugaresDeResidencia = outrosLugaresDeResidencia;
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

	@Override
	public String toString() {
		return "JSONInformante{" +
				"id=" + id +
				", anoNacemento=" + anoNacemento +
				", sexo=" + sexo +
				", estudos=" + estudos +
				", ocupacion=" + ocupacion +
				", lugarResidencia='" + lugarResidencia + '\'' +
				", outroLugarResidencia='" + outroLugarResidencia + '\'' +
				", lugarNacemento='" + lugarNacemento + '\'' +
				", outroLugarNacemento='" + outroLugarNacemento + '\'' +
				", lugarNacementoPaisCoincide=" + lugarNacementoPaisCoincide +
				", viviuForaDeGalicia=" + viviuForaDeGalicia +
				", outrosLugaresDeResidencia=" + outrosLugaresDeResidencia +
				", linguaNativa=" + linguaNativa +
				", linguaFalada=" + linguaFalada +
				", lugaresDesprazamento='" + lugaresDesprazamento + '\'' +
				", graoDiferenzaFala=" + graoDiferenzaFala +
				", mellorGalego='" + mellorGalego + '\'' +
				", peorGalego='" + peorGalego + '\'' +
				'}';
	}

	public String getDiferenteGalego() {
		return diferenteGalego;
	}

	public void setDiferenteGalego(String diferenteGalego) {
		this.diferenteGalego = diferenteGalego;
	}
}
