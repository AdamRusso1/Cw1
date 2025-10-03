package uk.ac.ed.acp.cw2.data;
import lombok.Getter;
import lombok.Setter;

///  This class represents a geographical coordinate with longitude and latitude.
@Getter
@Setter
public class LngLat {

    private Double lng; // using Double not double as Double object can be null
    private Double lat;

    // Default constructor - needed for Jackson JSON parsing
    public LngLat(){

    }

    // Constructor with params
    public LngLat(Double lng , Double lat){
        this.lng = lng;
        this.lat = lat;

    }

    // Helper method to check if this position is valid
    public boolean isValid() {
        // Check that both values exist and are real numbers
        return lng != null && lat != null
                && !lng.isNaN() && !lat.isNaN()
                && !lng.isInfinite() && !lat.isInfinite();
    }


}
