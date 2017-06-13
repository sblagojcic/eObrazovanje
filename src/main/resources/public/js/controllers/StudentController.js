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
			                	"text": $scope.text,
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
										if ($scope.isAdmin()) {
											window.location ="#/students";
										} else if($scope.isStudent()){
											window.location ="#/subjects/getFor/"+$rootScope.userId;
										}
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
				    
				    $scope.upload = function(){
						var id = $routeParams.id;
						var fd= new FormData();
						angular.forEach($scope.files,function(file){
							fd.append('file', file)
						});
						$http.post('api/documents/profilePic/'+id, fd,{
							transformRequest:angular.identity,	
							headers:{'Content-Type': undefined}
						})
						.success(function(data){
							$scope.student.picturePath = data;
						});
						
					};
				}

 ]);