StompClient.$inject = ['$q'];

function StompClient($q){
    var stompClient;
    var service = {
        init: function (url) {
            stompClient = webstomp.over(new SockJS(url));
        },
        connect: function () {
            return $q(function (resolve, reject) {
                if (!stompClient) {
                    reject("STOMP client not created");
                } else {
                    stompClient.connect({}, function (frame) {
                        resolve(frame);
                    }, function (error) {
                        reject("STOMP protocol error " + error);
                    });
                }
            });
        },
        disconnect: function() {
            stompClient.disconnect();
        },
        subscribe: function (destination) {
            var deferred = $q.defer();
            if (!stompClient) {
                deferred.reject("STOMP client not created");
            } else {
                stompClient.subscribe(destination, function (message) {
                    deferred.notify(JSON.parse(message.body));
                });
            }
            return deferred.promise;
        },
        subscribeSingle: function (destination) {
            return $q(function (resolve, reject) {
                if (!stompClient) {
                    reject("STOMP client not created");
                } else {
                    stompClient.subscribe(destination, function (message) {
                        resolve(JSON.parse(message.body));
                    });
                }
            });
        },
        send: function (destination, object, headers) {
            stompClient.send(destination, object, headers);
        }
    };
    return service;

}

angular.module('App').factory('StompClient', StompClient);