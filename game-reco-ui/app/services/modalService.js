angular.module('myApp.gameCenter').factory('modalService', ['$compile', function($compile){

    return {
        showModal: function(scope, container){
            container.find('reco-modal').remove();
            container.append($('<reco-modal></reco-modal>'));
             $compile($('reco-modal'))(scope);
        }
    }
}]);