package cz.kul.prime.detector;

import org.springframework.stereotype.Component;

/**
 * Implementation of PrimeDetector with simple but quite slow algorithm.
 * 
 * It just try to divide x by all numbers from interval &lt;2; x/2&gt;.
 */
@Component
public class SimplePrimeDetector implements PrimeDetector {

    @Override
    public boolean isPrime(long x) {
        if (x <= 1) return false;
        if (x <= 3) return true;
        for (int i = 2; i <= x / 2; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }

}
