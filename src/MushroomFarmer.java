import java.io.IOException;

/**
 * Simulates farming of mushrooms
 */
public class MushroomFarmer {
    public static void main(String[] args) throws IOException {
        Field field = new Field();
        //Simulates Farming
        field.simulate();
        //Summarizes Farming Results
        field.summarize();
        field.closeWriter();
    }
}
