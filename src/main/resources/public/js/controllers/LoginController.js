
'use strict';

angular.module('eObrazovanjeApp').controller('LoginController', ['$rootScope', '$http', '$scope', '$log',
    function ($rootScope, $http, $scope, $log ) {
	
	$scope.credentials = {};
	
	$scope.message = "";
	
	$scope.login = function() {
		var param = "Basic " + btoa("trusted-app:secret");
		var data = { "username": $scope.credentials.username, "password": $scope.credentials.password, "grant_type" : "password"};
		var config = { headers: { "Authorization": param }};
		/*
		$http.post('oauth/token', data, config).then(function(response){
			concole.log(response);
		});*/
		
		$.ajax({
			type: 'POST',
			url: 'http://localhost:8080/oauth/token',	
			headers: {"Authorization": param },
			data: data,
			success: function(response){ 
				var base64Url = response.access_token.split('.')[1];
				var base64 = base64Url.replace('-', '+').replace('_', '/');
				
				var token = "Bearer " + response.access_token;
				$http.defaults.headers.common.Authorization = token;
				localStorage.setItem('jwt_token', response.access_token);
				$http.get('api/users/user/' + $scope.credentials.username).success(function(data, status) {
					$scope.user = data;
					localStorage.setItem('userId', $scope.user.id);
					$rootScope.userId = localStorage.getItem('userId');
					if ($scope.user.role=='STUDENT') {
						window.location = "/#/subjects/getFor/"+$scope.user.id;
					}
					else if ($scope.user.role=='PROFESSOR') {
						window.location = "/#/subjects/getFor/"+$scope.user.id;
					}
					else{
						window.location = "/#/subjects"
					}
				}).error(function() {
				alert("greska")

			});
		
				
				
			},
			error: function(response){ 
				$scope.message = "Bad credentials!";
				console.log("Bad credentials!");
			} 
		})
			
	};

}]);