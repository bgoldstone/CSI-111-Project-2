import java.io.IOException;

/**
 * Simulates farming of mushrooms
 */
public class MushroomFarmer {
    public static void main(String[] args) throws IOException {
        //Creates new field.
        Field field = new Field();
        //Simulates farming simulation.
        field.simulate();
        //Summarizes farming results.
        field.summarize();
        //Closes BufferedWriter
        field.closeWriter();
    }
}
