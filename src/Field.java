import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
    private PrintWriter pw;

    public Field() {
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
                try {
                    pw = new PrintWriter(fileName.getName().substring(0, fileName.getName().indexOf(".")) + "_out.txt");
                } catch (FileNotFoundException e) {
                    e.printStackTrace(System.out);
                }
            pw.println(sb);
        try {
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
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        }
    }

    public void simulate() {
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
                    if (numberRemoved > 0) {
                        for (int k = 0; k < numberRemoved; k++) {
                            addSpores(position);
                        }
                    }
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
        pw.close();
    }

    /**
     * Adds spores given position
     *
     * @param position location in the field array
     */
    public void addSpores(int[] position) {
        try {
            field[position[0] - 1][position[1]].setNumberOfSpores(field[position[0] - 1][position[1]].getNumberOfSpores() + 1);
            field[position[0] + 1][position[1]].setNumberOfSpores(field[position[0] + 1][position[1]].getNumberOfSpores() + 1);
            field[position[0]][position[1] - 1].setNumberOfSpores(field[position[0]][position[1] - 1].getNumberOfSpores() + 1);
            field[position[0]][position[1] + 1].setNumberOfSpores(field[position[0]][position[1] + 1].getNumberOfSpores() + 1);
        } catch (IndexOutOfBoundsException ignored) {
        }
    }

    /**
     * Gives Maximum number of Mushrooms in the simulation and the day it happened on
     * plus the nutrient levels at the end of the simulation
     */
    public void summarize() {
        pw.print(String.format("The maximum number of mushrooms on a single day was %d on day %d ", maxNumInDay, numberOfDays));
        pw.print("\nThe nutrients still remaining in the field looks like this:");
        for (Mound[] row : field) {
            pw.print("\n|");
            for (Mound mound : row) {
                pw.print(String.format("%d|", mound.getAmountOfNutrients()));
            }
        }
    }

    /**
     * Gives # of Mushrooms in each mound per day
     */
    public void fieldState() {
        pw.print("\nDay:" + numberOfDays);
        for (Mound[] row : field) {
            pw.print("|");
            for (Mound mound : row) {
                pw.print("M" + mound.getMound().size() + "|" );
            }
            pw.print("\n");
        }
    }
}
