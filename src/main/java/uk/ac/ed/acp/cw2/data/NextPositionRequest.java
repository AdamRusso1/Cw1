package uk.ac.ed.acp.cw2.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NextPositionRequest {

    private LngLat start;
    private Double angle;

    //Default Contructor for JSON Parsing
    public NextPositionRequest(){
    }

    //Constructor with params
    public NextPositionRequest( LngLat start, Double angle){
        this.start = start;
        this.angle = angle;
    }

    //Validation method to check that positions exist and are valid
    public boolean isValid(){

        return start != null && start.isValid()  //Checks start position validity
                &&  angle != null && (angle >= 0 && angle <=360) && !angle.isNaN() && !angle.isInfinite(); // checks angle validity

    }


}
