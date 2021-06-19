package tests;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;

import main.Main;


/**
 * Unit Test runner: capture stdin/stdout for testing
 */
public abstract class BaseTest {


    private ByteArrayOutputStream testOut;

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
        
    }

    protected void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    protected String getOutput() {

        return testOut.toString();
    }

    @After
    public void restoreSystemInputOutput() {
        System.setIn(System.in);
        System.setOut(System.out);
    }

    protected void runTest(String[] input, String[] expectedOutput) throws Exception {

        provideInput(String.join("", input));

        Main.main(new String[0]);
        String output = getOutput();

        String[] arrOut = output.trim().split("\\n");

        assertEquals("Different length of output\n", expectedOutput.length, arrOut.length);

        for (int i=0; i < arrOut.length; i++) {
            assertEquals("Bad output in pos [" + i + "]:\n",
                            expectedOutput[i].trim(), arrOut[i].trim());

        }

    }
}
