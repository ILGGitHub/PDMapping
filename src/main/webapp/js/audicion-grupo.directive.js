(function() {
	'use strict';

	angular.module('app').directive('audicionGrupo', audicionGrupo);

	audicionGrupo.$inject = ['$sce', '$log'];

	function audicionGrupo($sce, $log) {
		return {
			scope: {
				startDrawing: '=',
				viewDrawing: '='
			},
			controller: function($scope) {
				var _playing = false;
				var _auxAudio = null;

				this.startPlaying = startPlaying;
				this.stopPlaying = stopPlaying;
				this.isPlaying = isPlaying;
				this.startDrawing = $scope.startDrawing;
				this.viewDrawing = $scope.viewDrawing;

				function isPlaying() {
					return _playing;
				}

				function startPlaying(audio) {
					if (!_playing) {
						audio.play();
						_auxAudio = audio;
						_playing = true;
						return true;
					} else if (_auxAudio === audio) {
						audio.currentTime = 0;
						audio.play();
						return true;
					} else {
						$log.info('Xa está a reproducir outro ficheiro');
						return false;
					}
				}

				function stopPlaying(audio) {
					if (!_playing) {
						// $log.info('Non estaba reproducindo');
					} else if (_auxAudio !== audio) {
						// $log.warn('Non estaba reproducindo este ficheiro');
					} else {
						audio.pause();
						audio.currentTime = 0;
						_auxAudio = null;
						_playing = false;
					}
				}

			}
		}
	}
})();