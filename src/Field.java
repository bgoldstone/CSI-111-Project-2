import java.io.*;
import java.util.Scanner;

/**
 * Object of a Field. Contains an Array of Mounds
 */
public class Field {
    private int lifespan;
    private Mound[][] field;
    private final int[] position = new int[2];
    private int numberOfDays = 0;
    private int maxNumInDay = 0;
    private int maxDay = 0;
    private BufferedWriter bw;

    public Field() throws IOException {
        //Prompts user for file name
        StringBuilder sb = new StringBuilder();
        System.out.println("What is the file name? ");
        Scanner scan = new Scanner(System.in);
        File fileName = new File(scan.nextLine());
        sb.append("What is the file name? ").append(fileName.getName()).append("\n");
        while (!fileName.exists()) {
            System.out.println("Invalid file name! \nWhat is the correct file name?");
            fileName = new File(scan.nextLine());
            sb.append("What is the file name? ").append(fileName.getName()).append("\n");
        }
        bw = new BufferedWriter(new FileWriter(fileName.getName().substring(0, fileName.getName().indexOf(".")) + "_out.txt"));

        bw.write(sb.toString());

        //Reads in file
        Scanner fileReader = new Scanner(fileName);
        boolean firstFieldLine = true;
        int rowNumber = 0;
        String line;
        //If file has more lines, keep reading
        while (fileReader.hasNextLine()) {
            line = fileReader.nextLine();
            if (line.contains("Lifespan")) {
                lifespan = Integer.parseInt(line.substring(line.indexOf("=") + 1));
            } else if (line.contains("First Mushroom")) {
                String[] tmp = line.split("=")[1].split(",");
                position[0] = Integer.parseInt(tmp[0]);
                position[1] = Integer.parseInt(tmp[1]);
            } else {
                String[] row = line.split(",");
                int columnNumber = 0;
                if (firstFieldLine) {
                    field = new Mound[row.length][row.length];
                    firstFieldLine = false;
                }
                for (String item : row) {
                    field[rowNumber][columnNumber] = new Mound(Integer.parseInt(item));
                    columnNumber++;
                }
                rowNumber++;
            }
        }
        fileReader.close();
    }

    public void simulate() throws IOException {
        boolean keepGoing = true;
        //Puts first mushroom on field
        field[position[0]][position[1]].growMushrooms(true);
        while (keepGoing) {
            int maxNumberOfMushroomsInADay = 0;
            int numberRemoved;
            for (int i = 0; i < field.length; i++) {
                Mound[] row = field[i];
                for (int j = 0; j < row.length; j++) {
                    Mound mound = row[j];
                    numberRemoved = mound.growMushrooms(lifespan);
                    maxNumberOfMushroomsInADay += mound.getMound().size();
                    position[0] = i;
                    position[1] = j;
                    if (numberRemoved > 0)
                        addSpores(position, numberRemoved);

                }
                fieldState();
                numberOfDays++;
            }
            if (this.maxNumInDay < maxNumberOfMushroomsInADay) {
                this.maxNumInDay = maxNumberOfMushroomsInADay;
                maxDay = numberOfDays--;
            }
            keepGoing = false;
            for (Mound[] row : field) {
                for (Mound mound : row) {
                    if (!mound.getIsDone()) {
                        keepGoing = true;
                        break;
                    }
                }
            }
            numberOfDays++;
        }
        summarize();
        bw.close();
    }

    /**
     * Adds spores given position
     *
     * @param position    Location in the field array
     * @param numberToAdd Number of spores to add
     */
    public void addSpores(int[] position, int numberToAdd) {
        try {
            field[position[0] - 1][position[1]].addSpores(numberToAdd);
            field[position[0] + 1][position[1]].addSpores(numberToAdd);
            field[position[0]][position[1] - 1].addSpores(numberToAdd);
            field[position[0]][position[1] + 1].addSpores(numberToAdd);
        } catch (IndexOutOfBoundsException ignored) {
        }
    }

    /**
     * Gives Maximum number of Mushrooms in the simulation and the day it happened on
     * plus the nutrient levels at the end of the simulation
     */
    public void summarize() throws IOException {
        bw.write(String.format("The maximum number of mushrooms on a single day was %d on day %d ", maxNumInDay, numberOfDays));
        bw.write("\nThe nutrients still remaining in the field looks like this:");
        for (Mound[] row : field) {
            bw.write("\n|");
            for (Mound mound : row) {
                bw.write(String.format("%d|", mound.getAmountOfNutrients()));
            }
        }
    }

    /**
     * Gives # of Mushrooms in each mound per day
     */
    public void fieldState() throws IOException{
        bw.write("\nDay:" + numberOfDays);
        for (Mound[] row : field) {
            bw.write("|");
            for (Mound mound : row) {
                bw.write("M" + mound.getMound().size() + "|");
            }
            bw.write("\n");
        }
    }
}
