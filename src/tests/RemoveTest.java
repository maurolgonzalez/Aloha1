package tests;

import org.junit.Test;

/**
 * Tests Remove Command
 */
public class RemoveTest extends BaseTest {
    @Test
    public void testSimpleRemove() throws Exception {
        String[] input =  { "DEPEND TELNET TCPIP\n",
                            "INSTALL TELNET\n",
                            "REMOVE TELNET\n",
                            "END\n",
                            };
        

        String[] expectedOutput =  {    "DEPEND TELNET TCPIP\n",
                                        "INSTALL TELNET\n",
                                        "Installing TCPIP\n",
                                        "Installing TELNET\n",
                                        "REMOVE TELNET\n",
                                        "Removing TCPIP\n",
                                        "Removing TELNET\n",
                                        "END\n",
                                        };

        runTest(input, expectedOutput);
    }

    @Test
    public void testNotRemoveUsedComponent() throws Exception {
        String[] input =  { "DEPEND TELNET TCPIP\n",
                            "INSTALL TELNET\n",
                            "REMOVE TCPIP\n",
                            "END\n",
                            };
        

        String[] expectedOutput =  {    "DEPEND TELNET TCPIP\n",
                                        "INSTALL TELNET\n",
                                        "Installing TCPIP\n",
                                        "Installing TELNET\n",
                                        "REMOVE TCPIP\n",
                                        "TCPIP is still needed\n",                                        
                                        "END\n",
                                        };

        runTest(input, expectedOutput);
    }

    @Test
    public void testTryRemoveNotInstalledComponent() throws Exception {
        String[] input =  { "DEPEND TELNET TCPIP\n",
                            "INSTALL TELNET\n",
                            "REMOVE NETCARD\n",
                            "END\n",
                            };
        

        String[] expectedOutput =  {    "DEPEND TELNET TCPIP\n",
                                        "INSTALL TELNET\n",
                                        "Installing TCPIP\n",
                                        "Installing TELNET\n",
                                        "REMOVE NETCARD\n",
                                        "NETCARD is not installed\n",                                        
                                        "END\n",
                                        };

        runTest(input, expectedOutput);
    }
}
