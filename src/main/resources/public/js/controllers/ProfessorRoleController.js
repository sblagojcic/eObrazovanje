angular.module('eObrazovanjeApp').controller(
		'ProfessorRoleController',
		[
				'$rootScope',
				'$scope',
				'$http',
				'$routeParams',
				'$location',
				'authService',
				function($rootScope, $scope, $http, $routeParams, authService, $location) {
					$rootScope.userId = localStorage.getItem('userId');
					$scope.getProfessorRole = function(id) {
						$http.get('api/professorRoles/' + id).success(
								function(data, status) {
									$scope.professoRole = data;

								}).error(function() {
							$scope.redAlert = true;

						});
					};

					$scope.getAllProfessorRoles = function() {
						$http.get('api/professorRoles/all').success
							(function(data, status) {
								$scope.professorRoles = data;

						}).error(function() {
							alert('Oops, something went wrong!');
						});

						$scope.resetFilter = function() {

						}
					};
					$scope.getAllSubjects = function() {
						$http.get('api/subjects/all').success
							(function(data, status) {
								$scope.subjects = data;

						}).error(function() {
							alert('Oops, something went wrong!');
						});

						$scope.resetFilter = function() {

						}
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


					$scope.deleteProfessorRole = function(id) {
						$http.delete('api/professorRoles/delete/' + id).success(
								function(data, status) {
									$scope.deleted = data;
									$scope.blueAlert = true;
									$scope.getAllProfessorRoles();

								}).error(function() {
							$scope.redAlert = true;

						});
					};

					$scope.hideAlerts = function() {
						$scope.redAlert = false;
						$scope.blueAlert = false;
						$scope.orangeAlert = false;
					};

					$scope.initProfessorRole = function() {
						$scope.professorRole = {};

						if ($routeParams && $routeParams.id) {
							// ovo je edit stranica
							$http.get('api/professorRoles/' + $routeParams.id).success(
									function(data) {
										$scope.professorRole = data;
									}).error(function() {

							});
						}
					};

					$scope.saveProfessorRole = function() {
						if ($scope.professorRole.id) {
							// edit stranica
							$http.put('api/professorRoles/edit/' + $scope.professorRole.id,
									$scope.professorRole).success(function() {
										window.location ="#/professorRoles";
							}).error(function() {
								alert("neka greska edita");
							});
						} else {
							// add stranica
							console.log($scope.professorRole)
							$http.post('api/professorRoles/add/', $scope.professorRole).success(
									function() {
										window.location ="#/professorRoles";
									}).error(function() {
								alert('greska dodavanja!')
							});
						}
					};

				} ]);