package uk.ac.ed.acp.cw2.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import uk.ac.ed.acp.cw2.Service.GeometryService;
import uk.ac.ed.acp.cw2.data.DistanceRequest;
import uk.ac.ed.acp.cw2.data.LngLat;
import uk.ac.ed.acp.cw2.data.RuntimeEnvironment;
import uk.ac.ed.acp.cw2.Service.GeometryService;
import java.net.URL;
import java.time.Instant;

/**
 * Controller class that handles various HTTP endpoints for the application.
 * Provides functionality for serving the index page, retrieving a static UUID,
 * and managing key-value pairs through POST requests.
 */
@RestController()
@RequestMapping("/api/v1")
public class ServiceController {

    private static final Logger logger = LoggerFactory.getLogger(ServiceController.class);

    @Value("${ilp.service.url}")
    public URL serviceUrl;

    private final GeometryService geometryService;

    public ServiceController(GeometryService geometryService) {
        this.geometryService = geometryService;
    }


    @GetMapping("/")
    public String index() {
        return "<html><body>" +
                "<h1>Welcome from ILP</h1>" +
                "<h4>ILP-REST-Service-URL:</h4> <a href=\"" + serviceUrl + "\" target=\"_blank\"> " + serviceUrl+ " </a>" +
                "</body></html>";
    }

    @GetMapping("/uid")
    public String uid() {
        return "s2520412";
    }

    @GetMapping("/demo")
    public String demo() {
        return "demo";
    }

    @PostMapping("/distanceTo")
    public ResponseEntity<?> distanceTo(@RequestBody DistanceRequest request){
        // Validate the request
        if(request ==null || !request.isValid()){
            logger.warn("Invalid request recieved at /distanceTo endpoint");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        // Calculate Distance
        double distance = geometryService.EuclideanDistance(
                request.getPosition1(),
                request.getPosition2()
        );

        logger.debug("Distance calculated: {}", distance);
        return ResponseEntity.ok(distance);



    }

    @PostMapping("/isCloseTo")
    public ResponseEntity<?> isCloseTo(@RequestBody DistanceRequest request){
        // Validate the Request
        if(request==null || !request.isValid()){
            logger.warn("Invalid request recieved at /isCloseTo endpoint");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        // max distance value
        final double MAX_DISTANCE = 0.00015;

        // Calculate Distance between points and check if its less than 0.0015 degrees away
        boolean isClose = geometryService.EuclideanDistance(
                request.getPosition1(),
                request.getPosition2()
        ) < MAX_DISTANCE;
        logger.debug("isClose: {}",isClose);
        return ResponseEntity.ok(isClose);
    }

}

