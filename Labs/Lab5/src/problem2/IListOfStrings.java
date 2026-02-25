package problem2;
/**
 * ADT Interface: defines a contract for a List of Strings.
 */
public interface IListOfStrings {

    /**
     * Checks whether the list is empty.
     * @return true if the list has no elements, false otherwise
     */
    boolean isEmpty();

    /**
     * Returns the total number of elements in the list.
     * @return the size of the list
     */
    int size();

    /**
     * Checks if a given String exists in the list.
     * @param s the String to search for
     * @return true if found, false otherwise
     */
    boolean contains(String s);

    /**
     * Checks if all elements of another list are present in this list
     * @param other the list of Strings to search
     * @return true if every element in other exists in this list
     */
    boolean containsAll(IListOfStrings other);

    /**
     * Returns a new list with all elements whose length > maxLength removed.
     * The original list is not modified.
     * @param maxLength the maximum allowed String length (inclusive)
     * @return a new filtered list
     */
    IListOfStrings filterLargerThan(int maxLength);

    /**
     * Checks if the list contains at least one duplicate element.
     * @return true if any String appears more than once
     */
    boolean hasDuplicates();

    /**
     * Returns a new list with all duplicate elements removed.
     * Only the first occurrence of each element is kept.
     * The original list is not modified.
     * @return a new list with unique elements only
     */
    IListOfStrings removeDuplicates();
}
