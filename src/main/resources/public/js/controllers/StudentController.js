angular.module('eObrazovanjeApp').controller(
		'StudentController',
		[
				'$rootScope',
				'$scope',
				'$http',
				'$routeParams',
				'$location',
				function($rootScope, $scope, $http, $routeParams,  $location) {
					$scope.getStudent = function(id) {
						$http.get('api/students/' + id).success(
								function(data, status) {
									$scope.student = data;

								}).error(function() {
							$scope.redAlert = true;

						});
					};

					$scope.getAllStudents = function() {
						$http.get('api/students/all').success
							(function(data, status) {
								$scope.students = data;
								
						}).error(function() {
							alert('Oops, something went wrong!');
						});

						$scope.resetFilter = function() {

						}
					};
					
					$scope.getAllDocumentsForUser = function(id) {
						$http.get('api/documents/getFor/' + id).success
							(function(data, status) {
								$scope.documents = data;
								$location.path('/documents/getFor/'+id);
						}).error(function() {
							alert('Oops, something went wrong!');
						});
						$scope.resetFilter = function() {
						}
					};

					$scope.deleteStudent = function(id) {
						$http.delete('api/students/delete/' + id).success(
								function(data, status) {
									$scope.deleted = data;
									$scope.blueAlert = true;
									$scope.getAllStudents();

								}).error(function() {
							$scope.redAlert = true;

						});
					};

					$scope.hideAlerts = function() {
						$scope.redAlert = false;
						$scope.blueAlert = false;
						$scope.orangeAlert = false;
					};

					$scope.initStudent= function() {
						$scope.student = {};

						if ($routeParams && $routeParams.id) {
							$http.get('api/students/' + $routeParams.id).success(
									function(data) {
										$scope.student = data;
									}).error(function() {

							});
						}
					};

					$scope.saveStudent = function() {
						if ($scope.student.id) {
							// edit stranica
							$http.put('api/students/edit/' + $scope.student.id,
									$scope.student).success(function() {
								$location.path('/students/all');
							}).error(function() {
								alert("neka greska edita");
							});
						} else {
							// add stranica
							$http.post('api/students/add/', $scope.student).success(
									function() {
										$location.path('/students/all');
									}).error(function() {
								alert('greska dodavanja!')
							});
						}
					};

				} ]);