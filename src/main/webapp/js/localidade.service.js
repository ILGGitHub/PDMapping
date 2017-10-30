(function() {
	'use strict';

	angular.module('app').factory('localidadeService', localidadeService);

	localidadeService.$inject = ['$http', '$log', '$q'];

	function localidadeService($http, $log, $q) {
		var service = {
			getProvincias: getProvincias,
			getMunicipios: getMunicipios,
			getParroquias: getParroquias,
			getLugares: getLugares
		};

		return service;

		function getProvincias() {
			var ret = [];
			ret.$promise = $q.defer();

			$http.get('api/provincias')
				.then(getProvinciasComplete)
				.catch(getProvinciasFailed);

			function getProvinciasComplete(response) {
				angular.copy(response.data, ret);
				ret.$promise.resolve(ret);
			}
			function getProvinciasFailed(error) {
				$log.error('XHR Failed for getProvincias.' + error.data);
			}

			return ret;
		}

		function getMunicipios(provincia) {
			var ret = [];
			ret.$promise = $q.defer();

			if (angular.isUndefinedOrNull(provincia)) {
				provincia = '';
			}

			$http.get('api/municipios?provincia=' + provincia)
				.then(getMunicipiosComplete)
				.catch(getMunicipiosFailed);

			function getMunicipiosComplete(response) {
				angular.copy(response.data, ret);
				ret.$promise.resolve(ret);
			}
			function getMunicipiosFailed(error) {
				$log.error('XHR Failed for getMunicipios.' + error.data);
			}

			return ret;
		}

		function getParroquias(municipio) {
			var ret = [];
			ret.$promise = $q.defer();

			if (angular.isUndefinedOrNull(municipio)) {
				municipio = '';
			}

			$http.get('api/parroquias?municipio=' + municipio)
				.then(getParroquiasComplete)
				.catch(getParroquiasFailed);

			function getParroquiasComplete(response) {
				angular.copy(response.data, ret);
				ret.$promise.resolve(ret);
			}
			function getParroquiasFailed(error) {
				$log.error('XHR Failed for getParroquias.' + error.data);
			}

			return ret;
		}

		function getLugares(municipio, parroquia) {
			var ret = [];
			ret.$promise = $q.defer();

			if (angular.isUndefinedOrNull(municipio)) {
				municipio = '';
			}
			if (angular.isUndefinedOrNull(parroquia)) {
				parroquia = '';
			}

			$http.get('api/lugares?municipio=' + municipio +'&parroquia=' + parroquia)
				.then(getLugaresComplete)
				.catch(getLugaresFailed);

			function getLugaresComplete(response) {
				angular.copy(response.data, ret);
				ret.$promise.resolve(ret);
			}
			function getLugaresFailed(error) {
				$log.error('XHR Failed for getLugares.' + error.data);
			}

			return ret;
		}
	}
})();