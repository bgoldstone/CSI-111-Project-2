import java.io.IOException;

/**
 * Simulates a mushroom farm using mounds and accounting for nutrient levels and spores.
 *
 * @author Ben Goldstone
 */
/*
    Instructor: Professor Joseph Helsing
    Date: 4/11/2021
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
