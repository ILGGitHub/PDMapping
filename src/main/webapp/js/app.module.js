(function() {
	'use strict';

	angular.module('app', [
		'ui.router',
		'ngResource',
		'ngSanitize',
		'ui.bootstrap',
		'leaflet-directive'
	]).config(routeConfig).run(appRun);

	function obterRuta(ruta) {
		if (no_concrete_routing) {
			return '/';
		} else {
			return '/' + ruta;
		}
	}

	routeConfig.$inject = ['$stateProvider', '$urlRouterProvider'];
	function routeConfig($stateProvider, $urlRouterProvider) {
		$urlRouterProvider.otherwise(obterRuta('inicio'));
		$stateProvider.state('inicio', {
			url: obterRuta('inicio'),
			templateUrl: 'partials/inicio.htm',
			controller: ['enquisaService', function(enquisaService) {
				enquisaService.inicializar();
			}]
		}).state('instruccions-formulario', {
			url: obterRuta('instruccions-formulario'),
			templateUrl: 'partials/instruccions-formulario.htm'
		}).state('instruccions-audicions', {
			url: obterRuta('instruccions-audicions'),
			templateUrl: 'partials/instruccions-audicions.htm'
		}).state('remate', {
			url: obterRuta('remate'),
			templateUrl: 'partials/remate.htm'
		}).state('formulario', {
			url: obterRuta('formulario'),
			templateUrl: 'partials/formulario.htm',
			controller: 'FormularioController',
			controllerAs: 'f'
		}).state('formulario-lingua', {
			url: obterRuta('formulario-lingua'),
			templateUrl: 'partials/formulario-lingua.htm',
			controller: 'FormularioLinguaController',
			controllerAs: 'f'
		}).state('formulario-audicions', {
			url: obterRuta('formulario-audicions'),
			templateUrl: 'partials/formulario-audicions.htm',
			controller: 'FormularioAudicionsController',
			controllerAs: 'f'
		}).state('mapa', {
			url: obterRuta('mapa'),
			templateUrl: 'partials/mapa.htm',
			controller: 'MapaController',
			controllerAs: 'm',
			resolve: {
				audicions: ['audicionService', function(audicionService) {
					return audicionService.getAudicions();
				}]
			}
		});
	}

	appRun.$inject = ['$rootScope'];
	function appRun($rootScope) {
		angular.isUndefinedOrNull = function(val) {
			return angular.isUndefined(val) || val === null;
		};

		//Traducción leaflet draw
		L.drawLocal.draw.handlers.polygon.tooltip.start = 'Fai clic para comezar a pintar a zona';
		L.drawLocal.draw.handlers.polygon.tooltip.cont = 'Segue marcando os lados do polígono';
		L.drawLocal.draw.handlers.polygon.tooltip.end = 'Fai clic no primeiro elemento para terminar (ou fai doble clic)';
	}
})();