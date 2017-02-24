'use strict';

angular.module('myApp.admin', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/admin', {
    templateUrl: 'admin/admin.html',
    controller: 'AdminCtrl'
  });
}])

.controller('AdminCtrl', ['$scope', '$http', function($scope, $http) {

  $scope.uploadFile = function(){

    $scope.error = false;
    $scope.errorMsg = "";
    $scope.success = false;
    $scope.successMsg = "";

    showLoader();
    $http({
                method: "POST",
                url: "http://localhost:8080/customers/1111/games/recommendations",
                headers: { 'Content-Type': undefined },
                //This method will allow us to change how the data is sent up to the server
                // for which we'll need to encapsulate the model data in 'FormData'
                transformRequest: function (data) {
                    var formData = new FormData();
                    if($scope.uploadedFile  && $scope.uploadedFile.length > 0){
                        formData.append("file", $scope.uploadedFile[0]);
                    }
                    return formData;
                },
                //Create an object that contains the model and files which will be transformed
                // in the above transformRequest method
                data: event
            }).
            success(function (data, status, headers, config) {
              hideLoader();
              $scope.success = true;
              $scope.successMsg = "File uploaded successfully";
            }).
            error(function (data, status, headers, config) {
              hideLoader();
              console.log("File upload failed");
              $scope.error = true;
              $scope.errorMsg = "File upload failed";
            });

  } 

}]);