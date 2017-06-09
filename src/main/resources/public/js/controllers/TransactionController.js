angular.module('eObrazovanjeApp').controller(
		'TransactionController',
		[
				'$rootScope',
				'$scope',
				'$http',
				'$routeParams',
				'$location',
				'authService',
				function($rootScope, $scope, $http, $routeParams, authService,  $location) {
					$rootScope.userId = localStorage.getItem('userId');
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

					};
					

					$scope.saveTransaction = function() {
							$scope.transaction.studentDTO={
									"id": $rootScope.userId
									
							}
							// add stranica
							$http.post('api/transactions/add/', $scope.transaction).success(
									function() {
										window.location ="#/transactions/getFor/"+$rootScope.userId;
									}).error(function() {
								alert('Error while adding!')
							});
					
					};
					

				} ]);