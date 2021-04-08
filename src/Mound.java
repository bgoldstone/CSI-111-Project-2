import java.util.LinkedList;

public class Mound {
    private int amountOfNutrients;
    private int numberOfSpores;
    private LinkedList<Mushroom> mound;

    public Mound(int amountOfNutrients) {
        this.amountOfNutrients = amountOfNutrients;
        mound = new LinkedList<Mushroom>();
        numberOfSpores = 0;
    }

    /**
     * Adds one day to the life of a Mushroom
     */
    public void addOneDay(int lifespan, Mound[][] fields) {
        for (Mushroom mushroom : mound) {
            mushroom.setNumberOfDays(mushroom.getNumberOfDays() + 1);
            if (mushroom.getNumberOfDays() == lifespan) {
                mound.remove(mushroom);
                addSpores(fields);
            }
        }
    }

    public boolean addSpores(Mound[][] fields) {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                try {
                    if (fields[i][j].amountOfNutrients > 0 && fields[i][j].numberOfSpores > 0) {
                        fields[i - 1][j].numberOfSpores += 1;
                        fields[i + 1][j].numberOfSpores += 1;
                        fields[i][j - 1].numberOfSpores += 1;
                        fields[i][j + 1].numberOfSpores += 1;
                        growMushrooms(fields);
                        return true;
                    }
                } catch (IndexOutOfBoundsException e) {

                }
            }
        }
        return false;
    }

    public void growMushrooms(Mound[][] fields) {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                if(fields[i][j].numberOfSpores > 0){
                    mound.add(new Mushroom(0));
                }
            }
        }
    }
}
