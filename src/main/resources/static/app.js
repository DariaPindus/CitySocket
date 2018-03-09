
$(function() {
    'use strict';

    var client;

    function showMessage(mesg)
    {   /* response object */
        $('#data').append('<tr>' +
            '<td>' + 'TEST' + '</td>' +
            '<td>' + mesg.value + '</td>' +
            '<td>' + mesg.type + '</td>' +
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
        client = Stomp.client('ws://localhost:8181/output');
        client.connect({}, function (frame) {
            setConnected(true);
            client.subscribe('/city/display', function (message) {
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
        client.send("/app/input", {}, JSON.stringify({
            sensorName: $("#from").val(),
            value: $('#value').val()
        }));
        $('#value').val("");
    });
});