package problem1;

/**
 * Name encapsulates an artist's first and last name together.
 *
 * Why a separate class instead of two strings in Artist?
 * - Keeps related data grouped together (encapsulation)
 * - Makes it easy to add behavior later (e.g. getFullName())
 * - Cleaner constructor signatures
 */
public class Name {
    private final String firstName;
    private final String lastName;

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() { return firstName; }
    public String getLastName()  { return lastName;  }

    public String getFullName()  { return firstName + " " + lastName; }

    @Override
    public String toString() { return getFullName(); }
}
