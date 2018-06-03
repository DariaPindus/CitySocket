
function SensorCtr($scope,  $state, $stateParams, sensor, APISrv) {
    var vm = this;
    vm.sensor = sensor;
    vm.mode = "view"; //"edit"

    //2nd param corresponds field on device entity
    vm.activeChange = function (active) {
        APISrv.changeSensorField(vm.sensor.id, 'isActive', value).then(function (success) {
            if (success.data.success)
                vm.sensor.isActive = active;
        },
        function (error) {
            console.log(error);
        });
    }
}

angular.module('App').controller('SensorCtr', SensorCtr);