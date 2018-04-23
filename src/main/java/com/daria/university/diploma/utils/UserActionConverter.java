package com.daria.university.diploma.utils;


import com.daria.university.diploma.model.dto.UserAction;
import com.daria.university.diploma.model.messaging.output.ClientActuatorMessage;

//todo: use some pattern ??
/*
    class to convert any needed object to useraction
 */
public class UserActionConverter {

    public static UserAction getUserAction(ClientActuatorMessage actuatorMessage){
        return new UserAction();
    }
}
