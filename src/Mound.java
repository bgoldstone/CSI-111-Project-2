import java.util.LinkedList;

public class Mound {
    private int amountOfNutrients;
    private int numberOfSpores;
    private LinkedList<Mushroom> mound;

    public Mound(int amountOfNutrients) {
        this.amountOfNutrients = amountOfNutrients;
        mound = new LinkedList<>();
        this.numberOfSpores = 0;
    }

    /**
     * Adds one day to the life of a Mushroom
     */
    public void addOneDay(int lifespan, Mound[][] fields) {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                for (Mushroom mushroom : fields[i][j].getMound()) {
                    mushroom.setNumberOfDays(mushroom.getNumberOfDays() + 1);
                    if (mushroom.getNumberOfDays() == lifespan) {
                        addSpores(mushroom, lifespan, fields, new int[]{i, j});
                        fields[i][j].getMound().remove(mushroom);
                    }
                }
            }
        }
    }

    public void addSpores(Mushroom mushroom, int lifespan, Mound[][] fields, int[] position) {
        try {
            fields[position[0] - 1][position[1]].setNumberOfSpores(fields[position[0] - 1][position[1]].getNumberOfSpores() + 1);
            fields[position[0] + 1][position[1]].setNumberOfSpores(fields[position[0] + 1][position[1]].getNumberOfSpores() + 1);
            fields[position[0]][position[1] - 1].setNumberOfSpores(fields[position[0]][position[1] - 1].getNumberOfSpores() + 1);
            fields[position[0]][position[1] + 1].setNumberOfSpores(fields[position[0]][position[1] + 1].getNumberOfSpores() + 1);
        } catch (IndexOutOfBoundsException ignored) {
        }

        growMushrooms(fields);
    }

    public int getAmountOfNutrients() {
        return amountOfNutrients;
    }

    public void setAmountOfNutrients(int amountOfNutrients) {
        this.amountOfNutrients = amountOfNutrients;
    }

    public int getNumberOfSpores() {
        return numberOfSpores;
    }

    public void setNumberOfSpores(int numberOfSpores) {
        this.numberOfSpores = numberOfSpores;
    }

    public LinkedList<Mushroom> getMound() {
        return mound;
    }

    public void setMound(LinkedList<Mushroom> mound) {
        this.mound = mound;
    }


    public void growMushrooms(Mound[][] fields) {
        for (Mound[] row : fields) {
            for (Mound mushroom : row) {
                if (mushroom.getNumberOfSpores() > 0) {
                    getMound().add(new Mushroom(0));
                }
            }
        }
    }

    public int getAmountOfNutrients() {
        return amountOfNutrients;
    }

    public void setAmountOfNutrients(int amountOfNutrients) {
        this.amountOfNutrients = amountOfNutrients;
    }

    public int getNumberOfSpores() {
        return numberOfSpores;
    }

    public void setNumberOfSpores(int numberOfSpores) {
        this.numberOfSpores = numberOfSpores;
    }

    public LinkedList<Mushroom> getMound() {
        return mound;
    }

    public void setMound(LinkedList<Mushroom> mound) {
        this.mound = mound;
    }
}
