package others.gson_practice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class FishRoom {
    private int machineType;
    private String currency;
    private int numRooms;
}
