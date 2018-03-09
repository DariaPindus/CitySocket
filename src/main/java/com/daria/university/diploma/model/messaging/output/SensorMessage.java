package com.daria.university.diploma.model.messaging.output;


import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class SensorMessage {

        private String sensorName;
        private boolean success;    //TODO: possibly not here
        private int sensorData;
        private String time;

    public SensorMessage(){}

        public SensorMessage(String sensorName, int sensorData) {
            this.sensorName = sensorName;
            this.sensorData = sensorData;
            this.time = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_DATE_TIME);
        }

        public String getSensorName() {
            return sensorName;
        }

        public void setSensorName(String sensorName) {
            this.sensorName = sensorName;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public int getSensorData(){
            return sensorData;
        }

        public void setSensorData(int sensorData){
            this.sensorData = sensorData;
        }
}
