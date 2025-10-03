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

}
