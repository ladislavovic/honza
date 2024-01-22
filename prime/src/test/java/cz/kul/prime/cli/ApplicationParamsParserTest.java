package cz.kul.prime.cli;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ApplicationParamsParserTest {

    @Test
    public void testParseParameters() {
        ApplicationParamsParser parser = new ApplicationParamsParser();

        {
            ApplicationParams expected = new ApplicationParams(null, false, null);
            ApplicationParams actual = parser.parseParameters(new String[] {});
            assertEquals(expected, actual);
        }

        {
            ApplicationParams expected = new ApplicationParams("dir/file.xlxs", false, null);
            ApplicationParams actual = parser.parseParameters(new String[] { "dir/file.xlxs" });
            assertEquals(expected, actual);
        }

        {
            ApplicationParams expected = new ApplicationParams("dir/file.xlxs", true, "simple");
            String[] args = { "dir/file.xlxs", "-detector:simple", "--brief", "-foo", "--bar" };
            ApplicationParams actual = parser.parseParameters(args);
            assertEquals(expected, actual);
        }
    }

}
