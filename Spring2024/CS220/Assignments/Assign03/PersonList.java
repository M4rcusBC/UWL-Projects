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
        addByName(p);
    }

    private void addByName(Person p) {
        PersonNode newNode = new PersonNode(p);
        if (headNameNode.data == null) {
            headNameNode = newNode;
            tailNameNode = newNode;
        } else if (headNameNode.data.compareTo(p) == 0) {
            addByAge(p);
        } else {
            PersonNode current = headNameNode;
            while (current.hasNextName() && current.getNextName().data.compareTo(p) < 0) {
                current = current.getNextName();
            }
            if (current.hasNextName()) {
                newNode.nextNameNode = current.getNextName();
                current.nextNameNode = newNode;
            } else {
                current.nextNameNode = newNode;
                tailNameNode = newNode;
            }
        }
    }

    private void addByAge(Person p) {
        PersonNode newNode = new PersonNode(p);
        PersonNode current = headAgeNode;
        while (current.hasNextAge() && current.getNextAge().data.compareToAge(p) < 0) {
            current = current.getNextAge();
        }
        if (current.hasNextAge()) {
            newNode.nextAgeNode = current.getNextAge();
            current.nextAgeNode = newNode;
        } else {
            current.nextAgeNode = newNode;
            tailAgeNode = newNode;
        }
    }

    public Iterator<Person> iterator() {
        return new NameIterator();
    }

    public Iterator<Person> ageIterator() {
        return new AgeIterator();
    }

    private class NameIterator implements Iterator<Person> {
        private PersonNode current;

        public NameIterator() {
            current = headNameNode;
        }

        public boolean hasNext() {
            return current.hasNextName();
        }

        public Person next() {
            if (hasNext()) {
                current = current.getNextName();
                return current.data;
            }
            return null;
        }
    }

    private class AgeIterator implements Iterator<Person> {
        private PersonNode current;

        public AgeIterator() {
            current = headAgeNode;
        }

        public boolean hasNext() {
            return current.hasNextAge();
        }

        public Person next() {
            if (hasNext()) {
                current = current.getNextAge();
                return current.data;
            }
            return null;
        }
    }

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
            return this.data.compareToAge(p.data);
        }

    }

}
