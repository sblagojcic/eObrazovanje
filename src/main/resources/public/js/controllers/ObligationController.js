angular.module('eObrazovanjeApp').controller(
	'ObligationController', [
		'$rootScope',
		'$scope',
		'$http',
		'$routeParams',
		'$location',
		function($rootScope, $scope, $http, $routeParams, $location) {
			$scope.getProfessor = function(id) {
				$http.get('api/obligations/' + id).success(
					function(data, status) {
						$scope.obligation = data;

					}).error(function() {
					$scope.redAlert = true;

				});
			};
			$scope.getSubjects = function(id) {
				$http.get('api/subjects/all').success(
					function(data, status) {
						$scope.subjects = data;

					}).error(function() {
					$scope.redAlert = true;

				});
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
						$location.path('/obligations/all');
					}).error(function() {
						alert("Editing error!");
					});
				} else {
					// add stranica
					$http.post('api/obligations/add/', $scope.obligation).success(
						function() {
							$location.path('/obligations/all');
						}).error(function() {
						alert('Error while adding!')
					});
				}
			};
		}
	]);