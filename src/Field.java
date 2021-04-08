import java.util.Scanner;
import java.io.*;

public class Field {
    private File fileName;
    private Scanner fileReader;
    private int lifespan;
    private Mound[][] field;
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
            String line;
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
    public void simulate(){
        while(true){

        }
    }
}
