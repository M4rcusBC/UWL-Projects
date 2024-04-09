package Spring2024.CS220.Assignments.Assign03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;

/**
 * A class to test the PersonList class functionality. Reads in data from a file and prints the list by name and age.
 */
public class Assign03Test {

    /**
     * Main method to test the PersonList class
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {

        PersonList pl = loadData();

        System.out.println("** PRINTING LIST BY NAME **\n");
        printListByName(pl);

        System.out.println("\n\n** PRINTING LIST BY AGE **\n");
        printListByAge(pl);

    }

    /**
     * Reads in data from people.txt, then constructs and returns the resulting
     * PersonList
     *
     * @return PersonList
     */
    private static PersonList loadData() {

        PersonList toReturn = new PersonList();

        try {
            FileReader fr = new FileReader(("Spring2024/CS220/Assignments/Assign03/people.txt"));
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();

            while (line != null) {
                String[] data = line.split(", "); // split on comma and space
                Person p = new Person(data[0], Integer.parseInt(data[1]));
                toReturn.add(p);
                line = br.readLine();
            }

            br.close();

        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return toReturn; // return the PersonList, regardless of whether it was successfully loaded or populated
    }

    /**
     * Prints the list in alphabetical order by name.
     *
     * @param pl PersonList to print alphabetically by name
     */
    private static void printListByName(PersonList pl) {
        Iterator<Person> it = pl.iterator();
        while (it.hasNext()) { // while there are more elements
            System.out.println(it.next());
        }
    }

    /**
     * Prints the list in numerical order by age
     *
     * @param pl PersonList to print numerically by age
     */
    private static void printListByAge(PersonList pl) {
        Iterator<Person> it = pl.ageIterator();
        while (it.hasNext()) { // while there are more elements
            System.out.println(it.next());
        }
    }
}
