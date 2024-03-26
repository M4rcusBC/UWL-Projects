import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class Assign03Test {

    public static void main(String[] args) {

        PersonList p1 = loadData();

        // System.out.println("** PRINTING LIST BY NAME **\n");
        // printListByName(pl);

        // System.out.println("\n\n** PRINTING LIST BY AGE **\n");
        // printListByAge(pl);

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
            FileReader fr = new FileReader(new File("/workspaces/UWL-Projects/Spring2024/CS220/Assignments/Assign03/people.txt"));
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();

            while (line != null) {
                String[] data = line.split(", ");
                String name = data[0];
                int age = Integer.parseInt(data[1]);
                Person p = new Person(name, age);
                toReturn.add(p);
                line = br.readLine();
            }

            br.close();

        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return toReturn;
    }

    /**
     * Prints the list in alphabetical order by name.
     * 
     * @param pl PersonList
     */
    // private static void printListByName(PersonList pl) {

    // }

    /**
     * Prints the list in numerical order by age
     * 
     * @param pl
     */
    // private static void printListByAge(PersonList pl) {

    // }

}
