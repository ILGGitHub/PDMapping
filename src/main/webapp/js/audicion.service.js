(function() {
	'use strict';

	angular.module('app').factory('audicionService', audicionService);

	audicionService.$inject = ['$http', '$log', '$q'];

	function audicionService($http, $log, $q) {
		var service = {
			getAudicions: getAudicions
		};

		return service;

		function shuffle(array) {
			var currentIndex = array.length, temporaryValue, randomIndex;

			// While there remain elements to shuffle...
			while (0 !== currentIndex) {

				// Pick a remaining element...
				randomIndex = Math.floor(Math.random() * currentIndex);
				currentIndex -= 1;

				// And swap it with the current element.
				temporaryValue = array[currentIndex];
				array[currentIndex] = array[randomIndex];
				array[randomIndex] = temporaryValue;
			}

			return array;
		}

		function getAudicions() {
			return $http.get('api/audicions')
				.then(getAudicionsComplete)
				.catch(getAudicionsFailed);
			function getAudicionsComplete(response) {
				// random e filtramos as non visibles
				return shuffle(response.data.filter(function(aud) {
					return aud.visible;
				}));
			}
			function getAudicionsFailed(error) {
				$log.error('XHR Failed for getAudicions.' + error.data);
			}
		}
	}
})();