
function MainCtr($scope, $location,  APISrv, SocketSrv, data) {

    var vm = this;
    vm.model = {
        sensors : [],
        searchSensor : ''
    };

    vm.toggleSearch = false;
    vm.headers = [
        {
            name:'',
            field:'thumb'
        },{
            name: 'Location',
            field: 'location'
        },{
            name:'Type',
            field: 'type'
        },{
            name: 'Value',
            field: 'value'
        }
    ];
    vm.custom = {};
    vm.sortable = ['location'];
    vm.thumbs = 'thumb';
    vm.count = 5;

    $scope.toggleSearch = false;
    $scope.headers = [
        {
            name:'',
            field:'thumb'
        },{
            name: 'Name',
            field: 'name'
        },{
            name:'Description',
            field: 'description'
        },{
            name: 'Last Modified',
            field: 'last_modified'
        }
    ];

    /*
    * expected format : {name:"abc123Sensor","id":1,"location":{"id:22,"label":"Hrecheskaya 11"},"type":"sound", "state":"ok", "value":123}
    */
    vm.data = data || [{name:"abc123Sensor",deviceId:1,location:"Hrecheskaya 11",type:"sound", state:"ok", value:123}];

    init();
    
    function init() {
        console.log('init');
    }
    
    vm.gotoRecord = function (deviceId) {
        $location.path('/sensor/' + deviceId);
    };

    SocketSrv.receive().then(null, null, function(message) {
        message.thumb = stateThumb(message.state);
        var indexInArray = _.findIndex(vm.data, function (a) {return a.deviceId == message.deviceId});
        if (indexInArray > -1)
            vm.data.splice(indexInArray, 1);
        //vm.data.splice(0, 0, message);
        vm.data.unshift(message);
        //vm.data.push(message);
    });

    function stateThumb(state){
        switch (state) {
            case "ok" :
                return "js/app/assets/ok.png";
            case "warning":
                return "js/app/assets/warning.png";
            case "danger":
                return "js/app/assets/danger.png";
            default :
                return "https://cdn1.iconfinder.com/data/icons/rounded-flat-country-flag-collection-1/128/_Unknown.png";
        }
    }

}

var app =angular.module('App');
app.controller('MainCtr', MainCtr);

app.directive('mdTable', function () {
    return {
        restrict: 'E',
        scope: {
            headers: '=',
            content: '=',
            sortable: '=',
            filters: '=',
            customClass: '=customClass',
            thumbs:'=',
            count: '='
        },
        controller: function ($scope,$filter,$window) {
            var orderBy = $filter('orderBy');
            $scope.tablePage = 0;
            $scope.nbOfPages = function () {
                return Math.ceil($scope.content.length / $scope.count);
            },
                $scope.handleSort = function (field) {
                    if ($scope.sortable.indexOf(field) > -1) { return true; } else { return false; }
                };
            $scope.order = function(predicate, reverse) {
                $scope.content = orderBy($scope.content, predicate, reverse);
                $scope.predicate = predicate;
            };
            $scope.order($scope.sortable[0],false);
            $scope.getNumber = function (num) {
                return new Array(num);
            };
            $scope.goToPage = function (page) {
                $scope.tablePage = page;
            };
        },
        template: angular.element(document.querySelector('#md-table-template')).html()
    }
});

//UNCOMMENT BELOW TO BE ABLE TO RESIZE COLUMNS OF THE TABLE
/*
 app.directive('mdColresize', function ($timeout) {
 return {
 restrict: 'A',
 link: function (scope, element, attrs) {
 scope.$evalAsync(function () {
 $timeout(function(){ $(element).colResizable({
 liveDrag: true,
 fixed: true

 });},100);
 });
 }
 }
 });
 */

app.directive('showFocus', function($timeout) {
    return function(scope, element, attrs) {
        scope.$watch(attrs.showFocus,
            function (newValue) {
                $timeout(function() {
                    newValue && element.focus();
                });
            },true);
    };
});

app.filter('startFrom',function (){
    return function (input,start) {
        start = +start;
        return input.slice(start);
    }
});