'use stirict';
angular.module('eObrazovanjeApp', [ 'ngRoute']).config(
		[ '$routeProvider', function($routeProvider) {
			$routeProvider.when('/', {
		        templateUrl: '/html/professors.html',
		        controller: 'ProfessorController',
		    }).when('/professors', {
				templateUrl : '/html/professors.html',
				controller : 'ProfessorController'
			}).when('/professorRoles', {
				templateUrl : '/html/professorRoles.html',
				controller : 'ProfessorRoleController'
			}).when('/addOrUpdateProfessor', {
				templateUrl : '/html/addOrUpdateProfessor.html',
				controller : 'ProfessorController'
			}).when('/professors/edit/:id', {
				templateUrl : '/html/addOrUpdateProfessor.html',
				controller : 'ProfessorController'
			}).when('/addOrUpdateProfessorRole', {
				templateUrl : '/html/addOrUpdateProfessorRole.html',
				controller : 'ProfessorRoleController'
			}).when('/professorRoles/edit/:id', {
				templateUrl : '/html/addOrUpdateProfessorRole.html',
				controller : 'ProfessorRoleController'
			}).otherwise({
				redirectTo : '/'
			});
		} ]);

