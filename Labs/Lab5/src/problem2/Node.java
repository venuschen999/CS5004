package problem2;
/**
 * A Node is one element in linked list
 *
 * Each Node holds:
 *   - data  : the String stored at this position
 *   - next  : a reference (pointer) to the next Node, or null if this is the last one
 *
 * e.g.
 *   Node("Alice") --> Node("Bob") --> Node("Carol") --> null
 *
 * This class is package-private because it is an internal
 * implementation detail. Users of the ADT should not interact with Nodes.
 */
class Node {
    String data;
    Node next;

    /**
     * Creates a new Node with the given data, pointing to null by default.
     */
    Node(String data) {
        this.data = data;
        this.next = null;
    }
}
