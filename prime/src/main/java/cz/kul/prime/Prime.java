package cz.kul.prime;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cz.kul.prime.cli.ApplicationParams;
import cz.kul.prime.cli.ApplicationParamsParser;
import cz.kul.prime.cli.CLI;

/**
 * Main class of the application. It makes it running.
 */
public class Prime {

    private final static Logger log = Logger.getLogger(Prime.class);
  
    public static void main(String[] args) {
        try {
            ApplicationParams params = new ApplicationParamsParser().parseParameters(args);
            runSpring(params);
        } catch (Exception e) {
            handleException(e);
        } catch (Throwable e) {
            handleException(e);
            throw e;
        }
    }

    private static void handleException(Throwable e) {
        System.out.println("An error occured: " + e.getMessage());
        log.error("An error occured", e);
    }

    private static void runSpring(ApplicationParams params) {
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext()) {
            AppConfig.setApplicationParams(params);
            ctx.register(AppConfig.class);
            ctx.refresh();
            CLI cli = ctx.getBean(CLI.class);
            cli.execute();
        }
    }

}
