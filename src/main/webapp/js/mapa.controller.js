(function() {
	'use strict';

	angular.module('app').controller('MapaController', MapaController);

	MapaController.$inject = ['audicions', 'leafletData', '$timeout', 'enquisaService', '$state', '$scope'];

	function MapaController(audicions, leafletData, $timeout, enquisaService, $state, $scope) {
		var vm = this;
		var _map = null;

		vm.enviar = enviar;
		vm.startDrawing = startDrawing;
		vm.viewDrawing = viewDrawing;
		vm.cancelDrawing = cancelDrawing;
		vm.audicions = audicions;
		vm.existePoligono = existePoligono;
		vm.todosEscoitados = todosEscoitados;
		vm.busy = false;

		vm.contador = 0;
		vm.parecidos = {};
		audicions.forEach(function(aud) {
			vm.parecidos[aud.id] = 0;
		});

		vm.defaults = {
			// minZoom: 8,
			// maxZoom: 8
		};
		vm.center = {
			lat: 42.80777884235988,
			lng: -8.0035400390625,
			zoom: 8.15
		};
		vm.layers = {
			baselayers: {
				mapOne: {
					name: 'mapOne',
					type: 'wms',
					url: geoserver_wms_url,
					layerParams: {},
					layerOptions: {
						layers: 'ESP_adm0,PRT_adm0',
						format: 'image/png',
						transparent: true
					}
				}
			},
			overlays: {
				mapTwo: {
					name: 'mapTwo',
					type: 'wms',
					url: geoserver_wms_url,
					visible: true,
					layerOptions: {
						layers: 'sombreado_galicia,rios_galicia',
						format: 'image/png',
						transparent: true
					}
				},
				mapThree: {
					name: 'mapThree',
					type: 'wms',
					url: geoserver_wms_url,
					visible: true,
					layerOptions: {
						layers: 'ciudades_galicia',
						format: 'image/png',
						transparent: true,
						tileSize: 3500
					}
				}
			}
		};

		function enviar(atras) {
			enquisaService.gardarXuizos(_polygons, vm.parecidos);
			$state.go('formulario-audicions');
		}

		// Iniciamos a estructura para gardar os polígonos debuxados
		var listaTemporalPoligonos = enquisaService.obterXuizos();
		var _polygons = {};
		var _numeroDePoligonos = 0;
		var _callback = null;
		var _freedraw = null;

		leafletData.getMap().then(function(map) {
			_map = map;
			(function() {
				// Para cada polígono recuperado dos datos gardados, engadímolo no mapa
				for (var pKey in listaTemporalPoligonos) {
					addPolygon(pKey, listaTemporalPoligonos[pKey].getLatLngs());
					delete listaTemporalPoligonos[pKey];
				}
			})();
			_freedraw = new L.FreeDraw();
			deshabilitarMovementoEZoom();
			_map.addLayer(_freedraw); //ollo, debe haber algún problema que hay que deshabilitar zoom e movemento antes e despois
			deshabilitarMovementoEZoom();
		});

		function deshabilitarMovementoEZoom() {
			_map.dragging.disable();
			_map.touchZoom.disable();
			_map.doubleClickZoom.disable();
			_map.scrollWheelZoom.disable();
			if (_map.tap) _map.tap.disable();
		}

		function addPolygon(id, latLngGroup) {
			_polygons[id] = new L.polygon(latLngGroup);
			_recontarNumeroDePoligonos();
			var aux = _polygons[id];
			aux.addTo(_map);
			$timeout(function() {
				_map.removeLayer(aux);
			}, 5000);
			return _polygons[id];
		}

		function _recontarNumeroDePoligonos() {
			_numeroDePoligonos = 0;
			for (var pAux in _polygons) {
				_numeroDePoligonos++;
			}
		}

		function _eliminarPoligono(id) {
			delete _polygons[id];
			_recontarNumeroDePoligonos();
		}

		function startDrawing(id, callback) {
			_eliminarPoligono(id);
			vm.busy = true;
			_apply();
			_callback = callback;
			if (!angular.isUndefinedOrNull(_polygons[id])) {
				_polygons[id] = null;
			}
			_freedraw.setMode(L.FreeDraw.MODES.CREATE);
			_freedraw.on('markers', function getMarkers(eventData) {
				_freedraw.off('markers');
				var createdPolygon = addPolygon(id, eventData.latLngs);
				_freedraw.destroyPolygon(_freedraw.getPolygons()[0]);
				_freedraw.unsetMode();
				vm.busy = false;

				if (createdPolygon.toGeoJSON().geometry.coordinates[0].length < 4) {
					swal({
						title: 'Error',
						text: 'Produciuse algún error, volva a debuxar a zona',
						type: 'error',
						allowEscapeKey: true,
						allowOutsideClick: true,
						showConfirmButton: true,
						confirmButtonText: 'Continuar'
					});
					$timeout(function() {
						startDrawing(id, callback);
					}, 500);
				} else {
					if (!angular.isUndefinedOrNull(_callback)) _callback(true);
				}
			});
		}

		function viewDrawing(id) {
			if (!angular.isUndefinedOrNull(_polygons[id])) {
				var aux = _polygons[id];
				aux.addTo(_map);
				$timeout(function() {
					_map.removeLayer(aux);
				}, 5000);
			}
		}

		function cancelDrawing() {
			_freedraw.off('markers');
			vm.busy = false;
			if (!angular.isUndefinedOrNull(_callback)) _callback(false);
		}

		function existePoligono(id) {
			return !angular.isUndefinedOrNull(_polygons[id]) ||
				!angular.isUndefinedOrNull(listaTemporalPoligonos) && !angular.isUndefinedOrNull(listaTemporalPoligonos[id]);
		}

		function todosEscoitados() {
			return vm.audicions.length === _numeroDePoligonos;
		}

		function _apply() {
			if (!$scope.$root.$$phase) {
				$scope.$apply();
			}
		}
	}
})();