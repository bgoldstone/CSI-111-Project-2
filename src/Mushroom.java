/**
 * Creates a simple object of a Mushroom.
 */
public class Mushroom {
    private int numberOfDays;

    /**
     * Initializes Mushroom Object
     */
    public Mushroom() {
        this.numberOfDays = 0;
    }

    /**
     * Gets number of days for a single mushroom object.
     * @return numberOfDays
     */
    public int getNumberOfDays() {
        return numberOfDays;
    }
    /**
     * Sets number of days for a single mushroom object.
     */
    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }
}
