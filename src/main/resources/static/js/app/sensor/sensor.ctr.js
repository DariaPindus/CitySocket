
function SensorCtr($scope,  $state, $stateParams, SocketSrv, APISrv, sensor) {
    var vm = this;
    vm.id = $stateParams.id;
    vm.sensor = sensor;
    vm.mode = "view"; //"edit"
    vm.data = [];

    init();
    
    function init() {
        APISrv.getOldDataById(vm.id).then(function (success) {
            if (success.data){
                vm.data = success.data;
            }
        }, function (error) {
            console.log(error);
        })
    }

    //2nd param corresponds field on device entity
    vm.activeChange = function (active) {
        var updateRequestList = [{fieldName : 'id', value : vm.id}, {fieldName : isActive, value : active}];
        APISrv.changeSensorField(vm.id, updateRequestList).then(function (success) {
            if (success.data)
                vm.sensor.isActive = active;
        },
        function (error) {
            console.log(error);
        });
    };

    SocketSrv.receive().then(null, null, function(message) {
        if (message.deviceId == vm.id){
            vm.data.push(message);
        }
    });

    vm.doActionsModal  = function(ev) {
        $mdDialog.show({
            controller: DialogController,
            templateUrl: 'dialog1.tmpl.html',
            parent: angular.element(document.body),
            targetEvent: ev,
            clickOutsideToClose:true,
            //fullscreen: $scope.customFullscreen // Only for -xs, -sm breakpoints.
        })
            .then(function(answer) {
                vm.status = 'You said the information was "' + answer + '".';
            }, function() {
                //$scope.status = 'You cancelled the dialog.';
            });
    };

    function DialogController($scope, $mdDialog) {
        $scope.hide = function() {
            $mdDialog.hide();
        };

        $scope.cancel = function() {
            $mdDialog.cancel();
        };

        $scope.callPolice = function() {
            $mdDialog.hide('Nearest police department will be notified');
        };

        $scope.callPolice = function() {
            $mdDialog.hide('Nearest sirens will be turned on');
        };

    }

}

angular.module('App').controller('SensorCtr', SensorCtr);