(function() {
	'use strict';

	angular.module('app').directive('audicion', audicion);

	audicion.$inject = ['$sce'];

	function audicion($sce) {
		return {
			templateUrl: 'partials/audicion.htm',
			scope: {
				item: '=',
				existePoligono: '&'
			},
			require: '^audicionGrupo',
			link: function(scope, element, attrs, controller) {
				scope.modoDebug = modoDebug;
				scope.outraAudicionReproducindose = outraAudicionReproducindose;
				scope.playing = false;
				if (scope.existePoligono()) {
					scope.pintable = true; //se xa hay poligono xa se escoitou o audio
					scope.pintado = true; 
				} else {
					scope.pintable = false; //se non se reproduce o audio non se pode pintar a zona
					scope.pintado = false; //se non se pintou non se pode ver
				}
				var _audio = element.find('audio')[0];

				_audio.addEventListener('ended', audioEnded);
				scope.getItemUrl = getItemUrl;

				element.find('.empezar').on('click', empezarAudio);
				element.find('.reiniciar').on('click', reempezarAudio);
				if (debug_mode) {
					element.find('.deter').on('click', deterAudio);
				}
				element.find('.pintar').on('click', empezarDibujoZona);

				element.find('.ver-pintura').on('click', function() {
					controller.viewDrawing(scope.item.id);
				});

				function getItemUrl(id) {
					return $sce.trustAsResourceUrl('api/audicions/' + id);
				}

				function empezarAudio() {
					if (controller.startPlaying(_audio)) {
						scope.playing = true;
						if (!scope.$root.$$phase) {
							scope.$apply();
						}
					}
				}

				function reempezarAudio() {
					if (controller.startPlaying(_audio)) {}
				}

				function deterAudio() {
					scope.pintable = true;
					controller.stopPlaying(_audio);
					scope.playing = false;
					if (!scope.$root.$$phase) {
						scope.$apply();
					}
					controller.startDrawing(scope.item.id, function(finalizado) {
						scope.pintado = finalizado;
					});
				}

				function audioEnded() {
					deterAudio();
				}

				function empezarDibujoZona() {
					scope.pintado = false;
					controller.startDrawing(scope.item.id, function(finalizado) {
						scope.pintado = finalizado;
						controller.stopPlaying(_audio);
						scope.playing = false;
						if (!scope.$root.$$phase) {
							scope.$apply();
						}
					});
				}

				function outraAudicionReproducindose() {
					return !scope.playing && controller.isPlaying()
				}

				function modoDebug() {
					return debug_mode;
				}
			}
		}
	}
})();