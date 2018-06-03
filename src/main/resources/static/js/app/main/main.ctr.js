MainCtr.$inject = ['$scope','$location', 'APISrv'];

function MainCtr($scope, $location,  APISrv, data) {

    var vm = this;
    vm.model = {
        sensors : [],
        searchSensor : ''
    };

    vm.data = data || [];

    init();
    
    function init() {
        console.log('init');
    }
    
    vm.gotoRecord = function (id) {
        $location.path('/sensor/' + id);
    }
}

angular.module('App').controller('MainCtr', MainCtr);