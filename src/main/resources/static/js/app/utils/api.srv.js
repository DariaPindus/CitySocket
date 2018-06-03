APISrv.$inject = ['$http'];

function APISrv($http) {

    var server_uri = "/api";
    var service = {
        getAllSensors : getAllSensors,
        getOldData : getOldData,
        getOldDataById : getOldDataById,
        getCurrentSensors : getCurrentSensors,
        getSensor : getSensor,
        getLocations : getLocations,
        changeSensorField : changeSensorField
    };

    return service;

    /* response type :
     * [{"name":"abc123", "location":{"longitude":42.3243242, "latitude":..., "label":"Pushkinskaya"}, "type":"sound"}]
     */
    function getAllSensors() {
        return $http.get(server_uri + '/sensors');
    }

    function getOldData() {
        return $http.get(server_uri + '/olddata');
    }

    function getOldDataById(deviceId) {
        return $http.get(server_uri + '/olddata?id=' + deviceId);
    }
    /* possible states correspond the name of assets : ok, warning and danger (in order of urgency raise)
     * response type :
     * [{"state":"ok","id":1,"name":"abc123", "location":{"longitude":42.3243242, "latitude":..., "label":"Pushkinskaya"}, "type":"sound"}]
     */
    function getCurrentSensors() {
        return $http.get(server_uri + '/sensors/current');
    }

    /*
    * response type:
    * {"type":"sound", "id":1, "name":"abc123","active":true}
    */
    function getSensor(id) {
        return $http.get(server_uri + '/sensors/' + id);
    }

    function getLocations() {
        return $http.get(server_uri + '/locations');
    }

    function changeSensorField(id, field, value) {
        //return $http.get(server_uri + '/sensor/' + id + '?' + field + '=' + value);
        return  $http.post(server_uri + '/' + id + '/update', updateList);
    }
    
    function updateFields(updateList) {
        return $http.post(server_uri + '/' + id + '/update', updateList);
    }
}

angular.module('App').service('APISrv', APISrv);