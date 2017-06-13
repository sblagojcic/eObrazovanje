'use stirict';
angular.module('eObrazovanjeApp', [ 'angular-jwt' ,'ngRoute']).config(
		[ '$routeProvider', function($routeProvider) {
			$routeProvider.when('/login', {
				templateUrl : '/html/login.html',
				controller : 'LoginController'
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
			}).when('/students/getStudentsInSubject/:id', {
				templateUrl : '/html/studentsInSubject.html',
				controller : 'SubjectController'
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
			}).when('/addOrUpdateTransaction', {
				templateUrl : '/html/addOrUpdateTransaction.html',
				controller : 'TransactionController'
			}).when('/documents', {
				templateUrl : '/html/Documents.html',
				controller : 'DocumentController'
			}).when('/documents/edit/:id', {
				templateUrl : '/html/addOrUpdateDocument.html',
				controller : 'DocumentController'
			}).when('/Students/profileOfStudent/:id', {
				templateUrl : '/html/profileOfStudent.html',
				controller : 'StudentController'
			}).when('/professors/profileOfProfesors/:id', {
				templateUrl : '/html/profileOfProfesors.html',
				controller : 'ProfessorController'
			}).when('/professors/profileOfProfesors/:id', {
				templateUrl : '/html/profileOfProfesors.html',
				controller : 'ProfessorController'
			}).when('/addOrUpdateDocument', {
				templateUrl : '/html/addOrUpdateDocument.html',
				controller : 'DocumentController'
			}).when('/documents/getFor/:id', {
				templateUrl : '/html/allDocumetsForUser.html',
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
			}).when('/exams/getFor/:id', {
				templateUrl : '/html/allExamsForUser.html',
				controller : 'ExamController'
			}).when('/subjects', {
				templateUrl : '/html/subjects.html',
				controller : 'SubjectController'
			}).when('/addOrUpdateSubject', {
				templateUrl : '/html/addOrUpdateSubject.html',
				controller : 'SubjectController'
			}).when('/subjects/edit/:id', {
				templateUrl : '/html/addOrUpdateSubject.html',
				controller : 'SubjectController'
			}).when('/obligations', {
				templateUrl : '/html/obligations.html',
				controller : 'ObligationController'
			}).when('/addOrUpdateObligation', {
				templateUrl : '/html/addOrUpdateObligation.html',
				controller : 'ObligationController'
			}).when('/obligations/edit/:id', {
				templateUrl : '/html/addOrUpdateObligation.html',
				controller : 'ObligationController'
			}).when('/subjects/getFor/:id', {
				templateUrl : '/html/UserSubjects.html',
				controller : 'SubjectController'
			}).when('/obligations/getFor/:id', {
				templateUrl : '/html/UserObligations.html',
				controller : 'ObligationController'
			}).when('/transactions/getFor/:id', {
				templateUrl : '/html/UserTransactions.html',
				controller : 'TransactionController'
			}).when('/subjects/:id/addStudents', {
				templateUrl : '/html/addStudentToSubject.html',
				controller : 'SubjectController'
			}).when('/changePassword/:id', {
				templateUrl : '/html/changePassword.html',
				controller : 'ChangePasswordController'
			}).otherwise({
				redirectTo : '/login'
			});
		} ]);

