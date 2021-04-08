import java.util.Scanner;
import java.io.*;

public class Field {
    private File fileName;
    private Scanner fileReader;
    private String line;
    private int lifespan;
    private int[][] field;
    private int[] firstMushroom = new int[2];

    public Field(Scanner scan) {
        System.out.println("What is the file name?");
        fileName = new File(scan.nextLine());
        while (!fileName.exists()) {
            System.out.println("Invalid file name! \nWhat is the correct file name?");
            fileName = new File(scan.nextLine());
        }
        try {
            fileReader = new Scanner(fileName, "UTF-8");
            boolean firstFieldLine = true;
            int rowNumber = 0;
            while (fileReader.hasNextLine()) {
                line = fileReader.nextLine();
                if (line.contains("Lifespan")) {
                    lifespan = Integer.parseInt(line.substring(line.indexOf("=") + 1));
                }
                if (line.contains("First Mushroom")) {
                    String[] tmp = line.split("=")[1].split(",");
                    firstMushroom[0] = Integer.parseInt(tmp[0]);
                    firstMushroom[1] = Integer.parseInt(tmp[1]);
                } else {
                    String[] row = line.split(",");
                    int columnNumber = 0;
                    if (firstFieldLine) {
                        field = new int[row.length][row.length];
                        for (String item : row) {
                            field[rowNumber][columnNumber] = Integer.parseInt(item);
                            columnNumber++;
                        }
                        firstFieldLine = false;
                    } else {
                        for (String item : row) {
                            field[rowNumber][columnNumber] = Integer.parseInt(item);
                            columnNumber++;
                        }
                    }
                    rowNumber++;
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        }
    }
}
