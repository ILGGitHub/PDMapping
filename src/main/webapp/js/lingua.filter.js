(function() {
	'use strict';

	angular.module('app').filter('etiquetaLingua', etiquetaLingua);

	etiquetaLingua.$inject = [];

	function etiquetaLingua() {
		return etiquetaLinguaFilter;

		function etiquetaLinguaFilter(key) {
			return {
				GALEGO: 'Só galego',
				MAIS_GALEGO: 'Máis galego ca castelán',
				AMBOS: 'Galego e castelán',
				MAIS_CASTELAN: 'Máis castelán ca galego',
				CASTELAN: 'Só castelán',
				OUTRA: 'Outra'
			}[key];
		};
	}
})();