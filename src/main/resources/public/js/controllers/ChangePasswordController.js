angular.module('eObrazovanjeApp').controller(
		'ChangePasswordController',
		[
				'$rootScope',
				'$scope',
				'$http',
				'$routeParams',
				'$location',
				'authService',
				function($rootScope, $scope, $http, $routeParams, authService, $location) {
					$rootScope.userId = localStorage.getItem('userId');
					
					$scope.initPassword = function() {
						$scope.password = {};
						$scope.password.userID = $routeParams.id
					};
					
					$scope.savePassword = function() {
						$scope.password.userID= $rootScope.userId;
						$http.put('api/users/changePassword/', $scope.password).success(
								function() {
									window.location ="#/subjects/getFor/"+$rootScope.userId;
								}).error(function() {
							alert('greska dodavanja!')
						});
						
					};
					
				} ]);