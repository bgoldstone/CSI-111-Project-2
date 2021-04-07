import java.util.LinkedList;

public class Mound {
    private int amountOfNutrients;
    private int numberOfSpores;
    private LinkedList<Mushroom> mound;
    public Mound(int amountOfNutrients) {
        this.amountOfNutrients = amountOfNutrients;
        mound = new LinkedList<Mushroom>();
        numberOfSpores = 1;
    }

    /**
     * Adds one day to the life of a Mushroom
     */
    public void addOneDay(){
        for (Mushroom mushroom : mound){
            if(mushroom.getNumberOfDays() == mushroom.getNumberOfDays())
            mushroom.setNumberOfDays(mushroom.getNumberOfDays() + 1);
        }
    }

    public void addSpores(){

    }
}
