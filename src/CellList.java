/**
 * Written By Honorine Ishimwe Twahirwa, ID 40300265
 * COMP 249
 * Assignment 3
 * Due Dec 1, 2024, 9:00 am
 */
import java.util.NoSuchElementException;

public class CellList implements Cloneable {

    /**
     * inner class
     */
    private class CellNode {
        /**
         * two attributes cellphone object and a pointer to cell node object
         */
        private CellPhone phone;
        private CellNode next;

        /**
         * default construcotr
         */
        private CellNode() {
            phone = null;
            next = null;
        }

        /**
         * parametarized constructor
         *
         * @param phone
         * @param next
         */
        public CellNode(CellPhone phone, CellNode next) {
            this.phone = phone;
            this.next = next;
        }

        /**
         * copy constructor
         *
         * @param C
         */
        public CellNode(CellNode C) {
            this.phone = C.phone;
            this.next = C.next;
        }

        /**
         * clone the object, this will do a shallow copy
         *
         * @return
         * @throws CloneNotSupportedException
         */
        protected CellNode clone() {
            try {
                CellNode newNode = (CellNode) super.clone(); // Perform a shallow copy
                if (this.phone != null) {
                    newNode.phone = this.phone.clone(); // Clone the CellPhone object if it's not null
                }
                if (this.next != null) {
                    newNode.next = this.next.clone(); // Recursively clone the rest of the list
                }
                return newNode;
            } catch (CloneNotSupportedException e) {
                System.out.println("Clone not supported: " + e.getMessage());
                return null; // Return null in case of a cloning error
            }
        }

        /**
         * @param phone
         */
        public void setPhone(CellPhone phone) {
            this.phone = phone;
        }

        /**
         * @param next
         */
        public void setNext(CellNode next) {
            this.next = next;
        }

        /**
         * @return
         */
        public CellPhone getPhone() {
            return phone;
        }

        /**
         * @return
         */
        public CellNode getNext() {
            return next;
        }

    }


    /**
     * attribute head that will be pointer to the first node
     * attribute size that will contain the size of the list
     */
    private CellNode head;
    private int size;

    /**
     * default constructor
     */
    public CellList() {
        head = null;
        size = 0;
    }

    /**
     * copy constructor
     *
     * @param list object
     */
    public CellList(CellList list) {
        if (list.head != null) {
            this.head = list.head.clone(); // Use the corrected clone method
        } else {
            this.head = null;
        }
        this.size = list.size; // Copy the size attribute
    }

    /**
     * method to add a node at the start of the list.
     *
     * @param phone
     */
    public void addToStart(CellPhone phone) {
        // no special case needed

        CellNode newNode = new CellNode(phone, head);
        head = newNode;
        size++;
        //System.out.println(phone);
        //showContents();
    }

    /**
     * this method adds a node at the index provided
     *
     * @param phone
     * @param index
     */
    public void insertAtIndex(CellPhone phone, int index) {
        CellNode t = head;
        int i = 0;
        //1st special case
        try {
            if (index < 0 || index > size - 1) {
                throw new NoSuchElementException("Index incorrect, index has to be between 0 and " + (size-1));

            }
            //2nd special case
            if (head == null) {
                CellNode newNode = new CellNode(phone, head);
                head = newNode;
                size++;
                //3rd special case and can be combined with 2nd
            } else if (head != null && index == 0) {
                CellNode newNode = new CellNode(phone, head);
                head = newNode;
                size++;


            }
            //4th special case which is insert at the end
            else if (t.next == null && index == size - 1) {
                CellNode newNode = new CellNode(phone, head);
                t.next = newNode;
                size++;
            } else {
                while (i < index && t.next != null) {
                    t = t.next;
                    i++;
                }
                //new node
                CellNode newNode = new CellNode(phone, t.next);
                t.next = newNode;
                size++;

            }
        } catch (NoSuchElementException e) {
            System.out.println("Invalid index, Exiting program!" + e.getMessage());
            System.exit(0);
        }


    }

