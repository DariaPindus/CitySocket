Config.$inject = ['$stateProvider', '$urlRouterProvider'];

function Config($stateProvider, $urlRouterProvider) {

    $stateProvider
        .state('index', {
            url: '/',
            views: {
                "main": {
                    templateUrl: "js/app/main/main.html",
                    controller: 'MainCtr',
                    controllerAs: 'vm'
                }
            },
            resolve : {
                data : function(APISrv) {
                    return APISrv.getOldData().then(function (response) {
                        if (response.data.success){
                            return response.data.content;
                        }
                    }, 
                    function (error) {
                        
                    });
                }
            }
        })
        .state('sensor', {
            url: '/sensor/:id',
            views: {
                "main": {
                    templateUrl: "js/app/sensor/sensor.html",
                    controller: 'SensorCtr',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                sensor: function (APISrv, $stateParams) {
                    console.log('try to resolve', $stateParams.id);
                    return APISrv.getSensor($stateParams.id).then(function (success) {
                        console.log('try to resolve');
                        return success.data;
                    }, function (error) {
                        console.log(error);
                    })
                }
            }
        });

    $urlRouterProvider.otherwise("/");

}

angular.module('App').config(Config);