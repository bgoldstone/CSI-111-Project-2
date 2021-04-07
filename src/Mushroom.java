/**
 * Creates an object of a Mushroom
 */
public class Mushroom {
    private int numberOfDays;

    /**
     * Initializes Mushroom Object
     * @param numberOfDays Number of days a mushroom lives
     */
    public Mushroom(int numberOfDays){
        this.setNumberOfDays(numberOfDays);
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }
}
