import java.io.*;
import java.util.Scanner;

/**
 * Object of a Field. Contains an Array of Mounds
 *
 * @author Ben Goldstone
 */
public class Field {
    private int lifespan; //Lifespan of a mushroom.
    private Mound[][] field; //Holds array of mounds.
    private final int[] position = new int[2]; //Holds current position in field.
    private int numberOfDays = 0; //Number of days passed in simulation.
    private int maxNumInDay = 0; //Maximum number of mushrooms in a day.
    private int maxDay = 0; //The day the maximum number of mushrooms happened on.
    private PrintWriter pw; //Writes to output file.

    public Field() throws IOException {
        //Prompts user for file name.
        StringBuilder sb = new StringBuilder();
        System.out.print("What is the file name? ");
        Scanner scan = new Scanner(System.in);
        File fileName = new File(scan.nextLine());
        System.out.println();
        sb.append("What is the file name? ").append(fileName.getName()).append("\n");
        //If file name does not exist, keep prompting user.
        while (!fileName.exists()) {
            System.out.print("Invalid file name! \nWhat is the correct file name?");
            fileName = new File(scan.nextLine());
            System.out.println();
            sb.append("What is the file name? ").append(fileName.getName()).append("\n");
        }
        //Initializes output file.
        pw = new PrintWriter(fileName.getName().substring(0, fileName.getName().indexOf(".")) + "_out.txt");
        //Writes file prompt as lines in new file.
        pw.print(sb);

        //Reads in file.
        Scanner fileReader = new Scanner(fileName);
        boolean firstFieldLine = true;
        int rowNumber = 0;
        String line;
        //If file has more lines, keep reading.
        while (fileReader.hasNextLine()) {
            line = fileReader.nextLine();
            //If line has lifespan on it, parse it for integer equaling to lifespan.
            if (line.contains("Lifespan")) {
                lifespan = Integer.parseInt(line.substring(line.indexOf("=") + 1));
                //If line contains first mushroom, get the position of the first mushroom.
            } else if (line.contains("First Mushroom")) {
                String[] tmp = line.split("=")[1].split(",");
                position[0] = Integer.parseInt(tmp[0]);
                position[1] = Integer.parseInt(tmp[1]);
                //Else, it must be the array of nutrients.
            } else {
                String[] row = line.split(",");
                int columnNumber = 0;
                //Initializes new Mound array based on length of first line.
                if (firstFieldLine) {
                    field = new Mound[row.length][row.length];
                    firstFieldLine = false;
                }
                //Goes through each line
                for (String item : row) {
                    field[rowNumber][columnNumber] = new Mound(Integer.parseInt(item));
                    columnNumber++;
                }
                //Moves to next row
                rowNumber++;
            }
        }
        field[position[0]][position[1]].growFirstMushroom();
        fieldState();
        fileReader.close();
    }

    /**
     * Simulates field over a cycle of days, until mushrooms are done growing and there are no nutrients left.
     */
    public void simulate() {
        boolean keepGoing = true;
        //While mushrooms and spores can be grown, keep going.
        while (keepGoing) {
            int maxNumberOfMushroomsInADay = 0;
            int numberRemoved;
            //Cycles through each mound.
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field[i].length; j++) {
                    Mound mound = field[i][j];
                    numberRemoved = mound.growMushroom(lifespan);
                    position[0] = i;
                    position[1] = j;
                    //If any mushrooms were removed from mound in position [i][j] add spores.
                    for (int k = 0; k < numberRemoved; k++) {
                        addSpores(position, numberRemoved);
                    }
                }
            }
            for (Mound[] row : field) {
                for (Mound mound : row) {
                    mound.growSpores();
                }
            }
            //prints field state on day numberOfDays to file
            for (Mound[] row : field) {
                for (Mound m : row) {
                    maxNumberOfMushroomsInADay += m.getMound().size();
                }
            }
            //if maximum number of mushrooms, set to maxNum in day
            if (maxNumInDay < maxNumberOfMushroomsInADay) {
                maxNumInDay = maxNumberOfMushroomsInADay;
                maxDay = numberOfDays;
            }
            //Checks if all mounds are done
            fieldState();
            keepGoing = isNotDone();
        }
    }

    /**
     * Adds spores given position
     *
     * @param position    Location in the field array
     * @param numberToAdd Number of spores to add
     */
    public void addSpores(int[] position, int numberToAdd) {
        if (position[0] >= 1)
            field[position[0] - 1][position[1]].addSpores(1);
        if (position[0] < field.length - 1)
            field[position[0] + 1][position[1]].addSpores(1);
        if (position[1] >= 1)
            field[position[0]][position[1] - 1].addSpores(1);
        if (position[1] < field.length - 1)
            field[position[0]][position[1] + 1].addSpores(1);

    }

    /**
     * Gives Maximum number of Mushrooms in the simulation and the day it happened on
     * plus the nutrient levels at the end of the simulation
     */
    public void summarize() {
        pw.print(String.format("The maximum number of mushrooms on a single day was %d on day %d ", maxNumInDay, maxDay));
        pw.print("\nThe nutrients still remaining in the field looks like this:");
        for (Mound[] row : field) {
            pw.print("\n|");
            for (Mound mound : row) {
                pw.print(String.format("%d|", mound.getAmountOfNutrients()));
            }
        }
        pw.println();
    }

    /**
     * Gives # of Mushrooms in each mound per day
     */
    public void fieldState() {
        pw.print("\nDay:" + numberOfDays + "\n");
        for (Mound[] row : field) {
            int numOfMushrooms = 0;
            pw.print("|");
            for (Mound mound : row) {

                pw.print("M" + mound.getMound().size() + "|");
            }
            pw.println();
        }
        //Adds a day.
        numberOfDays++;
    }

    /**
     * Finds if all fields are done
     *
     * @return boolean
     */
    public boolean isNotDone() {
        for (Mound[] row : field) {
            for (Mound mound : row) {
                if (mound.getAmountOfNutrients() > 0 && mound.getNumberOfSpores() > 0)
                    return true;
                if (mound.getMound().size() > 0)
                    return true;
            }
        }
        return false;
    }

    /**
     * Closes Buffered Writer
     *
     * @throws IOException
     */
    public void closeWriter() throws IOException {
        pw.close();
    }
}
