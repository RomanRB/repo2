package project;

import org.junit.*;

import static org.junit.Assert.*;

public class FinderTest {
    static Finder finder;
    @BeforeClass
    public static void setUp() throws Exception {
        finder = new Finder();
        finder.setContent("acbdzghybdz");
    }

    @AfterClass
    public static void tearDown() throws Exception {
    }

    @Test
    public void lookForLongest() {
        finder.lookForLongest();
        String expected = "bda";
        String actual = Sequence.longestSequence;
        assertEquals(expected, actual);
    }

    @Test
    public void largestCommonPrefix() {
    }

    @Test
    public void searchResult() {
    }
}