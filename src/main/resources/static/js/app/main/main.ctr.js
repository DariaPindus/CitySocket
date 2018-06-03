
function MainCtr($scope, $location,  APISrv, SocketSrv, data) {

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

    SocketSrv.receive().then(null, null, function(message) {
        vm.data.push(message);
    });
}

angular.module('App').controller('MainCtr', MainCtr);