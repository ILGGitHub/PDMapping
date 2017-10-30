(function() {
	'use strict';

	angular.module('app').filter('etiquetaDiferenza', etiquetaDiferenza);

	etiquetaDiferenza.$inject = [];

	function etiquetaDiferenza() {
		return etiquetaDiferenzaFilter;

		function etiquetaDiferenzaFilter(key) {
			return {
				POUCO: 'Pouco diferente',
				ALGO: 'Algo diferente',
				MOITO: 'Moi diferente'
			}[key];
		};
	}
})();