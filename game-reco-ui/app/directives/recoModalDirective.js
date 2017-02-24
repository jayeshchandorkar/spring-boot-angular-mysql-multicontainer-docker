angular.module('myApp.gameCenter').directive('recoModal', function(){

    return {
        restrict: 'EC',
        templateUrl: 'templates/recoModalTemplate.html',
        link: function(scope, element, attrs){
            element.children().modal({});
        }
    }
});