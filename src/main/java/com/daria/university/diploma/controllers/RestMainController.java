package com.daria.university.diploma.controllers;

import com.daria.university.diploma.model.UpdateRequestPair;
import com.daria.university.diploma.model.dto.Device;
import com.daria.university.diploma.model.dto.SensorData;
import com.daria.university.diploma.model.messaging.output.ClientMainDisplayMessage;
import com.daria.university.diploma.model.messaging.output.ClientMainDisplayMessageAdapter;
import com.daria.university.diploma.service.DeviceService;
import com.daria.university.diploma.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/**")
public class RestMainController {
    /*@RequestMapping(value = {"/", "/**"})
    public String main() {
        return "index";
    }
    @RequestMapping("/{path:[^\\.]+}/**")
    public String forward() {
        return "forward:/";
    }*/
    /*@RequestMapping(value = "/{[path:[^\\.]*}")
    public void redirect(@PathVariable String path, HttpServletResponse response) throws IOException {
        System.out.println("path " + path);
        response.sendRedirect("/#!" + path);
    }*/
    @Value("${app.data.load.forLastDays:2}")
    private int numberOfDays;

    @Autowired
    SensorDataService dataService;

    @Autowired
    DeviceService deviceService;

/*
    @RequestMapping(value = "/olddata", method = RequestMethod.GET)
    public Response getOldData(){
//        try{
            //List<SensorData> resultData = dataService.getDataForLastDays(numberOfDays);
        List<SensorData> resultData = dataService.getDataForLastDays(numberOfDays);
        return new Response(true, resultData);
//        } catch (Exception ex){
//            return new ResponseEntity(ex.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }

    }
*/
    @RequestMapping(value = "/olddata", method = RequestMethod.GET)
    public ResponseEntity<List<ClientMainDisplayMessage>> getOldData(@RequestParam(name = "id", defaultValue = "-1", required = false) long id){
        List<SensorData> sensorDatas;
        if (id == -1)
            sensorDatas = dataService.getDataForLastDays(numberOfDays);
        else
            sensorDatas = dataService.getDataForLastDaysById(numberOfDays, id);

        List<ClientMainDisplayMessage> messages = sensorDatas.stream()
                .map(sensorData -> new ClientMainDisplayMessageAdapter(sensorData))
                .collect(Collectors.toList());
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @RequestMapping(value = "/devices", method = RequestMethod.GET)
    public ResponseEntity<List<Device>> getDevices(){
        List<Device> resultData = (List<Device>)deviceService.getAllItems();
        return new ResponseEntity<>(resultData, HttpStatus.OK);
    }

/*
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public ResponseEntity updateDevice(@PathVariable("id") long id, @RequestBody Device device) {
        Device saved = deviceService.save(device);
        return new ResponseEntity(saved, HttpStatus.OK);
    }
*/

    @RequestMapping("/sensors/{id}")
    public ResponseEntity<Device> getDevice(@PathVariable("id") long id){
        if (!deviceService.findById(id).isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Device device = deviceService.findById(id).get();
        return new ResponseEntity<>(device, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public ResponseEntity updateDeviceFields(@PathVariable("id") long id, @RequestBody UpdateRequestList updateRequest) throws NoSuchFieldException {

        Class  aClass = Device.class;
        Device device = new Device(id);
        updateRequest.forEach(req -> {
            Field field = null;
            try {
                field = aClass.getField(req.getFieldName());
                field.set(device, req.getValue());
            } catch (NoSuchFieldException |IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        Device saved = deviceService.update(device);

        return new ResponseEntity(saved, HttpStatus.OK);
    }

    static class UpdateRequestList extends ArrayList<UpdateRequestPair>{}
}
