package tests;

import org.junit.Test;

/**
 * Test given by Salesforce team
 */
public class GivenTest extends BaseTest {
    
    @Test
    public void testGivenExample() throws Exception {
        String[] input =  { "DEPEND TELNET TCPIP NETCARD\n",
                            "DEPEND TCPIP NETCARD\n",
                            "DEPEND NETCARD TCPIP\n",
                            "DEPEND DNS TCPIP NETCARD\n",
                            "DEPEND BROWSER TCPIP HTML\n",
                            "INSTALL NETCARD\n",
                            "INSTALL TELNET\n",
                            "INSTALL foo\n",
                            "REMOVE NETCARD\n",
                            "INSTALL BROWSER\n",
                            "INSTALL DNS\n",
                            "LIST\n",
                            "REMOVE TELNET\n",
                            "REMOVE NETCARD\n",
                            "REMOVE DNS\n",
                            "REMOVE NETCARD\n",
                            "INSTALL NETCARD\n",
                            "REMOVE TCPIP\n",
                            "REMOVE BROWSER\n",
                            "REMOVE TCPIP\n",
                            "LIST\n",
                            "END\n"};
        

        String[] expectedOutput =  {    "DEPEND TELNET TCPIP NETCARD\n",
                                        "DEPEND TCPIP NETCARD\n",
                                        "DEPEND NETCARD TCPIP\n",
                                        "TCPIP depends on NETCARD, ignoring command\n",
                                        "DEPEND DNS TCPIP NETCARD\n",
                                        "DEPEND BROWSER TCPIP HTML\n",
                                        "INSTALL NETCARD\n",
                                        "Installing NETCARD\n",
                                        "INSTALL TELNET\n",
                                        "Installing TCPIP\n",
                                        "Installing TELNET\n",
                                        "INSTALL foo\n",
                                        "Installing foo\n",
                                        "REMOVE NETCARD\n",
                                        "NETCARD is still needed\n",
                                        "INSTALL BROWSER\n",
                                        "Installing HTML\n",
                                        "Installing BROWSER\n",
                                        "INSTALL DNS\n",
                                        "Installing DNS\n",
                                        "LIST\n",
                                        "NETCARD\n",
                                        "TCPIP\n",
                                        "TELNET\n",
                                        "foo\n",
                                        "HTML\n",
                                        "BROWSER\n",
                                        "DNS\n",
                                        "REMOVE TELNET\n",
                                        "Removing TELNET\n",
                                        "REMOVE NETCARD\n",
                                        "NETCARD is still needed\n",
                                        "REMOVE DNS\n",
                                        "Removing DNS\n",
                                        "REMOVE NETCARD\n",
                                        "NETCARD is still needed\n",
                                        "INSTALL NETCARD\n",
                                        "NETCARD is already installed\n",
                                        "REMOVE TCPIP\n",
                                        "TCPIP is still needed\n",
                                        "REMOVE BROWSER\n",
                                        "Removing BROWSER\n",
                                        "Removing TCPIP\n",
                                        "Removing HTML\n",
                                        "REMOVE TCPIP\n",
                                        "TCPIP is not installed\n",
                                        "LIST\n",
                                        "NETCARD\n",
                                        "foo\n",
                                        "END\n"};

        runTest(input, expectedOutput);
    }
}
