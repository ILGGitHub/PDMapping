(function() {
	'use strict';

	angular.module('app').controller('FormularioLinguaController', FormularioLinguaController);

	FormularioLinguaController.$inject = ['$state', 'informanteService', 'enquisaService', '$q'];

	function FormularioLinguaController($state, informanteService, enquisaService, $q) {
		var vm = this;

		vm.informante = enquisaService.obterInformante();
		vm.posiblesLinguas = informanteService.getPosiblesLinguas();
		vm.validarDatos = validarDatos;
		vm.enviar = enviar;
		vm.auxiliar = {
			desplazase: vm.informante.lugaresDesprazamento != null
		};

		function enviar(atras) {
			if (!vm.auxiliar.desplazase) {
				vm.informante.lugaresDesprazamento = null;
			}
			enquisaService.gardarInformante(vm.informante);
			$state.go('instruccions-audicions');
		}

		// Función usada para comprobar que los datos del informante son válidos y se puede continuar 
		function validarDatos(informante) {
			if (angular.isUndefinedOrNull(informante.linguaNativa) ||
				angular.isUndefinedOrNull(informante.linguaFalada) ||
				(vm.auxiliar.desplazase && angular.isUndefinedOrNull(informante.lugaresDesprazamento))
			) return false;
			return true;
		}
	}
})();