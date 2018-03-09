
$(function() {
    'use strict';

    var client;

    function showMessage(mesg)
    {   /* response object */
        $('#data').append('<tr>' +
            '<td>' + mesg.from + '</td>' +
            '<td>' + mesg.topic + '</td>' +
            '<td>' + mesg.message + '</td>' +
            '<td>' + mesg.time + '</td>' +
            '</tr>');
    }

    function setConnected(connected) {
        $("#connect").prop("disabled", connected);
        $("#disconnect").prop("disabled", !connected);
        $('#from').prop('disabled', connected);
        $('#value').prop('disabled', !connected);
        if (connected) {
            $("#conversation").show();
            $('#value').focus();
        }
        else $("#conversation").hide();
        $("#data").html("");
    }

    $("form").on('submit', function (e) {
        e.preventDefault();
    });

    $('#from').on('blur change keyup', function(ev) {
        $('#connect').prop('disabled', $(this).val().length == 0 );
    });
    $('#connect,#disconnect,#value').prop('disabled', true);

    $('#connect').click(function() {
        client = Stomp.over(new SockJS('/city'));
        client.connect({}, function (frame) {
            setConnected(true);
            client.subscribe('/output', function (message) {
                showMessage(JSON.parse(message.body));
            });
        });
    });

    $('#disconnect').click(function() {
        if (client != null) {
            client.disconnect();
            setConnected(false);
        }
        client = null;
    });

    $('#send').click(function() {
        var sensorName = $('#sensorName').val();
        client.send("/app/city/" + sensorName+".alter", {}, JSON.stringify({
            from: $("#from").val(),
            text: $('#value').val(),
        }));
        $('#value').val("");
    });
});