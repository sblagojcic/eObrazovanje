angular.module('eObrazovanjeApp').controller(
		'PictureController',
		[
				'$rootScope',
				'$scope',
				'$http',
				'$routeParams',
				'$location',
				'authService',
				function($rootScope, $scope, $http, $routeParams, authService, $location) {
					$rootScope.userId = localStorage.getItem('userId');
					var id = $routeParams.id;
				    	
				    	$http.get("api/documents/downloadPicture/" + id)
				        .then( function(response){
				        	alert(response.data);
				        	$scope.availableImages[0].src = response.data;
				        	})
				    
				    $scope.availableImages = [
				    	{
				    	  src: "http://upload.wikimedia.org/wikipedia/commons/thumb/8/80/US_1.svg/50px-US_1.svg.png"
				    	}
				    	];
				} ]);