package uk.ac.ed.acp.cw2.Service;

import org.springframework.stereotype.Service;
import uk.ac.ed.acp.cw2.data.LngLat;

@Service
public class GeometryService {
    /**
     * Calculates the Euclidean distance between two positions using this formula Sqrt( (x2-x1)^2 + (y2-y1)^2 )
     * @param position1 First position
     * @param position2 Second position
     * @return The distance in degrees
     */

    public double EuclideanDistance(LngLat position1, LngLat position2){

        //Calculating the difference in Longitude and lattitude
        double lngDiff = position1.getLng() - position2.getLng();
        double latDiff = position1.getLat() - position2.getLat();

        // Calculating the Euclidean distance

        double distance = Math.sqrt(Math.pow(lngDiff,2) + Math.pow(latDiff,2));


    return distance;
    }

    private static final double MOVE_DISTANCE = 0.00015; //Distance traveled per move as the spec specified
    /**
     * Calculates the next position given a start position and an angle using trigonomety
     * @param start The start position
     * @param angle The angle in degrees ( 0 = east, 90 = north, 180 = west, 270 = South)
     * @return The next position
     */
    public LngLat nextPosition(LngLat start, Double angle){

        //Convert degrees to radians as required by Java Maths functions
        double radAngle = Math.toRadians(angle);

        // Calculating the new Longitude and Latitude
        double newLat = start.getLat() + (MOVE_DISTANCE * Math.sin(radAngle));
        double newLng = start.getLng() + (MOVE_DISTANCE * Math.cos(radAngle));

        // returning new position

        return new LngLat(newLng, newLat);


    }

}
