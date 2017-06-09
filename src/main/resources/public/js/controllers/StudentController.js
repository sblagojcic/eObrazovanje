angular.module('eObrazovanjeApp').controller(
		'StudentController',
		[
				'$rootScope',
				'$scope',
				'$http',
				'$routeParams',
				'$location',
				'authService',
				function($rootScope, $scope, $http, $routeParams, authService, $location) {
					$rootScope.userId = localStorage.getItem('userId');
					$scope.getStudent = function(id) {
						$http.get('api/students/all' + id).success(
								function(data, status) {
									$scope.student.content = data;

								}).error(function() {
							$scope.redAlert = true;

						});
					};

					$scope.pageNumber = 0;

					$scope.getAllStudents = function() {
						$http.get('api/students', {
			                params: {
			                    "pageNumber":$scope.pageNumber
			                    
			                }
			            }).success
							(function(data, status) {
								$scope.students= data.content;
								$scope.pageNum = data.number + 1;
				                $scope.pageNumMax = data.totalPages;
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
										window.location ="#/students";
							}).error(function() {
								alert("neka greska edita");
							});
						} else {
							// add stranica
							$http.post('api/students/add/', $scope.student).success(
									function() {
										window.location ="#/students";
									}).error(function() {
								alert('greska dodavanja!')
							});
						}
					};
					//paginacija
					$scope.previousPage = function() {
				    	if ($scope.pageNumber!=0) {
				    		$scope.pageNumber = $scope.pageNumber-1;
						}
				        $http.get('api/students', {
				                params: {
				                    "pageNumber":$scope.pageNumber
				                }
				            })
				            .success(function(data, status) {
				                $scope.students = data.content;
				                $scope.pageNum = data.number + 1;
				                
				            })
				            .error(function() {
				                alert('Oops, something went wrong!');
				            });

				    };
				    $scope.firstPage = function() {
				    	$scope.pageNumber = 0;
				        $http.get('api/students', {
				                params: {"pageNumber":$scope.pageNumber
				                }
				            })
				            .success(function(data, status) {
				                $scope.students = data.content;
				                $scope.pageNum = data.number + 1;
				            })
				            .error(function() {
				                alert('Oops, something went wrong!');
				            });

				    };
				    $scope.nextPage = function() {
				    	if ($scope.pageNumber+1<$scope.pageNumMax) {
				    		$scope.pageNumber = $scope.pageNumber+1;
						}
				        $http.get('api/students', {
				                params: {
				                    "pageNumber":$scope.pageNumber
				                }
				            })
				            .success(function(data, status) {
				                $scope.students = data.content;
				                $scope.pageNum = data.number + 1;
				            })
				            .error(function() {
				                alert('Oops, something went wrong!');
				            });
				    };
				    $scope.lastPage = function() {
				    	$scope.pageNumber = $scope.pageNumMax-1;
				        $http.get('api/students', {
			                params: {
			                    "pageNumber":$scope.pageNumber
			                }
			            })
			            .success(function(data, status) {
			                $scope.students = data.content;
			                $scope.pageNum = data.number + 1;
			            })
				            .error(function() {
				                alert('Oops, something went wrong!');
				            });
				    };
				    
				    $scope.profilePicture = function() {
				    	if ($routeParams && $routeParams.id) {
				    		$http.get('api/documents/downloadPicture/' + $routeParams.id)
				    		.success(function(data, status) {
								$scope.documents = data;
							}).error(function() {
								alert('Oops, something went wrong!');
							});
						}
					}
				    
				    $scope.availableImages = [
				    	{
				    	  src: "http://upload.wikimedia.org/wikipedia/commons/thumb/8/80/US_1.svg/50px-US_1.svg.png"
				    	}
				    	];
				} ]);