(function() {
	'use strict';

	angular.module('app').controller('FormularioController', FormularioController);

	FormularioController.$inject = ['$state', 'localidadeService', 'informanteService', 'enquisaService', '$q'];

	/*
		Ollo: para que a ordenación de AngularJS teña en conta os acentos e caracteres raros hai que modificar o código de angular.js (http://www.janholinka.net/Blog/Article/8):
			En concreto, hay que modificar a función compare do filtro "orderByFilter", terminando por isto para que funcione correctamente:

			if (t1 === t2) {
				if (t1 === "string") {
				   v1 = v1.toLocaleLowerCase();
				   v2 = v2.toLocaleLowerCase();
				}
				v1 = v1 == null ? "" : v1.toLocaleString();
				return v1.localeCompare(v2);
			} else {
				return t1.localeCompare(t2);
			}

		Hai outras solucións más complexas, pero para esta aplicación que non usa a versión minimizada chega con facer iso. 
	*/

	function FormularioController($state, localidadeService, informanteService, enquisaService, $q) {
		var vm = this;

		vm.nacemento = {
			dende: 1930,
			ata: new Date().getFullYear() - 5
		};

		vm.enviar = enviar;
		vm.actualizarMunicipios = actualizarMunicipios;
		vm.actualizarParroquias = actualizarParroquias;
		vm.actualizarLugares = actualizarLugares;
		vm.residenciaIgualNacemento = false;
		vm.comprobarResidenciaIgualNacemento = comprobarResidenciaIgualNacemento;
		vm.posiblesEstudos = informanteService.getPosiblesEstudos();
		vm.posiblesOcupacions = informanteService.getPosiblesOcupacions();
		vm.validarDatos = validarDatos;

		// outras residencias
		vm.auxiliar = {};
		vm.actualizarAuxiliar = actualizarAuxiliar;
		vm.engadirResidencia = engadirResidencia;
		vm.eliminarResidencia = eliminarResidencia;

		vm.combo = enquisaService.obterCombo();
		if (vm.combo === null) {
			vm.combo = {
				provincias: localidadeService.getProvincias(),
				residencia: {
					municipios: [],
					parroquias: [],
					lugares: []
				},
				nacemento: {
					municipios: [],
					parroquias: [],
					lugares: []
				}
			};
		}

		vm.informante = enquisaService.obterInformante();
		if (vm.informante.residencia.lugar && vm.informante.residencia.lugar === vm.informante.nacemento.lugar) {
			vm.residenciaIgualNacemento = true;
		}

		function enviar() {
			enquisaService.gardarCombo(vm.combo);
			enquisaService.gardarInformante(vm.informante);
			$state.go('formulario-lingua');
		}

		function actualizarMunicipios(residencia, provincia) {
			if (residencia) {
				vm.informante.residencia.lugar = null;
				vm.combo.residencia.lugares = [];
				vm.informante.residencia.parroquia = null;
				vm.combo.residencia.parroquias = [];
				vm.combo.residencia.municipios = localidadeService.getMunicipios(provincia);
			} else {
				vm.informante.nacemento.lugar = null;
				vm.combo.nacemento.lugares = [];
				vm.informante.nacemento.parroquia = null;
				vm.combo.nacemento.parroquias = [];
				vm.combo.nacemento.municipios = localidadeService.getMunicipios(provincia);
			}
		}

		// Úsase para obter os datos das posibles parroquias en función do municipio. Non se mostran na interface, pero úsanse igualmente para discernir os lugares co mesmo nome
		function actualizarParroquias(residencia, municipio) {
			if (residencia) {
				vm.combo.residencia.parroquias = localidadeService.getParroquias(municipio);
			} else {
				vm.combo.nacemento.parroquias = localidadeService.getParroquias(municipio);
			}
			actualizarLugares(residencia, municipio, null);
		}

		// Úsase para obter os posibles lugares en función do municipio e da parroquia (anque esta última xa non se elixa)
		function actualizarLugares(residencia, municipio, parroquia) {
			var listaLugares = null;
			var listaParroquias = null;

			// Recuperamos os datos e os preparamos para o proceso de cambiar os nome os lugares repetidos en función de si estamos en residencia ou non
			if (residencia) {
				vm.informante.residencia.lugar = null;
				vm.combo.residencia.lugares = localidadeService.getLugares(municipio, parroquia);
				listaLugares = vm.combo.residencia.lugares;
				listaParroquias = vm.combo.residencia.parroquias;
			} else {
				vm.informante.nacemento.lugar = null;
				vm.combo.nacemento.lugares = localidadeService.getLugares(municipio, parroquia);
				listaLugares = vm.combo.nacemento.lugares;
				listaParroquias = vm.combo.nacemento.parroquias;
			}

			// Aquí renombramos os lugares que teñen un nome repetido. 
			// Usamos $.each por ser sincrono, en vez de o de angular que é asincrono e teríamos que esperar con promesas. 
			listaLugares.$promise.promise.then(function() {
				var lugaresRepetidos = {};
				$.each(listaLugares, function(k1, lugar) {
					// No array de cada nome vamos a ir metendo todos os lugares de nombre repetido. Se non existe o array, creámolo. 
					if (angular.isUndefinedOrNull(lugaresRepetidos[lugar.nome])) {
						lugaresRepetidos[lugar.nome] = [];
					}

					lugaresRepetidos[lugar.nome].push(lugar);
				});
				// Repasamos a lista de lugares repetidos, añadindo a parroquia o nome de ser neceario
				$.each(lugaresRepetidos, function(k2, listaLugaresMesmoNome) {
					if (listaLugaresMesmoNome.length > 1) {
						$.each(listaLugaresMesmoNome, function(k3, lugar) {
							// Obtemos o nome da parroquia da lista de parroquias, que xa a temos cargada porque ó cambiar o concello se recarga automáticamente 
							lugar.nome += ' - ' + _parroquiaPorCodigo(listaParroquias, lugar.parr).nome;
						});
					}
				});
			});
		}

		// Si se marca o desmarca el check de que los lugares sean el mismo, se lanza esto para igualar los combos y datos
		function comprobarResidenciaIgualNacemento() {
			if (vm.residenciaIgualNacemento) {
				vm.combo.residencia = vm.combo.nacemento;
				vm.informante.residencia = vm.informante.nacemento;
			} else {
				vm.combo.residencia = {};
				vm.informante.residencia = {};
				angular.copy(vm.combo.nacemento, vm.combo.residencia);
				angular.copy(vm.informante.nacemento, vm.informante.residencia);
			}
		}

		// Función usada para comprobar que los datos del informante son válidos y se puede continuar 
		function validarDatos(informante) {
			if (angular.isUndefinedOrNull(informante.anoNacemento) ||
				angular.isUndefinedOrNull(informante.sexo) ||
				angular.isUndefinedOrNull(informante.estudos) ||
				angular.isUndefinedOrNull(informante.ocupacion) ||
				(angular.isUndefinedOrNull(informante.residencia.lugar) && angular.isUndefinedOrNull(informante.residencia.outro)) ||
				(angular.isUndefinedOrNull(informante.nacemento.lugar) && angular.isUndefinedOrNull(informante.nacemento.outro)) ||
				angular.isUndefinedOrNull(informante.lugarNacementoPaisCoincide)
			) return false;
			return true;
		}

		// Obtemos unha parroquia dunha lista polo código
		function _parroquiaPorCodigo(lista, codigo) {
			var out;
			$.each(lista, function(k, v) {
				if (v.codigo == codigo) {
					out = v;
				}
			});
			return out;
		}

		function actualizarAuxiliar() {
			vm.auxiliar.municipios = localidadeService.getMunicipios(vm.auxiliar.provincia);
		}

		function resetAuxiliar() {
			vm.auxiliar = {};
		}

		function engadirResidencia() {
			vm.informante.outrosLugaresDeResidencia.push({
				codigo: vm.auxiliar.municipio.codigo,
				nome: vm.auxiliar.municipio.nome
			});
			resetAuxiliar();
		}

		function eliminarResidencia(item) {
			vm.informante.outrosLugaresDeResidencia.splice(
				vm.informante.outrosLugaresDeResidencia.indexOf(item),
				1
			);
		}
	}
})();