(function() {
	'use strict';

	angular.module('app').controller('FormularioAudicionsController', FormularioAudicionsController);

	FormularioAudicionsController.$inject = ['$state', 'informanteService', 'enquisaService', '$q'];

	function FormularioAudicionsController($state, informanteService, enquisaService, $q) {
		var vm = this;

		vm.informante = enquisaService.obterInformante();
		vm.posiblesGraos = informanteService.getPosiblesGraos();
		vm.validarDatos = validarDatos;
		vm.enviar = enviar;

		function enviar(atras) {
			enquisaService.gardarInformante(vm.informante);
			enquisaService.enviarDatos()
				.success(function() {
					$state.go('remate');
				})
				.error(function(error) {
					swal({
						title: 'Desculpa, produciuse un erro',
						text: 'Houbo un erro ó enviar a enquisa, deberá volver a empezar. Se o erro se repite consulte a un administrador.',
						type: 'error',
						allowEscapeKey: true,
						allowOutsideClick: true,
						confirmButtonText: 'Continuar'
					});
					$timeout(function() {
						$state.go('inicio');
					}, 1000);
				});
		}

		// Función usada para comprobar que los datos del informante son válidos y se puede continuar
		function validarDatos(informante) {
			if (angular.isUndefinedOrNull(informante.graoDiferenzaFala))
				return false;
			return true;
		}
	}
})();