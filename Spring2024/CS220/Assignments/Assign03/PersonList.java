package Spring2024.CS220.Assignments.Assign03;

import java.util.Iterator;

/**
 * A class representing a list of people using a singly linked list
 * structured by name and age. Includes inner classes for the PersonNode and iterators.
 *
 * @author Marcus Clements
 * @see PersonNode
 * @since 2024-04-08
 */
public class PersonList {

    // This program DOES use sentinel nodes.
    PersonNode headNameNode;
    PersonNode tailNameNode;
    PersonNode headAgeNode;
    PersonNode tailAgeNode;

    /**
     * Constructor for the PersonList class; initializes the head and tail nodes
     */
    public PersonList() {
        headNameNode = new PersonNode(null); // sentinel node
        headAgeNode = new PersonNode(null); // sentinel node
        tailNameNode = headNameNode;
        tailAgeNode = headAgeNode;
    }

    /**
     * Adds a person to the list by their name and age
     *
     * @param p Person to add
     */
    public void add(Person p) {
        addByName(p);
        addByAge(p);
    }

    /**
     * Adds a person to the list alphabetically by name
     *
     * @param p Person to add
     */
    private void addByName(Person p) {
        PersonNode newNode = new PersonNode(p);
        if (headNameNode.data == null) {
            headNameNode = newNode;
            tailNameNode = newNode;
        } else if (headNameNode.data.compareTo(p) == 0) {
            addByAge(p); // if the name is the same, add by age
        } else {
            PersonNode current = headNameNode;
            while (current.hasNextName() && current.getNextName().data.compareTo(p) < 0) { // while the next name is valued less than the current name
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

    /**
     * Adds a person to the list by age
     *
     * @param p Person to add
     */
    private void addByAge(Person p) {
        if (headAgeNode.data == null) {
            headAgeNode = new PersonNode(p);
            tailAgeNode = headAgeNode;
            return;
        }
        PersonNode newNode = new PersonNode(p);
        PersonNode current = headAgeNode;
        while (current.hasNextAge() && current.getNextAge().data.compareToAge(p) < 0) { // while the next age is valued less than the current age
            current = current.getNextAge();
        }
        if (current.hasNextAge()) {
            newNode.nextAgeNode = current.nextAgeNode;
            current.nextAgeNode = newNode;
        } else {
            current.nextAgeNode = newNode;
            tailAgeNode = newNode;
        }
    }

    /**
     * Returns an iterator for the list by name
     *
     * @return Iterator<Person>
     */
    public Iterator<Person> iterator() {
        return new NameIterator();
    }

    /**
     * Returns an iterator for the list by age
     *
     * @return Iterator<Person>
     */
    public Iterator<Person> ageIterator() {
        return new AgeIterator();
    }

    /**
     * Iterator for the list by name
     */
    private class NameIterator implements Iterator<Person> {
        private PersonNode current;

        /**
         * Constructor for the NameIterator
         */
        public NameIterator() {
            current = headNameNode;
        }

        /**
         * Checks if there is another name in the list
         *
         * @return boolean
         */
        public boolean hasNext() {
            return current.hasNextName();
        }

        /**
         * Returns the next name in the list
         *
         * @return Person
         */
        public Person next() {
            if (hasNext()) {
                current = current.getNextName();
                return current.data;
            }
            return null;
        }
    }

    /**
     * Iterator for the list by age
     */
    private class AgeIterator implements Iterator<Person> {
        private PersonNode current;

        /**
         * Constructor for the AgeIterator
         */
        public AgeIterator() {
            current = headAgeNode;
        }

        /**
         * Checks if there is another age in the list
         *
         * @return boolean
         */
        public boolean hasNext() {
            return current.hasNextAge();
        }

        /**
         * Returns the next age in the list
         *
         * @return Person
         */
        public Person next() {
            if (hasNext()) {
                current = current.getNextAge();
                return current.data;
            }
            return null;
        }
    }

    /**
     * Inner, private class for the PersonNode
     */
    private class PersonNode implements Comparable<PersonNode> {
        private final Person data;
        private PersonNode nextNameNode;
        private PersonNode nextAgeNode;

        /**
         * Constructor for the PersonNode
         *
         * @param p Person to add
         */
        public PersonNode(Person p) {
            this.data = p;
            this.nextNameNode = null;
            this.nextAgeNode = null;
        }

        /**
         * Checks if there is another name in the list
         *
         * @return boolean
         */
        public boolean hasNextName() {
            return nextNameNode != null;
        }

        /**
         * Checks if there is another age in the list
         *
         * @return boolean
         */
        public boolean hasNextAge() {
            return nextAgeNode != null;
        }

        /**
         * Returns the next name in the list, even if null
         *
         * @return PersonNode
         */
        public PersonNode getNextName() {
            return nextNameNode;
        }

        /**
         * Returns the next age in the list, even if null
         *
         * @return PersonNode
         */
        public PersonNode getNextAge() {
            return nextAgeNode;
        }

        /**
         * Compares two PersonNodes
         *
         * @param p PersonNode to compare
         * @return int result of the comparison
         */
        @Override
        public int compareTo(PersonNode p) {
            return data.compareTo(p.data);
        }

        /**
         * Compares two PersonNodes by age
         *
         * @param p PersonNode to compare
         * @return int result of the comparison
         */
        public int compareToAge(PersonNode p) {
            return this.data.compareToAge(p.data);
        }

    }

}
