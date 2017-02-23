'use strict';

angular.module('myApp.gameCenter', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/gamecenter', {
    templateUrl: 'gamecenter/gamecenter.html',
    controller: 'GameCenterCtrl'
  });
}])

.controller('GameCenterCtrl', ['$scope', function($scope) {

  $scope.showRecommendations = function(){
    $('#recommendationModal').modal({});
  };
}]);