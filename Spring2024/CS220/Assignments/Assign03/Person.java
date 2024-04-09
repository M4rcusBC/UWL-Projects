package Spring2024.CS220.Assignments.Assign03;

/**
 * A class representing a person with a name and age. Implements the Comparable
 * interface to compare people by name and age.
 *
 * @author Marcus Clements
 * @since 2024-04-08
 */
public class Person implements Comparable<Person> {

    private final String name;
    private final int age;

    /**
     * Constructor for the Person class
     *
     * @param name Name of the person
     * @param age  Age of the person
     */
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Returns the name of the person
     *
     * @return Name of the person
     */
    @Override
    public String toString() {
        return name + ", " + age;
    }

    /**
     * Compares two Person objects by name. This method satisfies the Comparable interface implementation requirements.
     *
     * @param p Person to compare
     * @return int result of the comparison
     */
    @Override
    public int compareTo(Person p) {
        if (this.name == null && p.name != null) {
            return -1;
        } else if (this.name != null && p.name == null) {
            return 1;
        }
        try {
            assert this.name != null; // Throws an AssertionError if this.name is null
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        int toReturn = this.name.compareTo(p.name);
        if (toReturn == 0) {
            return this.compareToAge(p);
        }
        return toReturn;
    }

    /**
     * Compares two Person objects by age
     *
     * @param p Person to compare
     * @return int result of the comparison
     */
    public int compareToAge(Person p) {
        if (this.age < p.age) {
            return -1;
        } else if (this.age > p.age) {
            return 1;
        }
        return this.compareTo(p);
    }

}
