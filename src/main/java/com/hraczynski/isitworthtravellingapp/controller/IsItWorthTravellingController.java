package com.hraczynski.isitworthtravellingapp.controller;

import com.hraczynski.isitworthtravellingapp.model.Information;
import com.hraczynski.isitworthtravellingapp.service.IsItWorthTravellingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class IsItWorthTravellingController {
    private final IsItWorthTravellingService isItWorthTravellingService;

    @GetMapping("/info")
    public ResponseEntity<Information> getWeather(@RequestParam double lon, @RequestParam double lat, @RequestParam(defaultValue = "10",required = false) int limit, @RequestParam(required = false) String kinds) {
        Information informationAboutChosenTarget = isItWorthTravellingService.getInformationAboutChosenTarget(lon, lat, limit, kinds);
        if (informationAboutChosenTarget != null) {
            return new ResponseEntity<>(informationAboutChosenTarget, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
