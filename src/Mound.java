import java.util.LinkedList;

public class Mound {
    private int amountOfNutrients;
    private int numberOfSpores;
    private LinkedList<Mushroom> mound;

    public Mound(int amountOfNutrients) {
        this.amountOfNutrients = amountOfNutrients;
        mound = new LinkedList<Mushroom>();
    }

    /**
     * Adds one day to the life of a Mushroom
     */
    public void addOneDay(int lifespan, String[] fields) {
        for (Mushroom mushroom : mound) {
            mushroom.setNumberOfDays(mushroom.getNumberOfDays() + 1);
            if (mushroom.getNumberOfDays() == lifespan) {
                mound.remove(mushroom);
                addSpores(fields);
            }
        }
    }

    public void addSpores(String[] fields) {


    }

    public void growMushrooms(String[] fields) {
        if (amountOfNutrients < 0) return;
    }

}
