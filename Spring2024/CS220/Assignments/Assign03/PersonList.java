import java.util.Iterator;

public class PersonList {
    
    PersonNode headNameNode;
    PersonNode tailNameNode;
    PersonNode headAgeNode;
    PersonNode tailAgeNode;

    public PersonList() {
        headNameNode = new PersonNode(null);
        headAgeNode = new PersonNode(null);
        tailNameNode = headNameNode;
        tailAgeNode = headAgeNode;
    }

    public void add(Person p) {

    }

    public void addByName(Person p) {

    }

    private void addByAge(Person p) {

    }

    public Iterator<Person> iterator() {}

    public Iterator<Person> ageIterator() {}

    private class PersonNode implements Comparable<PersonNode> {
        private Person data;
        private PersonNode nextNameNode;
        private PersonNode nextAgeNode;

        public PersonNode(Person p) {
            this.data = p;
            this.nextNameNode = null;
            this.nextAgeNode = null;
        }

        public boolean hasNextName() {
            return nextNameNode != null;
        }

        public boolean hasNextAge() {
            return nextAgeNode != null;
        }

        public PersonNode getNextName() {
            return nextNameNode;
        }

        public PersonNode getNextAge() {
            return nextAgeNode;
        }

        public int compareTo(PersonNode p) {
            return data.compareTo(p.data);
        }

        public int compareToAge(PersonNode p) {
            return data.compareToAge(p.data);
        }


    }



}
