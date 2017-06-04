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
			}).when('/students', {
				templateUrl : '/html/Students.html',
				controller : 'StudentController'
			}).when('/addOrUpdateStudent', {
				templateUrl : '/html/addOrUpdateStudent.html',
				controller : 'StudentController'
			}).when('/Students/edit/:id', {
				templateUrl : '/html/addOrUpdateStudent.html',
				controller : 'StudentController'
			}).when('/transactions', {
				templateUrl : '/html/Transactions.html',
				controller : 'TransactionController'
			}).when('/documents', {
				templateUrl : '/html/Documents.html',
				controller : 'DocumentController'
			}).when('/documents/edit/:id', {
				templateUrl : '/html/addOrUpdateDocument.html',
				controller : 'DocumentController'
			}).when('/addOrUpdateDocument', {
				templateUrl : '/html/addOrUpdateDocument.html',
				controller : 'DocumentController'
			}).when('/exams', {
				templateUrl : '/html/Exams.html',
				controller : 'ExamController'
			}).when('/exams/edit/:id', {
				templateUrl : '/html/addOrUpdateExam.html',
				controller : 'ExamController'
			}).when('/addOrUpdateExam', {
				templateUrl : '/html/addOrUpdateExam.html',
				controller : 'ExamController'
			}).otherwise({
				redirectTo : '/'
			});
		} ]);

