(function() {
	'use strict';

	angular.module('app').filter('etiquetaOcupacion', etiquetaOcupacion);

	etiquetaOcupacion.$inject = [];

	function etiquetaOcupacion() {
		return etiquetaOcupacionFilter;

		function etiquetaOcupacionFilter(key) {
			return {
				ESTUDANTE: 'Estudante',
				FUNCIONARIO: 'Funcionario',
				DOMESTICO: 'Traballador no ámbito doméstico sen retribución salarial',
				AUTONOMO_PRIMARIO: 'Autónomo no sector primario (gandaría, agricultura, pesca...)',
				AUTONOMO_SECUNDARIO: 'Autónomo no sector secundario (artesanía e industria)',
				AUTONOMO_TERCIARIO: 'Autónomo no sector terciario (servizos)',
				ALLEA_PRIMARIO: 'Traballador por conta allea no sector primario (gandaría, agricultura, pesca...)',
				ALLEA_SECUNDARIO: 'Traballador por conta allea no sector secundario (artesanía e industria)',
				ALLEA_TERCIARIO: 'Traballador por conta allea no sector terciario (servizos)',
				PARO: 'No paro'
			}[key];
		};
	}
})();
