package cz.kul.prime.cli;

import java.io.File;
import java.io.PrintStream;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;

import cz.kul.prime.NumberSource;
import cz.kul.prime.PrimeException;
import cz.kul.prime.PrimeFinder;

/**
 * CLI - Command Line Interface of the application.
 * 
 * If you want to use this class first create instance of the class and then call
 * {@link CLI#execute()} method.
 */
public class CLI {

    private PrimeFinder primeFinder;

    private ApplicationParams params;

    private PrintStream out;

    private ApplicationContext ctx;

    public CLI(PrimeFinder primeFinder, ApplicationParams params, PrintStream out, ApplicationContext ctx) {
        this.primeFinder = primeFinder;
        this.params = params;
        this.out = out;
        this.ctx = ctx;
    }

    public void execute() {
        printAppProloque();
        validateParams(params);
        printFindingProloque();
        List<Long> primes = primeFinder.findPrimes(ctx.getBean(NumberSource.class));
        printPrimes(primes);
    }

    private void printAppProloque() {
        if (params.isBriefOutput()) return;
        out.println("Application \"Prime\"");
        out.println("Usage: java -jar prime.jar filename [-options]");
        out.println("where options are:");
        out.println("  -brief");
        out.println("      brief output, only primes are printed,");
        out.println("      nothing else");
        out.println("  -detector:detector");
        out.println("      prime detector implementation. You can");
        out.println("      choose from these values: \"simple\",");
        out.println("      \"standard\", \"commonsMath3\". The default");
        out.println("      value is \"standard\"");
    }

    private static void validateParams(ApplicationParams params) {
        if (StringUtils.isEmpty(params.getFileName())) {
            throw new PrimeException("filename argument is missing");
        }
        File file = new File(params.getFileName());
        if (!file.exists()) {
            throw new PrimeException("file \"" + params.getFileName() + "\" does not exist");
        }
    }

    private void printFindingProloque() {
        if (params.isBriefOutput()) return;
        out.println();
        out.println("Finding primes in file \"" + params.getFileName() + "\"");
        out.println("with \"" + primeFinder.getDetector().getClass().getName() + "\" implementation.");
        out.println("The found primes:");
    }

    private void printPrimes(List<Long> primes) {
        primes.forEach(x -> out.println(x));
    }

}
