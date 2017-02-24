'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
  'ngRoute',
  'myApp.gameCenter',
  'myApp.admin',
  'myApp.version'
]).
config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
  $locationProvider.hashPrefix('!');

  $routeProvider.otherwise({redirectTo: '/admin'});
}]);

var showLoader = function(){
  $('.loader').show();
}

var hideLoader = function(){
  $('.loader').hide();
}