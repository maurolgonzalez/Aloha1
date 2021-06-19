package tests;

import org.junit.Test;

/**
 * Tests Install Command
 */
public class InstallTest extends BaseTest {
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
    public void testNotInstallDependTwice() throws Exception {
        String[] input =  { "DEPEND TELNET TCPIP NETCARD\n",
                            "DEPEND TCPIP NETCARD\n",
                            "INSTALL TELNET\n",
                            "INSTALL NETCARD\n",
                            "END\n",
                            };
        

        String[] expectedOutput =  {    "DEPEND TELNET TCPIP NETCARD\n",
                                        "DEPEND TCPIP NETCARD\n",
                                        "INSTALL TELNET\n",
                                        "Installing NETCARD\n",
                                        "Installing TCPIP\n",
                                        "Installing TELNET\n",
                                        "INSTALL NETCARD\n",
                                        "NETCARD is already installed\n",
                                        "END\n",
                                        };

        runTest(input, expectedOutput);
    }

    @Test
    public void testInstallWithoutDep() throws Exception {
        String[] input =  { "INSTALL foo\n",
                            "END\n",
                            };
        

        String[] expectedOutput =  {    "INSTALL foo\n",
                                        "Installing foo\n",
                                        "END\n",
                                        };

        runTest(input, expectedOutput);
    }

    @Test
    public void testDuplicateInstall() throws Exception {
        String[] input =  { "DEPEND TELNET TCPIP NETCARD\n",
                            "DEPEND TCPIP NETCARD\n",
                            "INSTALL TELNET\n",
                            "INSTALL TELNET\n",
                            "END\n",
                            };
        

        String[] expectedOutput =  {    "DEPEND TELNET TCPIP NETCARD\n",
                                        "DEPEND TCPIP NETCARD\n",
                                        "INSTALL TELNET\n",
                                        "Installing NETCARD\n",
                                        "Installing TCPIP\n",
                                        "Installing TELNET\n",
                                        "INSTALL TELNET\n",
                                        "TELNET is already installed\n",
                                        "END\n",
                                        };

        runTest(input, expectedOutput);
    }
}
