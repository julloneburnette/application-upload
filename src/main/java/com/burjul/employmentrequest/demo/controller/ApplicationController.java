package com.burjul.employmentrequest.demo.controller;

import com.burjul.employmentrequest.demo.model.AppBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApplicationController {

    Logger logger = LoggerFactory.getLogger(ApplicationController.class);

    @GetMapping(path ="/application", produces = "application/json")
    @ResponseBody
    public ResponseEntity submitRequest(@RequestParam(name = "data", required = true) String data)  {

        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Payload received: {}", data);
        AppBody appBody;
        try {
            appBody = objectMapper.readValue(data, AppBody.class);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>("Data was no parsed properly", HttpStatus.BAD_REQUEST);
        }
        logger.info("Response: {}", appBody);
        return new ResponseEntity<AppBody>(appBody, HttpStatus.OK);
    }
}
