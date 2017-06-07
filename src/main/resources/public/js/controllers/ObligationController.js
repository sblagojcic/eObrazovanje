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
			$scope.pageNumber = 0;
			$scope.getAllObligations = function() {
				$http.get('api/obligations', {
					params: {
						"pageNumber": $scope.pageNumber

					}
				}).success(function(data, status) {
					$scope.obligations = data;
					$scope.pageNum = 1;
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
			// paginacija
			$scope.previousPage = function() {
				if ($scope.pageNumber != 0) {
					$scope.pageNumber = $scope.pageNumber - 1;
				}
				$http.get('api/obligations', {
					params: {
						"pageNumber": $scope.pageNumber

					}
				}).success(function(data, status) {
					$scope.obligations = data.content;
					$scope.pageNum = data.number + 1;
				}).error(function() {
					alert('Oops, something went wrong!');
				});

			};
			$scope.firstPage = function() {
				$scope.pageNumber = 0;

				$http.get('api/obligations', {
					params: {
						"pageNumber": $scope.pageNumber

					}
				}).success(function(data, status) {
					$scope.obligations = data.content;
					$scope.pageNum = data.number + 1;
				}).error(function() {
					alert('Oops, something went wrong!');
				});

			};
			$scope.nextPage = function() {
				if ($scope.pageNumber + 1 < $scope.pageNumMax) {
					$scope.pageNumber = $scope.pageNumber + 1;
				}
				$http.get('api/obligations', {
					params: {
						"pageNumber": $scope.pageNumber

					}
				}).success(function(data, status) {
					$scope.obligations = data.content;
					$scope.pageNum = data.number + 1;
				}).error(function() {
					alert('Oops, something went wrong!');
				});
			};
			$scope.lastPage = function() {
				$scope.pageNumber = $scope.pageNumMax - 1;
				$http.get('api/obligations', {
					params: {
						"pageNumber": $scope.pageNumber

					}
				}).success(function(data, status) {
					$scope.obligations = data.content;
					$scope.pageNum = data.number + 1;
				}).error(function() {
					alert('Oops, something went wrong!');
				});
			};


		}
	]);