    /**
     * this method deletes from index provided
     *
     * @param index
     */

    public void deleteFromIndex(int index) {
        //local variables

        int i = 0;
        CellNode t = head;
        try {
            if (index < 0 || index > size - 1) {
                throw new NoSuchElementException();
            }
            //1st special case
            if (head == null) {
                System.out.println("List is empty");
                return;
            }
            // 2nd case if the list only has the head
            if (index == 0 && head.next == null) {
                head = null;
                size--;
                return;
            }


            while (i < index - 1 && t.next != null) {
                t = t.next;
                i++;

            }
            // 3rd case if index is at the end of the list
            if (index == size - 1) {
                t.next = null;
            } else {
                t.next = t.next.next;
                size--;
            }

        } catch (NoSuchElementException e) {
            System.out.println("Invalid index, Exiting program!" + e.getMessage());
            System.exit(0);
        }

    }

    /**
     * delete node at the start, can be written in a shorter version but prefer this for context and as a beginner
     */
    public void deleteFromStart() {
        //1st special case

        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        // 2nd special case
        if (head.next == null) {
            head = null;
            return;
        } else {
            head = head.next;
            size--;
        }
    }

    /**
     * replaces a node at a certain index
     *
     * @param phone
     * @param index
     */
    public void replaceAtIndex(CellPhone phone, int index) {
        int i = 0;
        CellNode t = head;

        //if index is not valid
        if (index < 0 || index > size - 1) {
            System.out.println("Index not valid");
            return;
        }
        //1st case if head is null
        if (head == null) {
            return;
        }
        //2nd case
        if (head.next == null) {
            CellNode newNode = new CellNode(phone, null);
            head = newNode;
        } else {
            while (i < index - 1 && t.next != null) {
                t = t.next;
                i++;
            }
            //3rd special case
            if (index == size - 1) {
                CellNode newNode = new CellNode(phone, null); // Last node points to null
                t.next = newNode;

            } else {
                CellNode newNode = new CellNode(phone, t.next.next);
                t.next = newNode;
            }

        }

    }

    /**
     * this method is in charge of finding a node that has the same serial number entered. (can add some printing info as well)
     * @param serNum
     * @return the pointer
     */
    public CellNode find(long serNum) {
        //local variables
        int iterationCount = 0;
        CellNode t = head;


            while (t != null) {
                iterationCount++;
                if (serNum == t.phone.getSerialNum()) {
                    System.out.println("The number of iterations: " + iterationCount);
                    System.out.println(t);
                    return t;
                }

                t = t.next;
            }
            System.out.println("Serial number not found");
            return null;


    }

    /**
     * this method simply check if a node contain a certain serialNum
     * @param serNum
     * @return true or false
     */
    public boolean contains(long serNum){
        CellNode t = head;

        while (t != null) {
            if (serNum == t.phone.getSerialNum()) return true;
            t = t.next;
        }
        return false;
    }

    /**
     * this method show the contents of the list.
     */
    public void showContents() {
        CellNode t = head;
        System.out.println("The current size of the list is size: " + size + ". Here are the contents of the list");

        while (t != null){
         System.out.print(t.getPhone() + "------->");
         t=t.next;
        }
        System.out.println("X"); // Indicate the end of the list
    }

    /**
     * method to compare objects
     * @param list
     * @return
     */

    public boolean equals(CellList list){

        if (this == list) return true;
        if (list.getClass() != getClass()) return false;
        CellList other = (CellList) list;
        if (this.head == null && other.head == null) return true;
        if (this.head == null || other.head == null) return false;

        CellNode t1 = this.head;
        CellNode t2 = other.head;

        while (t1 != null && t2 != null){
            if ( !(t1.phone.getBrand().equals(t2.phone.getBrand())) ||
                    t1.phone.getYear() != t2.phone.getYear() ||
                    t1.phone.getPrice() != t2.phone.getPrice()) return false;
            t1 = t1.next;
            t2 = t2.next;
        }
          if (t1 != null || t2 != null) return false;
    return true;
    }
}
