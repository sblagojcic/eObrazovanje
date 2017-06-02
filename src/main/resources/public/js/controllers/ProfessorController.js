angular.module('eObrazovanjeApp').controller(
		'ProfessorController',
		[
				'$rootScope',
				'$scope',
				'$http',
				'$routeParams',
				'$location',
				function($rootScope, $scope, $http, $routeParams,  $location) {
					$scope.getProfessor = function(id) {
						$http.get('api/professors/' + id).success(
								function(data, status) {
									$scope.professor = data;

								}).error(function() {
							$scope.redAlert = true;

						});
					};

					$scope.getAllProfessors = function() {
						$http.get('api/professors/all').success
							(function(data, status) {
								$scope.professors = data;

						}).error(function() {
							alert('Oops, something went wrong!');
						});

						$scope.resetFilter = function() {

						}
					};
					


					$scope.deleteProfessor = function(id) {
						$http.delete('api/professors/delete/' + id).success(
								function(data, status) {
									$scope.deleted = data;
									$scope.blueAlert = true;
									$scope.getAllProfessors();

								}).error(function() {
							$scope.redAlert = true;

						});
					};

					$scope.hideAlerts = function() {
						$scope.redAlert = false;
						$scope.blueAlert = false;
						$scope.orangeAlert = false;
					};

					$scope.initProfessor = function() {
						$scope.professor = {};

						if ($routeParams && $routeParams.id) {
							// ovo je edit stranica
							$http.get('api/professors/' + $routeParams.id).success(
									function(data) {
										$scope.professor = data;
									}).error(function() {

							});
						}
					};

					$scope.saveProfessor = function() {
						if ($scope.professor.id) {
							// edit stranica
							$http.put('api/professors/edit/' + $scope.professor.id,
									$scope.professor).success(function() {
								$location.path('/professors/all');
							}).error(function() {
								alert("neka greska edita");
							});
						} else {
							// add stranica
							$http.post('api/professors/add/', $scope.professor).success(
									function() {
										$location.path('/professors/all');
									}).error(function() {
								alert('greska dodavanja!')
							});
						}
					};

				} ]);