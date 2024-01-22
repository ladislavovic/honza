package cz.kul.prime;

import java.util.List;
import java.util.stream.Collectors;

import cz.kul.prime.detector.PrimeDetector;

/**
 * It can find primes in a set of numbers.
 */
public class PrimeFinder {

    private PrimeDetector detector;

    /**
     * Create new instance of the class.
     * 
     * @param primeDetector
     *            by this parameter you choose particular {@link PrimeDetector}
     *            implementation
     */
    public PrimeFinder(PrimeDetector primeDetector) {
        this.detector = primeDetector;
    }

    /**
     * It takes all numbers from given source and find primes.
     * 
     * @param source
     *            source of numbers
     * @return all primes found in the source
     */
    public List<Long> findPrimes(NumberSource source) {
        List<Long> result = source.getNumbers().stream()
                .filter(x -> detector.isPrime(x))
                .collect(Collectors.toList());
        return result;
    }

    public PrimeDetector getDetector() {
        return detector;
    }

}
