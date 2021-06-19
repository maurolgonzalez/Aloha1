package tests;

import org.junit.Test;

/**
 * Tests created by me
 */
public class MyTest extends BaseTest {
    
    @Test
    public void testSimpleInstall() throws Exception {
        String[] input =  { "DEPEND TELNET TCPIP NETCARD\n",
                            "DEPEND TCPIP NETCARD\n",
                            "INSTALL TELNET\n",
                            "END\n",
                            };
        

        String[] expectedOutput =  {    "DEPEND TELNET TCPIP NETCARD\n",
                                        "DEPEND TCPIP NETCARD\n",
                                        "INSTALL TELNET\n",
                                        "Installing NETCARD\n",
                                        "Installing TCPIP\n",
                                        "Installing TELNET\n",
                                        "END\n",
                                        };

        runTest(input, expectedOutput);
    }

    @Test
    public void testDetectCrossDepend() throws Exception {
        String[] input =  { "DEPEND TELNET TCPIP NETCARD\n",
                            "DEPEND TCPIP NETCARD\n",
                            "DEPEND NETCARD TCPIP\n",
                            "DEPEND TCPIP TELNET\n",
                            "END\n",
                            };
        

        String[] expectedOutput =  {    "DEPEND TELNET TCPIP NETCARD\n",
                                        "DEPEND TCPIP NETCARD\n",
                                        "DEPEND NETCARD TCPIP\n",
                                        "TCPIP depends on NETCARD, ignoring command\n",
                                        "DEPEND TCPIP TELNET\n",
                                        "TELNET depends on TCPIP, ignoring command\n",
                                        "END\n",
                                        };

        runTest(input, expectedOutput);
    }
}