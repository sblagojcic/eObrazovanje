angular.module('eObrazovanjeApp').controller(
		'TransactionController',
		[
				'$rootScope',
				'$scope',
				'$http',
				'$routeParams',
				'$location',
				function($rootScope, $scope, $http, $routeParams,  $location) {
					$scope.getUserTransactions = function() {
						$http.get('api/transactions/getFor/' + $routeParams.id).success
						(function(data, status) {
							$scope.transactions = data;
						}).error(function() {
						alert('Oops, something went wrong!');
					});
				};
					$scope.getAllTransactions = function() {
						$http.get('api/transactions/all').success
							(function(data, status) {
								$scope.transactions = data;

						}).error(function() {
							alert('Oops, something went wrong!');
						});

						$scope.resetFilter = function() {

						}
					};
					

				} ]);