package cz.kul.prime.detector;

import org.springframework.stereotype.Component;

/**
 * Optimized prime detection. It is more effective than {@link SimplePrimeDetector}
 * 
 * Based on https://en.wikipedia.org/wiki/Primality_test
 */
@Component
public class StandardPrimeDetector implements PrimeDetector {

    @Override
    public boolean isPrime(long x) {
        if (x <= 1) return false;
        if (x <= 3) return true;
        if (x % 2 == 0 || x % 3 == 0) return false;
        for (long i = 5L; i * i <= x; i += 6) {
            if (x % i == 0 || x % (i + 2) == 0) return false;
        }
        return true;
    }

}
