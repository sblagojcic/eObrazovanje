angular.module('eObrazovanjeApp').controller(
		'ExamController',
		[
			'$rootScope',
			'$scope',
			'$http',
			'$routeParams',
			'$location',
			'authService',
			function($rootScope, $scope, $http, $routeParams, authService,  $location) {
				$rootScope.userId = localStorage.getItem('userId');
				$scope.getExam = function(id) {
					$http.get('api/exams/' + id).success(
							function(data, status) {
								$scope.exam = data;
							}).error(function() {
						$scope.redAlert = true;
					});
				};
				$scope.getAllStudents = function() {
					$http.get('api/students/inSubject/'+$rootScope.subjectId).success
						(function(data, status) {
							$scope.students= data;
						
					}).error(function() {
						alert('Oops, something went wrong!');
					});

					$scope.resetFilter = function() {

					}
				};
				$scope.getAllExamsForUser = function() {
					if ($routeParams && $routeParams.id) {
						$http.get('api/exams/getFor/' + $routeParams.id).success
						(function(data, status) {
							$scope.exams = data;
					}).error(function() {
						alert('Oops, something went wrong!');
					});
					}
					
					$scope.resetFilter = function() {
					}
				};
				
				$scope.getAllExams = function() {
					$http.get('api/exams/all').success
						(function(data, status) {
							$scope.exams = data;
					}).error(function() {
						alert('Oops, something went wrong!');
					});
					$scope.resetFilter = function() {
					}
				};

				$scope.deleteExam = function(id) {
					$http.delete('api/exams/delete/' + id).success(
							function(data, status) {
								$scope.deleted = data;
								$scope.blueAlert = true;
								$scope.getAllDocuments();

							}).error(function() {
						$scope.redAlert = true;
					});
				};

				$scope.hideAlerts = function() {
					$scope.redAlert = false;
					$scope.blueAlert = false;
					$scope.orangeAlert = false;
				};

				$scope.initExam = function() {
					$scope.exam = {};

					if ($routeParams && $routeParams.id) {
						// ovo je edit stranica
						$http.get('api/exams/' + $routeParams.id).success(
								function(data) {
									$scope.exam = data;
								}).error(function() {
						});
					}
				};

				$scope.saveExam = function() {
					if ($scope.exam.id) {
						// edit stranica
						$http.put('api/exams/edit/' + $scope.exam.id,
								$scope.exam).success(function() {
									window.location ="#/subjects/getFor/"+$rootScope.userId;
						}).error(function() {
							alert("neka greska edita");
						});
					} else {
						// add stranica
						$scope.exam.subjectName=$rootScope.subjectId;
						$http.post('api/exams/add/', $scope.exam).success(
								function() {
									window.location ="#/subjects/getFor/"+$rootScope.userId;
								}).error(function() {
							alert('greska dodavanja!')
						});
					}
				};
			}
		]
);