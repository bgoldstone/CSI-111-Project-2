import java.util.LinkedList;

/**
 * Creates an object of a Mound to store mushroom objects.
 *
 * @author Ben Goldstone
 */
public class Mound {
    private int amountOfNutrients;
    private int numberOfSpores;
    private LinkedList<Mushroom> mound;

    /**
     * Mound Constructor.
     *
     * @param amountOfNutrients amount of initial nutrients in mound
     */
    public Mound(int amountOfNutrients) {
        this.amountOfNutrients = amountOfNutrients;
        mound = new LinkedList<>();
        this.numberOfSpores = 0;
    }

    /**
     * Gets amount of nutrients.
     *
     * @return amountOfNutrients
     */
    public int getAmountOfNutrients() {
        return amountOfNutrients;
    }

    /**
     * Sets amount of nutrients.
     *
     * @param amountOfNutrients total number of nutrients in this mound.
     */
    public void setAmountOfNutrients(int amountOfNutrients) {
        this.amountOfNutrients = amountOfNutrients;
    }

    /**
     * Gets number of spores.
     *
     * @return numberOfSpores
     */
    public int getNumberOfSpores() {
        return numberOfSpores;
    }

    /**
     * Sets number of spores.
     *
     * @param numberOfSpores number of spores in this mound.
     */
    public void setNumberOfSpores(int numberOfSpores) {
        this.numberOfSpores = numberOfSpores;
    }

    /**
     * Gets LinkedList of Mushrooms in current mound.
     *
     * @return mound
     */
    public LinkedList<Mushroom> getMound() {
        return mound;
    }


    /**
     * Adds one day to the life of a Mushroom
     */
    public void addSpores(int numberToAdd) {
        numberOfSpores += numberToAdd;
    }

    /**
     * Grows first mushroom.
     */
    public void growFirstMushroom() {
        mound.add(new Mushroom());
        amountOfNutrients--;
    }

    /**
     * Grows First Mushroom
     *
     * @param lifespan number of days a mushroom lives.
     * @return numberRemoved to add spores
     */
    public int growMushroom(int lifespan) {
        int numberRemoved = 0;
        //if (mound.isEmpty()) {
        //return numberRemoved;
        //}
        //if more than 0 spores exist and nutrients available
        if (amountOfNutrients > 0) {
            for (int i = 0; i < numberOfSpores; i++) {
                //if nutrients available, grow spores, else spores won't grow.
                if (amountOfNutrients > 0) {
                    mound.add(new Mushroom());
                    amountOfNutrients--;
                }
            }
        }
        for (Mushroom mushroom : mound) {
            //If it is the mushrooms' lifespan, remove the mushroom.
            if (mushroom.getNumberOfDays() == lifespan) {
                mound.remove(mushroom);
                numberRemoved--;
            }
            //If mound is out of nutrients and no mushrooms in mound, this mound is done.
            else {
                //Grows each mushroom in mound for simulation
                mushroom.setNumberOfDays(mushroom.getNumberOfDays() + 1);
            }
        }
        return (numberRemoved);
    }
}