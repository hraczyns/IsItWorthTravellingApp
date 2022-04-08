package com.hraczynski.isitworthtravellingapp.controller;

import com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject.SimpleCostLivings;
import com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject.SimpleWikidataResult;
import com.hraczynski.isitworthtravellingapp.model.Information;
import com.hraczynski.isitworthtravellingapp.service.IsItWorthTravellingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000","https://isitworthtravelling.herokuapp.com/"})
public class IsItWorthTravellingController {
    private final IsItWorthTravellingService isItWorthTravellingService;

    @GetMapping("/info")
    public ResponseEntity<Information> getWeather(@RequestParam double lon, @RequestParam double lat, @RequestParam(defaultValue = "9", required = false) int limit, @RequestParam(required = false) String kinds) {
        Information informationAboutChosenTarget = isItWorthTravellingService.getInformationAboutChosenTarget(lon, lat, limit, kinds);
        if (informationAboutChosenTarget != null) {
            return new ResponseEntity<>(informationAboutChosenTarget, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/info/places")
    public ResponseEntity<List<SimpleWikidataResult>> getSimplePlaces(@RequestParam double lon, @RequestParam double lat, @RequestParam(defaultValue = "9", required = false) int limit, @RequestParam(required = false) String kinds) {
        List<SimpleWikidataResult> tripPlaces = isItWorthTravellingService.getTripPlaces(lon, lat, limit, kinds);
        if (tripPlaces != null && !tripPlaces.isEmpty()) {
            return new ResponseEntity<>(tripPlaces, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/info/costs")
    public ResponseEntity<?> getCostOfLivings(@RequestParam double lon, @RequestParam double lat) {
        SimpleCostLivings costOfLivings = isItWorthTravellingService.getCostOfLivings(lon, lat);
        if (costOfLivings != null) {
            return new ResponseEntity<>(costOfLivings, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
