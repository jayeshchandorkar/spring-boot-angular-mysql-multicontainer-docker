'use strict';

angular.module('myApp.gameCenter', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/gamecenter', {
    templateUrl: 'gamecenter/gamecenter.html',
    controller: 'GameCenterCtrl'
  });
}])

.controller('GameCenterCtrl', ['$scope', '$http','modalService', function($scope, $http, modalService) {

  $scope.customerNumber = "";
  $scope.count = "";
  var container = $(window.document).find('html');
  $scope.showRecommendations = function(){

    $scope.error = false;
    $scope.errorMsg = "";
    showLoader();
    $http.get('http://localhost:8080/customers/' + $scope.customerNumber + '/games/recommendations',
    {params:{"count": $scope.count}}
    ).then(function successCallback(response) {
      hideLoader();
        console.log(response);
        console.log(container);
        if(response.status == 200){
          if(response.data && response.data.length > 0){
            $scope.slides = response.data;
            modalService.showModal($scope, container);
          }
        }

      }, function errorCallback(response) {
        hideLoader();
        if(response.status == 404){
          $scope.error = true;
          $scope.errorMsg = "Customer not found with Customer Number [" + $scope.customerNumber +"]";
        }else{
          $scope.error = true;
          $scope.errorMsg = "Invalid Request";          
        }
      });

  };
}]);