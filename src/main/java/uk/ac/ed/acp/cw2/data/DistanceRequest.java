package uk.ac.ed.acp.cw2.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DistanceRequest {
    private LngLat position1;
    private LngLat position2;

    // Default constructor - needed for Jackson JSON parsing
    public DistanceRequest(){

    }
    // Constructor with params
    public DistanceRequest(LngLat position1, LngLat position2){
        this.position1 = position1;
        this.position2 = position2;
    }

    //Validation method to check if positions exist and are valid( .isValid() from LngLat class)
    public boolean isValid(){
        return position1 != null && position2 != null && position1.isValid() && position2.isValid();
    }

}
