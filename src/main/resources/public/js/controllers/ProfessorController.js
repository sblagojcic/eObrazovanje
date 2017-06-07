angular.module('eObrazovanjeApp').controller(
	'ProfessorController', [
		'$rootScope',
		'$scope',
		'$http',
		'$routeParams',
		'$location',
		function ($rootScope, $scope, $http, $routeParams, $location) {
			$scope.getProfessor = function (id) {
				$http.get('api/professors/' + id).success(
					function (data, status) {
						$scope.professor = data;

					}).error(function () {
					$scope.redAlert = true;

				});
			};
			$scope.pageNumber = 0;

			$scope.getAllProfessors = function () {
				$http.get('api/professors', {
						params: {
							"pageNumber": $scope.pageNumber

						}
					})
					.success(function (data, status) {
						$scope.professors = data.content;
						$scope.pageNum = data.number + 1;
					}).error(function () {
						alert('Oops, something went wrong!');
					});

			};



			$scope.deleteProfessor = function (id) {
				$http.delete('api/professors/delete/' + id).success(
					function (data, status) {
						$scope.deleted = data;
						$scope.blueAlert = true;
						$scope.getAllProfessors();

					}).error(function () {
					$scope.redAlert = true;

				});
			};

			$scope.hideAlerts = function () {
				$scope.redAlert = false;
				$scope.blueAlert = false;
				$scope.orangeAlert = false;
			};

			$scope.initProfessor = function () {
				$scope.professor = {};

				if ($routeParams && $routeParams.id) {
					// ovo je edit stranica
					$http.get('api/professors/' + $routeParams.id).success(
						function (data) {
							$scope.professor = data;
						}).error(function () {

					});
				}
			};

			$scope.saveProfessor = function () {
				if ($scope.professor.id) {
					// edit stranica
					$http.put('api/professors/edit/' + $scope.professor.id,
						$scope.professor).success(function () {
						$location.path('/professors/all');
					}).error(function () {
						alert("neka greska edita");
					});
				} else {
					// add stranica
					$http.post('api/professors/add/', $scope.professor).success(
						function () {
							$location.path('/professors/all');
						}).error(function () {
						alert('greska dodavanja!')
					});
				}
			};
			// paginacija
			$scope.previousPage = function () {
				if ($scope.pageNumber != 0) {
					$scope.pageNumber = $scope.pageNumber - 1;
				}
				$http.get('api/professors', {
						params: {
							"pageNumber": $scope.pageNumber

						}
					})
					.success(function (data, status) {
						$scope.professors = data.content;
						$scope.pageNum = data.number + 1;
					}).error(function () {
						alert('Oops, something went wrong!');
					});

			};
			$scope.firstPage = function () {
				$scope.pageNumber = 0;

				$http.get('api/professors', {
						params: {
							"pageNumber": $scope.pageNumber

						}
					})
					.success(function (data, status) {
						$scope.professors = data.content;
						$scope.pageNum = data.number + 1;
					}).error(function () {
						alert('Oops, something went wrong!');
					});

			};
			$scope.nextPage = function () {
				if ($scope.pageNumber + 1 < $scope.pageNumMax) {
					$scope.pageNumber = $scope.pageNumber + 1;
				}
				$http.get('api/professors', {
						params: {
							"pageNumber": $scope.pageNumber

						}
					})
					.success(function (data, status) {
						$scope.professors = data.content;
						$scope.pageNum = data.number + 1;
					}).error(function () {
						alert('Oops, something went wrong!');
					});
			};
			$scope.lastPage = function () {
				$scope.pageNumber = $scope.pageNumMax - 1;
				$http.get('api/professors', {
						params: {
							"pageNumber": $scope.pageNumber
						}
					})
					.success(function (data, status) {
						$scope.professors = data.content;
						$scope.pageNum = data.number + 1;
					}).error(function () {
						alert('Oops, something went wrong!');
					});

			};
		}
	]);