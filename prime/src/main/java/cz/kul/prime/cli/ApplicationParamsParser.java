package cz.kul.prime.cli;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

public class ApplicationParamsParser {

    /**
     * It parse parameters from application arguments.
     * 
     * @param args
     *            application arguments
     * @return parsed parameters
     */
    public ApplicationParams parseParameters(String[] args) {
        String fileName = args.length > 0 ? args[0] : null;
        boolean briefOutput = isArgPresent(args, "brief");
        String detector = getArgValue(args, "detector");
        return new ApplicationParams(fileName, briefOutput, detector);
    }

    /**
     * It returns true if argument is present between args.
     * 
     * @param args
     *            application arguments
     * @param argName
     *            name of the argument without dash at the start. for example "foo", not
     *            "-foo" nor "--foo"
     */
    private boolean isArgPresent(String[] args, String argName) {
        // NOTE: this regex found arguments with one or two dashes at the start. E.g. -foo, --foo
        String regex = "\\-{1,2}" + Pattern.quote(argName);
        for (int i = 1; i < args.length; i++) {
            if (args[i].matches(regex)) {
                return true;
            }
        }
        return false;
    }

    /**
     * It returns value of the argument. Argument with value must have this scheme:
     * "-arg:value". If the argument is not found or value is missing, it returns null.
     * 
     * @param args
     *            application arguments
     * @param argName
     *            name of the argument without dash at the start. for example "foo", not
     *            "-foo" nor "--foo"
     * @return value of the argument or null when argument is not present or value is
     *         missing
     */
    private String getArgValue(String[] args, String argName) {
        // NOTE: this regex found arguments with value. E.g. -foo:value
        String regex = "\\-{1,2}" + Pattern.quote(argName) + ":(\\w*)";
        Pattern pattern = Pattern.compile(regex);
        for (int i = 1; i < args.length; i++) {
            Matcher matcher = pattern.matcher(args[i]);
            if (matcher.matches()) {
                String value = matcher.group(1);
                return StringUtils.isEmpty(value) ? null : value;
            }
        }
        return null;
    }

}
