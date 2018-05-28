MainCtr.$inject = ['$scope','$location', 'APISrv'];

function MainCtr($scope, $location,  APISrv) {

    var vm = this;
    vm.model = {
        sensors : [],
        searchSensor : ''
    };

    init();
    
    function init() {
        
    }
    
    vm.gotoRecord = function (id) {
        $location.path('/sensor/' + id);
    }
}

angular.module('App').controller(MainCtr);