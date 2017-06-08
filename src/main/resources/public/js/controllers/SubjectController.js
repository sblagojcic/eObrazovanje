angular.module('eObrazovanjeApp').controller(
		'SubjectController',
		[
				'$rootScope',
				'$scope',
				'$http',
				'$routeParams',
				'$location',
				'authService',
				function($rootScope, $scope, $http, $routeParams, authService, $location) {
					$scope.getUserSubjects = function() {
							$http.get('api/subjects/getFor/' + $routeParams.id).success
							(function(data, status) {
								$scope.subjects = data;
							}).error(function() {
							alert('Oops, something went wrong!');
						});
					};
					$scope.getProfessor = function(id) {
						$http.get('api/subjects/' + id).success(
								function(data, status) {
									$scope.subject = data;

								}).error(function() {
							$scope.redAlert = true;

						});
					};
					$scope.pageNumber = 0;
					
					$scope.getAllSubjects = function() {
						$http.get('api/subjects', {
							params: {
								"pageNumber": $scope.pageNumber

							}
						}).success
							(function(data, status) {
								$scope.subjects = data.content;
								$scope.pageNum = data.number + 1;
				                $scope.pageNumMax = data.totalPages;

						}).error(function() {
							alert('Oops, something went wrong!');
						});

						$scope.resetFilter = function() {

						}
					};
					


					$scope.deleteSubject = function(id) {
						$http.delete('api/subjects/delete/' + id).success(
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

					$scope.initSubject = function() {
						$scope.subject = {};

						if ($routeParams && $routeParams.id) {
							// ovo je edit stranica
							$http.get('api/subjects/' + $routeParams.id).success(
									function(data) {
										$scope.subject = data;
									}).error(function() {

							});
						}
					};

					$scope.saveSubject = function() {
						if ($scope.subject.id) {
							// edit stranica
							$http.put('api/subjects/edit/' + $scope.subject.id,
									$scope.subject).success(function() {
									window.location ="#/subjects";

							}).error(function() {
								alert("Editing error!");
							});
						} else {
							// add stranica
							$http.post('api/subjects/add/', $scope.subject).success(
									function() {
										window.location ="#/subjects";
									}).error(function() {
								alert('Error while adding!')
							});
						}
					};

					// paginacija
					$scope.previousPage = function () {
						if ($scope.pageNumber != 0) {
							$scope.pageNumber = $scope.pageNumber - 1;
						}
						$http.get('api/subjects', {
							params: {
								"pageNumber": $scope.pageNumber

							}
						}).success
							(function(data, status) {
								$scope.subjects = data.content;
								$scope.pageNum = data.number + 1;

						}).error(function() {
							alert('Oops, something went wrong!');
						});

					};
					$scope.firstPage = function () {
						$scope.pageNumber = 0;

						$http.get('api/subjects', {
							params: {
								"pageNumber": $scope.pageNumber

							}
						}).success
							(function(data, status) {
								$scope.subjects = data.content;
								$scope.pageNum = data.number + 1;

						}).error(function() {
							alert('Oops, something went wrong!');
						});

					};
					$scope.nextPage = function () {
						if ($scope.pageNumber + 1 < $scope.pageNumMax) {
							$scope.pageNumber = $scope.pageNumber + 1;
						}
						$http.get('api/subjects', {
							params: {
								"pageNumber": $scope.pageNumber

							}
						}).success
							(function(data, status) {
								$scope.subjects = data.content;
								$scope.pageNum = data.number + 1;

						}).error(function() {
							alert('Oops, something went wrong!');
						});
					};
					$scope.lastPage = function () {
						$scope.pageNumber = $scope.pageNumMax - 1;
						$http.get('api/subjects', {
							params: {
								"pageNumber": $scope.pageNumber

							}
						}).success
							(function(data, status) {
								$scope.subjects = data.content;
								$scope.pageNum = data.number + 1;

						}).error(function() {
							alert('Oops, something went wrong!');
						});
					};
				} ]);