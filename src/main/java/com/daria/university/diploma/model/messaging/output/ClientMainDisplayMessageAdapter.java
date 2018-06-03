package com.daria.university.diploma.model.messaging.output;

import com.daria.university.diploma.model.dto.SensorData;
import com.daria.university.diploma.service.SensorStateConverter;

public class ClientMainDisplayMessageAdapter extends ClientMainDisplayMessage {

    public ClientMainDisplayMessageAdapter(SensorData data){
        super(data.getDevice(),
                data.getDefaultData(),
                SensorStateConverter.getSensorState(data.getDevice().getType(),data.getDefaultData()));
    }
}
