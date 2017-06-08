'use strict';

angular.module('eObrazovanjeApp').service('authService', ['$http', 'jwtHelper', '$rootScope', '$log',
    function ($http, jwtHelper, $rootScope, $log) {

        var service = {};

        service.logout = function () {
            $http.defaults.headers.common.Authorization = '';
            localStorage.removeItem('jwt_token');
            $rootScope.loggedUserData = null;
            window.location = "/#/login";
        };

        service.isLoggedIn = function () {
            var token = localStorage.getItem('jwt_token');
            if (token == null) {
                return false;
            }
            var isTokenExpired = jwtHelper.isTokenExpired(token);
            if (isTokenExpired) {
                $log.info('Token expired.');
                return false;
            }
            var payload = jwtHelper.decodeToken(token);
            if(($http.defaults.headers.common.Authorization == '') || (typeof $http.defaults.headers.common.Authorization == "undefined") || ($http.defaults.headers.common.Authorization == null)){
            	$http.defaults.headers.common.Authorization = "Bearer " + token;
            }
            var payload = jwtHelper.decodeToken(token);
            $rootScope.roles = payload.authorities;
            $rootScope.username = payload.user_name;
            return true;
        };
        
        service.isAdmin = function(){
        	if(!service.isLoggedIn()){
        		return false;
        	}
        	var token = localStorage.getItem('jwt_token');
        	var payload = jwtHelper.decodeToken(token);
        	var roles = payload.authorities;
			if(roles[0] == "ROLE_ADMIN"){
				return true;	
			}
    				
        	return false;
        }
        
        service.isProfessor = function(){
        	if(!service.isLoggedIn()){
        		return false;
        	}
        	var token = localStorage.getItem('jwt_token');
        	var payload = jwtHelper.decodeToken(token);
        	var roles = payload.authorities;
			if(roles[0] == "ROLE_PROFESSOR"){
				return true;	
			}
    				
        	return false;
        }
        
        service.isStudent = function(){
        	if(!service.isLoggedIn()){
        		return false;
        	}
        	var token = localStorage.getItem('jwt_token');
        	var payload = jwtHelper.decodeToken(token);
        	var roles = payload.authorities;
			if(roles[0] == "ROLE_STUDENT"){
				return true;	
			}
    				
        	return false;
        }
     

        return service;

    }
]);