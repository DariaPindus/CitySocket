SocketSrv.$inject = ['StompClient', '$q'];

function SocketSrv(StompClient, $q){
    var SERVER_URL = "ws://localhost:8181/output";
    var listener = $q.defer();

    var service = {
        getAllSensors : getAllSensors,
        getCurrentSensors : getCurrentSensors,
        getSensor : getSensor,
        getLocations : getLocations,
        changeSensorField : changeSensorField
    };

    return service;

    function connect() { // changed from the sources one, change it possibly
        StompClient.init(SERVER_URL);
        return StompClient.connect();
            /*.then(function (frame) {
            return frame.headers['user-name'];      // ???
        });*/
    }

    function disconnect() {
        StompClient.disconnect();
    }

    function loadData() {
        return StompClient.subscribeSingle("/app/positions");
    }

    function fetchQuoteStream() {
        return StompClient.subscribe("/topic/price.stock.*");
    }

    function fetchPositionUpdateStream() {
        return StompClient.subscribe("/user/queue/position-updates");
    }

    function fetchErrorStream() {
        return StompClient.subscribe("/user/queue/errors");
    }

    function sendActiationMessage(actuation) {
        return StompClient.send("/app/trade", JSON.stringify(tradeOrder), {});
    }
}