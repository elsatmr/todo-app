package problem1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class CommandLineParserTest {

  private ArrayList<String> parsedCL1 = new ArrayList<>();
  private ArrayList<String> parsedCL2 = new ArrayList<>();
  private CommandLineParser clp, clp2;
  // Checking whether options make sense is not CLP's job, it just parses
  private String[] commandLine1 = new String[] {"--csv-file", "path/to/file/tada.csv",
      "--apples", "Fuji", "--oranges", "{Cara cara}", "--pomelos"};
  private String[] commandLine2 = new String[] {"--csv-file", "path/to/file/tada.csv",
      "--apples", "Fuji", "Granny Smith"};

  @Before
  public void setUp() throws Exception {
    clp = new CommandLineParser(commandLine1);
    parsedCL1.add("--csv-file path/to/file/tada.csv");
    parsedCL1.add("--apples Fuji");
    parsedCL1.add("--oranges {Cara cara}");
    parsedCL1.add("--pomelos ");
  }

  @Test(expected = WrongNumberOfArgsException.class)
  public void badParse() throws WrongNumberOfArgsException {
    clp2 = new CommandLineParser(commandLine2);
    clp2.getFlags();
  }

  @Test
  public void getFlags() {
    assertEquals(clp.getFlags(), parsedCL1);
  }
}