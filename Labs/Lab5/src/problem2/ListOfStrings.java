package problem2;

/**
 * ListOfStrings: A concrete implementation of IListOfStrings using a linked list.
 *
 * Internal structure:
 *   - head: points to the first Node in the chain (null if list is empty)
 *
 * e.g. after add("A"), add("B"), add("C"):
 *   head --> [A | *] --> [B | *] --> [C | null]
 */
public class ListOfStrings implements IListOfStrings {

    // The entry point into our linked list. null means the list is empty.
    private Node head;

    // -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------

    /**
     * Creates a new, empty ListOfStrings.
     */
    public ListOfStrings() {
        this.head = null;  // empty list: no nodes exist yet
    }

    // -------------------------------------------------------------------------
    // helper: add an element to the end of the list.
    // -------------------------------------------------------------------------

    /**
     * Adds a String to the end of the list.
     * This is a helper used by ourselves and tests to populate the list.
     */
    public void add(String s) {
        Node newNode = new Node(s);

        if (head == null) {
            // List is empty: new node becomes the head
            head = newNode;
        } else {
            // Walk to the last node (where next == null)
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            // leave while loop when current.next == null;
            // Attach the new node at the end
            current.next = newNode;
        }
    }

    // -------------------------------------------------------------------------
    // IListOfStrings implementations
    // -------------------------------------------------------------------------

    /**
     * isEmpty: the list is empty when head is null (no nodes).
     */
    @Override
    public boolean isEmpty() {

      return head == null;
    }

    /**
     * size: walk every node and count.
     *
     * Pattern: start at head, move to .next repeatedly until null.
     */
    @Override
    public int size() {
        int count = 0;
        Node current = head;           // start at the first node

        while (current != null) {      // keep going until we fall off the end
            count++;
            current = current.next;    // move to next node
        }

        return count;
    }

    /**
     * contains: walk every node, compare the data using .equals().
     *
     * use .equals() for String comparison
     */
    @Override
    public boolean contains(String s) {
        Node current = head;

        while (current != null) {
            if (current.data.equals(s)) {
                return true;           // found it
            }
            current = current.next;
        }

        return false;                  // never found it
    }

    /**
     * containsAll: for each element in 'other', check if THIS list contains it.
     *
     * Strategy: reuse contains() method here.
     * We need to iterate over 'other' — but IListOfStrings doesn't expose nodes.
     * So we cast to ListOfStrings to access the head (package-private access).
     */
    @Override
    public boolean containsAll(IListOfStrings other) {
        // Cast to concrete type so we can traverse its internal linked list
        ListOfStrings otherList = (ListOfStrings) other;

        Node current = otherList.head;
        while (current != null) {
            if (!this.contains(current.data)) {
                return false;          // found something in other not in this
            }
            current = current.next;
        }

        return true;                   // every element of other was found in this
    }

    /**
     * filterLargerThan: build a new list containing only strings with length <= maxLength.
     *
     * Original list is not modified.
     */
    @Override
    public IListOfStrings filterLargerThan(int maxLength) {
        ListOfStrings result = new ListOfStrings();  // start with empty new list

        Node current = head;
        while (current != null) {
            if (current.data.length() <= maxLength) {
                result.add(current.data);   // keep it — short enough
            }
            current = current.next;
        }

        return result;
    }

    /**
     * hasDuplicates: for each node, check if the SAME string appears later in the list.
     *
     * Strategy: for each node at position i, scan nodes i+1 onward.
     * As soon as we find a match, return true immediately.
     */
    @Override
    public boolean hasDuplicates() {
      Node current = head;

      while (current != null) {
        // scanner starts at the node AFTER current
        Node scanner = current.next;
        while (scanner != null) {
          if (current.data.equals(scanner.data)) {
            return true;       // found a duplicate pair
          }
          scanner = scanner.next;
        }
        current = current.next;
      }

      return false;                  // no duplicates found
    }

    /**
     * removeDuplicates: build a NEW list, adding each element only if not already added.
     *
     * Strategy: walk the original list. For each element, check if our result
     * list already contains it. If not, add it. If yes, skip it.
     *
     * First occurrence is always kept; subsequent duplicates are dropped.
     */
    @Override
    public IListOfStrings removeDuplicates() {
        ListOfStrings result = new ListOfStrings();

        Node current = head;
        while (current != null) {
            if (!result.contains(current.data)) {
                result.add(current.data);  // only add if not already in result
            }
            current = current.next;
        }

        return result;
    }

    // -------------------------------------------------------------------------
    // toString — useful for debugging and printing
    // -------------------------------------------------------------------------

    /**
     * Returns a human-readable representation, e.g.: [Alice -> Bob -> Carol]
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        Node current = head;

        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }

        sb.append("]");
        return sb.toString();
    }
}
