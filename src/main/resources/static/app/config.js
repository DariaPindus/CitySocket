Config.$inject = ['$stateProvider', '$urlRouterProvider'];

function Config($stateProvider, $urlRouterProvider) {

    $stateProvider
        .state('index', {
            url: '/',
            views: {
                "main": {
                    templateUrl: "/resources/app/main/main.html",
                    controller: 'IndexCtr',
                    controllerAs: 'vm'
                }
            }
        })
        .state('sensor', {
            url: '/sensor/:id',
            views: {
                "main": {
                    templateUrl: "resources/app/editions/edition.html",
                    controller: 'EditionCtr',
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
        })

}

angular.module('App').config(Config);