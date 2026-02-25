package problem1;

/**
 * Awardable: an interface (contract) for anything that can receive awards.
 */
public interface Awardable {
    /**
     * Adds an award to this entity's list of awards.
     * @param award the name of the award received
     */
    void receiveAward(String award);
}
