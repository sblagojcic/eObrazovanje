angular.module('eObrazovanjeApp').controller(
	'ObligationController', [
		'$rootScope',
		'$scope',
		'$http',
		'$routeParams',
		'$location',
		'authService',
		function($rootScope, $scope, $http, $routeParams, authService, $location) {
			$rootScope.userId = localStorage.getItem('userId');
			$scope.getProfessor = function(id) {
				$http.get('api/obligations/' + id).success(
					function(data, status) {
						$scope.obligation = data;

					}).error(function() {
					$scope.redAlert = true;

				});
			};
			
			$scope.getUserObligations = function() {
				$http.get('api/obligations/getFor/' + $routeParams.id).success(function(data, status) {
					$scope.obligations = data;
				}).error(function() {
					alert('Oops, something went wrong!');
				});
			};

			$scope.getSubjects = function(id) {
				if ($scope.isProfessor()) {
					$http.get('api/subjects/getFor/'+$rootScope.userId).success(
							function(data, status) {
								$scope.subjects = data;

							}).error(function() {
							$scope.redAlert = true;

						});
				} else {
					$http.get('api/subjects/all').success(
							function(data, status) {
								$scope.subjects = data;

							}).error(function() {
							$scope.redAlert = true;

						});
				}
			};
			$scope.getAllObligations = function() {
				$http.get('api/obligations/all').success(function(data, status) {
					$scope.obligations = data;
				}).error(function() {
					alert('Oops, something went wrong!');
				});
			};
			$scope.deleteObligation = function(id) {
				$http.delete('api/obligations/delete/' + id).success(
					function(data, status) {
						$scope.deleted = data;
						$scope.blueAlert = true;
						if ($scope.isAdmin()) {
							$scope.getAllObligations();
						} else if($scope.isProfessor()){
							$scope.getUserObligations();
						}

					}).error(function() {
					$scope.redAlert = true;

				});
			};

			$scope.hideAlerts = function() {
				$scope.redAlert = false;
				$scope.blueAlert = false;
				$scope.orangeAlert = false;
			};

			$scope.initObligation = function() {
				$scope.obligation = {};

				if ($routeParams && $routeParams.id) {
					// ovo je edit stranica
					$http.get('api/obligations/' + $routeParams.id).success(
						function(data) {
							$scope.obligation = data;
						}).error(function() {

					});
				}
			};

			$scope.saveObligation = function() {
				if ($scope.obligation.id) {
					// edit stranica
					$http.put('api/obligations/edit/' + $scope.obligation.id,
						$scope.obligation).success(function() {
							if ($scope.isAdmin()) {
								window.location ="#/obligations";
							} else if($scope.isProfessor()){
								window.location ="#/obligations/getFor/"+$rootScope.userId;
							}
							

					}).error(function() {
						alert("Editing error!");
					});
				} else {
					// add stranica
					$http.post('api/obligations/add/', $scope.obligation).success(
						function() {
							if ($scope.isAdmin()) {
								window.location ="#/obligations";
							} else if($scope.isProfessor()){
								window.location ="#/obligations/getFor/"+$rootScope.userId;
							}
						}).error(function() {
						alert('Error while adding!')
					});
				}
			};
		}
	]);

			
			