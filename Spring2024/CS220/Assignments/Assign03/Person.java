

public class Person implements Comparable<Person> {
    
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + ", " + age;
    }

    @Override
    public int compareTo(Person p) {
        if (this.name == null && p.name != null){
            return -1;
        }
        else if (this.name != null && p.name == null){
            return 1;
        }
        int toReturn = this.name.compareTo(p.name);
        if (toReturn == 0) {
            return this.compareToAge(p);
        }
        return toReturn;
    }

    public int compareToAge(Person p) {
        if (this.age < p.age) {
            return -1;
        } else if (this.age > p.age) {
            return 1;
        }
        return this.compareTo(p);
    }

}
