SocketSrv.$inject = ['StompClient', '$q', '$timeout'];

function SocketSrv(StompClient, $q,  $timeout){
    var SERVER_URL = "ws://localhost:8181/output";
    var  listener = $q.defer(),
        socket = {
            client: null,
            stomp: null
        },
        messageIds = [];

    var service = {
        RECONNECT_TIMEOUT : 30000,
        SOCKET_URL : "ws://localhost:8181/output",  //"/spring-ng-chat/chat", ws://localhost:8181
        INCOMING_URL : "/city/display",

        initialize : initialize,
        receive : receive,
        send : send,
        reconnect : reconnect,
        startListener : startListener
    };


    function receive () {
        return listener.promise;
    }

    function send (message) {
        var id = Math.floor(Math.random() * 1000000);
        socket.stomp.send(service.CHAT_BROKER, {
            priority: 9
        }, JSON.stringify({
            message: message,
            id: id
        }));
        messageIds.push(id);
    }

    /*
    * When the connection to the Websocket server is lost, it will call the reconnect() function
    * which will attempt to initialize the connection again after 30 seconds.
    */
    function reconnect () {
        $timeout(function() {
            initialize();
        }, this.RECONNECT_TIMEOUT);
    }

    function startListener () {
        socket.stomp.subscribe(service.INCOMING_URL, function(data) {
            listener.notify(JSON.parse(data.body));
        });
    }

    function initialize() {
        socket.stomp = Stomp.client(service.SOCKET_URL);//Stomp.over(socket.client);
        socket.stomp.connect({}, startListener);
        socket.stomp.onclose = reconnect;
    }

    initialize();
    return service;
}

angular.module('App').service('SocketSrv', SocketSrv);