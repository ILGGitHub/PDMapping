(function() {
	'use strict';

	angular.module('app').filter('etiquetaEstudo', etiquetaEstudo);

	etiquetaEstudo.$inject = [];

	function etiquetaEstudo() {
		return etiquetaEstudoFilter;

		function etiquetaEstudoFilter(key) {
			return {
				SEN_ESTUDOS: 'Sen estudos',
				PRIMARIOS: 'Primarios',
				SECUNDARIOS: 'Secundarios ou de formación profesional',
				UNIVERSITARIOS: 'Universitarios',
				OUTROS_ESTUDOS: 'Outros estudos'
			}[key];
		};
	}
})();