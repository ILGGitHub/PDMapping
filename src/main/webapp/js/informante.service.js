(function() {
	'use strict';

	angular.module('app').factory('informanteService', informanteService);

	informanteService.$inject = ['$http', '$log', '$q'];

	function informanteService($http, $log, $q) {
		var service = {
			getPosiblesEstudos: getPosiblesEstudos,
			getPosiblesOcupacions: getPosiblesOcupacions,
			getPosiblesLinguas: getPosiblesLinguas,
			getPosiblesGraos: getPosiblesGraos
		};

		return service;

		function getPosiblesEstudos() {
			var ret = [];
			ret.$promise = $q.defer();

			$http.get('api/estudos')
				.then(getPosiblesEstudosComplete)
				.catch(getPosiblesEstudosFailed);

			function getPosiblesEstudosComplete(response) {
				angular.copy(response.data, ret);
				ret.$promise.resolve(ret);
			}
			function getPosiblesEstudosFailed(error) {
				$log.error('XHR Failed for getPosiblesEstudos.' + error.data);
			}

			return ret;
		}

		function getPosiblesOcupacions() {
			var ret = [];
			ret.$promise = $q.defer();

			$http.get('api/ocupacions')
				.then(getPosiblesOcupacionsComplete)
				.catch(getPosiblesOcupacionsFailed);

			function getPosiblesOcupacionsComplete(response) {
				angular.copy(response.data, ret);
				ret.$promise.resolve(ret);
			}
			function getPosiblesOcupacionsFailed(error) {
				$log.error('XHR Failed for getPosiblesOcupacions.' + error.data);
			}

			return ret;
		}

		function getPosiblesLinguas() {
			var ret = [];
			ret.$promise = $q.defer();

			$http.get('api/linguas')
				.then(getPosiblesLinguasComplete)
				.catch(getPosiblesLinguasFailed);

			function getPosiblesLinguasComplete(response) {
				angular.copy(response.data, ret);
				ret.$promise.resolve(ret);
			}
			function getPosiblesLinguasFailed(error) {
				$log.error('XHR Failed for getPosiblesLinguas.' + error.data);
			}

			return ret;
		}

		function getPosiblesGraos() {
			var ret = [];
			ret.$promise = $q.defer();

			$http.get('api/graos')
				.then(getPosiblesGraosComplete)
				.catch(getPosiblesGraosFailed);

			function getPosiblesGraosComplete(response) {
				angular.copy(response.data, ret);
				ret.$promise.resolve(ret);
			}
			function getPosiblesGraosFailed(error) {
				$log.error('XHR Failed for getPosiblesGraos.' + error.data);
			}

			return ret;
		}
	}
})();