package problem2;
import problem2.ListOfStrings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ListOfStrings.
 * Testing strategy:
 *   - Test empty list (edge case)
 *   - Test single element (boundary)
 *   - Test multiple elements (normal case)
 *   - Test each method independently
 */
class ListOfStringsTest {

    // We'll reuse these lists across tests
    private ListOfStrings emptyList;
    private ListOfStrings singleList;
    private ListOfStrings multiList;

    /**
     * @BeforeEach runs before every test method.
     * Gives each test a fresh, known state.
     */
    @BeforeEach
    void setUp() {
        emptyList = new ListOfStrings();

        singleList = new ListOfStrings();
        singleList.add("Alice");

        multiList = new ListOfStrings();
        multiList.add("Alice");
        multiList.add("Bob");
        multiList.add("Charlie");
        multiList.add("Bob");       // duplicate on purpose for hasDuplicates tests
        multiList.add("Di");        // short string for filterLargerThan tests
    }

    // ------------------------------------------------------------------
    // isEmpty()
    // ------------------------------------------------------------------

    @Test
    void testIsEmpty_onEmptyList_returnsTrue() {
        assertTrue(emptyList.isEmpty());
    }

    @Test
    void testIsEmpty_onNonEmptyList_returnsFalse() {
        assertFalse(singleList.isEmpty());
        assertFalse(multiList.isEmpty());
    }

    // ------------------------------------------------------------------
    // size()
    // ------------------------------------------------------------------

    @Test
    void testSize_emptyList_returnsZero() {
        assertEquals(0, emptyList.size());
    }

    @Test
    void testSize_singleElement_returnsOne() {
        assertEquals(1, singleList.size());
    }

    @Test
    void testSize_multipleElements_returnsCorrectCount() {
        // multiList has: Alice, Bob, Charlie, Bob, Di = 5 elements
        assertEquals(5, multiList.size());
    }

    // ------------------------------------------------------------------
    // contains()
    // ------------------------------------------------------------------

    @Test
    void testContains_emptyList_returnsFalse() {
        assertFalse(emptyList.contains("Alice"));
    }

    @Test
    void testContains_elementExists_returnsTrue() {
        assertTrue(multiList.contains("Alice"));
        assertTrue(multiList.contains("Bob"));
        assertTrue(multiList.contains("Di"));
    }

    @Test
    void testContains_elementNotPresent_returnsFalse() {
        assertFalse(multiList.contains("Zara"));
    }

    @Test
    void testContains_caseSensitive() {
        // "alice" != "Alice" — Java Strings are case-sensitive
        assertFalse(multiList.contains("alice"));
    }

    // ------------------------------------------------------------------
    // containsAll()
    // ------------------------------------------------------------------

    @Test
    void testContainsAll_emptyOtherList_returnsTrue() {
        // Every element of an empty list is trivially in this list
        assertTrue(multiList.containsAll(emptyList));
    }

    @Test
    void testContainsAll_subsetPresent_returnsTrue() {
        ListOfStrings subset = new ListOfStrings();
        subset.add("Alice");
        subset.add("Bob");
        assertTrue(multiList.containsAll(subset));
    }

    @Test
    void testContainsAll_elementMissing_returnsFalse() {
        ListOfStrings other = new ListOfStrings();
        other.add("Alice");
        other.add("Zara");          // Zara is NOT in multiList
        assertFalse(multiList.containsAll(other));
    }

    // ------------------------------------------------------------------
    // filterLargerThan()
    // ------------------------------------------------------------------

    @Test
    void testFilterLargerThan_removesLongStrings() {
        // multiList: Alice(5), Bob(3), Charlie(7), Bob(3), Di(2)
        // maxLength = 4: keep Bob(3), Bob(3), Di(2). Remove Alice(5), Charlie(7)
        IListOfStrings filtered = multiList.filterLargerThan(4);
        assertFalse(filtered.contains("Alice"));
        assertFalse(filtered.contains("Charlie"));
        assertTrue(filtered.contains("Bob"));
        assertTrue(filtered.contains("Di"));
    }

    @Test
    void testFilterLargerThan_doesNotModifyOriginal() {
        multiList.filterLargerThan(3);
        // Original list should be unchanged
        assertEquals(5, multiList.size());
        assertTrue(multiList.contains("Alice"));
    }

    @Test
    void testFilterLargerThan_emptyList_returnsEmpty() {
        IListOfStrings result = emptyList.filterLargerThan(5);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFilterLargerThan_maxLengthZero_returnsEmpty() {
        // No strings have length <= 0
        IListOfStrings result = multiList.filterLargerThan(0);
        assertTrue(result.isEmpty());
    }

    // ------------------------------------------------------------------
    // hasDuplicates()
    // ------------------------------------------------------------------

    @Test
    void testHasDuplicates_emptyList_returnsFalse() {
        assertFalse(emptyList.hasDuplicates());
    }

    @Test
    void testHasDuplicates_singleElement_returnsFalse() {
        assertFalse(singleList.hasDuplicates());
    }

    @Test
    void testHasDuplicates_withDuplicate_returnsTrue() {
        // multiList contains "Bob" twice
        assertTrue(multiList.hasDuplicates());
    }

    @Test
    void testHasDuplicates_noDuplicates_returnsFalse() {
        ListOfStrings unique = new ListOfStrings();
        unique.add("Alice");
        unique.add("Bob");
        unique.add("Charlie");
        assertFalse(unique.hasDuplicates());
    }

    // ------------------------------------------------------------------
    // removeDuplicates()
    // ------------------------------------------------------------------

    @Test
    void testRemoveDuplicates_emptyList_returnsEmpty() {
        IListOfStrings result = emptyList.removeDuplicates();
        assertTrue(result.isEmpty());
    }

    @Test
    void testRemoveDuplicates_noDuplicates_returnsSameElements() {
        IListOfStrings result = singleList.removeDuplicates();
        assertEquals(1, result.size());
        assertTrue(result.contains("Alice"));
    }

    @Test
    void testRemoveDuplicates_withDuplicates_keepsOnlyFirst() {
        // multiList: Alice, Bob, Charlie, Bob, Di
        // After removeDuplicates: Alice, Bob, Charlie, Di (4 elements)
        IListOfStrings result = multiList.removeDuplicates();
        assertEquals(4, result.size());
        assertTrue(result.contains("Alice"));
        assertTrue(result.contains("Bob"));
        assertTrue(result.contains("Charlie"));
        assertTrue(result.contains("Di"));
        assertFalse(result.hasDuplicates());
    }

    @Test
    void testRemoveDuplicates_doesNotModifyOriginal() {
        multiList.removeDuplicates();
        // Original still has 5 elements including the duplicate Bob
        assertEquals(5, multiList.size());
        assertTrue(multiList.hasDuplicates());
    }
}
