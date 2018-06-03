package com.daria.university.diploma.controllers;

import com.daria.university.diploma.model.Response;
import com.daria.university.diploma.model.dto.SensorData;
import com.daria.university.diploma.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequestMapping(name = "/api")
@Controller
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

    @RequestMapping(value = "/olddata", method = RequestMethod.GET)
    public Response getOldData(){
//        try{
            List<SensorData> resultData = dataService.getDataForLastDays(numberOfDays);
            return new Response(true, resultData);
//        } catch (Exception ex){
//            return new ResponseEntity(ex.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }

    }
}
