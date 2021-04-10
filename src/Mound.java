import java.util.LinkedList;

public class Mound {
    private int amountOfNutrients;
    private int numberOfSpores;
    private LinkedList<Mushroom> mound;
    private boolean isDone = false;
    private int numberRemoved;

    public Mound(int amountOfNutrients) {
        this.amountOfNutrients = amountOfNutrients;
        mound = new LinkedList<>();
        this.numberOfSpores = 0;
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
    public boolean getIsDone() {
        return isDone;
    }
    /**
     * Adds one day to the life of a Mushroom
     */
    public void addOneDay() {

    }

    public void addSpores() {
    }

    public void growMushrooms(boolean isFirstMushroom){
        mound.add(new Mushroom());
        amountOfNutrients--;
    }

    public int growMushrooms(int lifespan) {
        numberRemoved = 0;
        if(mound.isEmpty()){
            return numberRemoved;
        }

        if(numberOfSpores > 0 && amountOfNutrients > 0){
            mound.add(new Mushroom());
            amountOfNutrients--;
        }
        for (Mushroom mushroom : mound) {
            if(mushroom.getNumberOfDays() == lifespan){
                mound.remove(mushroom);
                numberRemoved--;
            }
            if (amountOfNutrients < 0) {
                isDone = true;
                return numberRemoved;
            } else {
                mushroom.setNumberOfDays(mushroom.getNumberOfDays() + 1);
            }
            amountOfNutrients--;
        }
        return(numberRemoved);
    }
}