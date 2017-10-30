(function() {
	'use strict';

	angular.module('app').factory('enquisaService', enquisaService);

	enquisaService.$inject = ['$http', '$log', '$q'];

	function enquisaService($http, $log, $q) {
		var datos = null;
		var combo = null;
		inicializar();

		var service = {
			gardarInformante: gardarInformante,
			obterInformante: obterInformante,
			gardarCombo: gardarCombo,
			obterCombo: obterCombo,
			gardarXuizos: gardarXuizos,
			obterXuizos: obterXuizos,
			gardarCuestionario: gardarCuestionario,
			obterCuestionario: obterCuestionario,
			enviarDatos: enviarDatos,
			inicializar: inicializar
		};

		return service;

		function gardarInformante(inf) {
			datos.informante = inf;
		}

		function gardarCombo(c) {
			combo = c;
		}

		function obterInformante() {
			return datos.informante;
		}

		function obterCombo() {
			return combo;
		}

		function gardarXuizos(xuiz, parecidos) {
			datos.xuizos = xuiz;
			datos.parecidos = parecidos;
		}

		function obterXuizos() {
			return datos.xuizos;
		}

		function gardarCuestionario(cuest) {
			datos.cuestionario = cuest;
		}

		function obterCuestionario() {
			return datos.cuestionario;
		}

		function enviarDatos() {
			var datosPreparados = null;
			if (datos.informante !== null) {
				datosPreparados = {
					informante: _prepararInformante(datos.informante),
					xuizos: _prepararXuizos(datos.xuizos, datos.parecidos),
					cuestionario: _prepararCuestionario(datos.cuestionario)
				};
			}

			return $http.post('api/enquisas', datosPreparados)
				.success(enviarDatosSuccess)
				.error(enviarDatosFailed);

			function enviarDatosSuccess() {
				inicializar();
			}

			function enviarDatosFailed(error) {
				$log.error('XHR Failed for enviarDatos.' + error);
			}
		}

		function _prepararInformante(inf) {
			var informante = {};
			angular.copy(inf, informante);
			informante.lugarResidencia = informante.residencia.lugar;
			informante.outroLugarResidencia = informante.residencia.outro;
			informante.lugarNacemento = informante.nacemento.lugar;
			informante.outroLugarNacemento = informante.nacemento.outro;
			informante.outrosLugaresDeResidencia = informante.outrosLugaresDeResidencia.map(function(m) {
				return m.codigo
			});
			delete informante.residencia;
			delete informante.nacemento;
			return informante;
		}

		function _prepararXuizos(xuiz, parecidos) {
			var xuizos = [];
			for (var property in xuiz) {
				var item = xuiz[property].toGeoJSON().geometry;
				xuizos.push({
					id: property,
					geom: item,
					parecido: parecidos[property]
				});
			}
			return xuizos;
		}

		function _prepararCuestionario(cuest) {
			var cuestionario = [];
			for (var property in cuest) {
				cuestionario.push({
					id: property,
					respuesta: cuest[property]
				});
			}
			return cuestionario;
		}

		function inicializar() {
			combo = null;
			datos = {
				informante: {
					residencia: {
						provincia: null,
						municipio: null,
						parroquia: null,
						lugar: null
					},
					nacemento: {
						provincia: null,
						municipio: null,
						parroquia: null,
						lugar: null
					},
					outrosLugaresDeResidencia: [],
					viviuForaDeGalicia: false
				},
				xuizos: null,
				parecidos: null,
				cuestionario: null
			}
		}
	}
})();