angular.module('eObrazovanjeApp')
	.directive('fileInput',['$parse', function($parse){
		return{
			restrict: 'A',
			link:function(scope,elm,attrs){
				elm.bind('change', function(){
					$parse(attrs.fileInput)
					.assign(scope,elm[0].files)
					scope.$apply()
				})
			}
		}
	}])
	.controller(
		'DocumentController',
		[
			'$rootScope',
			'$scope',
			'$http',
			'$routeParams',
			'$location',
			function($rootScope, $scope, $http, $routeParams,  $location) {
				$scope.getDocument = function(id) {
					$http.get('api/documents/' + id).success(
							function(data, status) {
								$scope.document = data;
							}).error(function() {
						$scope.redAlert = true;
					});
				};

				$scope.getAllDocumentsForUser = function() {
					if ($routeParams && $routeParams.id) {
						// ovo je edit stranica
						$http.get('api/documents/getFor/' + $routeParams.id).success
						(function(data, status) {
							$scope.documents = data;
						}).error(function() {
						alert('Oops, something went wrong!');
					});
					}
					
					$scope.resetFilter = function() {
					}
				};
				
				$scope.getAllDocuments = function() {
					$http.get('api/documents/all').success
						(function(data, status) {
							$scope.documents = data;
					}).error(function() {
						alert('Oops, something went wrong!');
					});
					$scope.resetFilter = function() {
					}
				};

				$scope.deleteDocument = function(id) {
					$http.delete('api/documents/delete/' + id).success(
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

				$scope.initDocument = function() {
					$scope.document = {};

					if ($routeParams && $routeParams.id) {
						// ovo je edit stranica
						$http.get('api/documents/' + $routeParams.id).success(
								function(data) {
									$scope.document = data;
								}).error(function() {
						});
					}
				};

				$scope.saveDocument = function() {
					if ($scope.document.id) {
						// edit stranica
						$http.put('api/documents/edit/' + $scope.document.id,
								$scope.document).success(function() {
							$location.path('/documents');
						}).error(function() {
							alert("neka greska edita");
						});
					} else {
						// add stranica
						$http.post('api/documents/add/', $scope.document).success(
								function() {
									$location.path('/documents/all');
								}).error(function() {
							alert('greska dodavanja!')
						});
					}
				};
				
				$scope.filesChanged = function(element){
					$scope.files = element.files;
					$scope.$apply();
				};
				
				$scope.upload = function(){
					var fd= new FormData();
					angular.forEach($scope.files,function(file){
						fd.append('file', file)
					});
					$http.post('api/documents/uploadAngular', fd,{
						transformRequest:angular.identity,	
						headers:{'Content-Type': undefined}
					})
					.success(function(data){
						$scope.document.path = data;
					});
					
				};
			}
		]
);