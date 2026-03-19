package problem2;

import java.util.Objects;

/**
 * Represents a person who is a creator.
 *
 * A person has a first name and a last name.
 */
public abstract class Person extends Creator {
  private String firstName;
  private String lastName;

  /**
   * Constructs a person with the given first and last name.
   *
   * @param firstName the first name
   * @param lastName the last name
   * @throws IllegalArgumentException if either name is null or blank
   */
  public Person(String firstName, String lastName) {
    setFirstName(firstName);
    setLastName(lastName);
  }

  /**
   * Returns the first name.
   *
   * @return the first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets the first name.
   *
   * @param firstName the new first name
   * @throws IllegalArgumentException if firstName is null or blank
   */
  public void setFirstName(String firstName) {
    if (firstName == null || firstName.isBlank()) {
      throw new IllegalArgumentException("First name cannot be null or blank.");
    }
    this.firstName = firstName;
  }

  /**
   * Returns the last name.
   *
   * @return the last name
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets the last name.
   *
   * @param lastName the new last name
   * @throws IllegalArgumentException if lastName is null or blank
   */
  public void setLastName(String lastName) {
    if (lastName == null || lastName.isBlank()) {
      throw new IllegalArgumentException("Last name cannot be null or blank.");
    }
    this.lastName = lastName;
  }

  /**
   * Returns the person's full name.
   *
   * @return the full name
   */
  @Override
  public String toString() {
    return firstName + " " + lastName;
  }

  /**
   * Compares this person to another object for equality.
   *
   * Two people are equal if they are the same subclass and have the same
   * first and last name.
   *
   * @param o the other object
   * @return true if equal; false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Person person = (Person) o;
    return firstName.equals(person.firstName) && lastName.equals(person.lastName);
  }

  /**
   * Returns a hash code for this person.
   *
   * @return the hash code
   */
  @Override
  public int hashCode() {
    return Objects.hash(getClass(), firstName, lastName);
  }
}