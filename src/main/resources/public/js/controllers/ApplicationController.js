angular.module('eObrazovanjeApp').controller(
		'ApplicationController',
		[
				'$rootScope',
				'$scope',
				'$http',
				'$routeParams',
				'$location',
				'authService',
				
				function($rootScope, $scope, $http, $routeParams, $location,authService) {
					$scope.isLoggedIn = authService.isLoggedIn;
					
					$scope.isAdmin = authService.isAdmin;
					
					$scope.isProfessor = authService.isProfessor;
					
					$scope.isStudent = authService.isStudent;


					$scope.logout = authService.logout;
				}
		]
);