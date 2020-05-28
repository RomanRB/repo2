package project.Test;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import project.Sequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;

public class SequenceTest {
    private Sequence sequence = new Sequence();
    private StringReader srForFileName = new StringReader("D:\\result1.txt");

    @After
    public void tearDown() throws Exception {
        sequence = null;
        srForFileName.close();
    }

    @Test
    public void getFileName() {
        String expected = "D:\\result1.txt";
        String actual = "";
        try {
            actual = sequence.getFileName(new BufferedReader(srForFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(expected, actual);
    }

    @Test
    public void getFileContent() {
        String expected = "11";
        String actual = "";
        try {
            actual = sequence.getFileContent(new BufferedReader(srForFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(expected, actual);
    }
}