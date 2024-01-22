package cz.kul.prime;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.StringUtils;

import cz.kul.prime.cli.ApplicationParams;
import cz.kul.prime.cli.CLI;
import cz.kul.prime.detector.PrimeDetector;
import cz.kul.prime.detector.StandardPrimeDetector;
import cz.kul.prime.xlsx.Coordinates;
import cz.kul.prime.xlsx.WorkbookNumberSource;
import cz.kul.prime.xlsx.XLSXCellIterator;

@Configuration
@ComponentScan("cz.kul.prime")
public class AppConfig {

    private static final String PRIME_DETECTOR_BEAN_SUFFIX = "PrimeDetector";

    private static ApplicationParams params;

    public static void setApplicationParams(ApplicationParams params) {
        AppConfig.params = params;
    }

    @Bean
    public ApplicationParams params() {
        return AppConfig.params;
    }

    @Bean
    public PrimeDetector primeDetector(ApplicationParams params, ApplicationContext ctx) {
        if (StringUtils.isEmpty(params.getDetector())) {
            return new StandardPrimeDetector();
        }
        String beanName = params.getDetector() + PRIME_DETECTOR_BEAN_SUFFIX;
        Object detector = ctx.getBean(beanName);
        return (PrimeDetector) detector;
    }

    @Bean
    public PrimeFinder primeFinder(@Qualifier("primeDetector") PrimeDetector primeDetector) {
        return new PrimeFinder(primeDetector);
    }

    @Bean
    @Lazy
    public NumberSource numberSource(ApplicationParams params) {
        int COLUMN_INDEX_WITH_DATA = 1;
        List<Coordinates> coordinates = Arrays.asList(Coordinates.getForColumn(COLUMN_INDEX_WITH_DATA));
        Iterator<Cell> iterator = new XLSXCellIterator(params.getFileName(), coordinates);
        return new WorkbookNumberSource(iterator);
    }

    @Bean
    public CLI cli(PrimeFinder finder, ApplicationParams params, ApplicationContext ctx) {
        CLI cli = new CLI(finder, params, System.out, ctx);
        return cli;
    }

}
