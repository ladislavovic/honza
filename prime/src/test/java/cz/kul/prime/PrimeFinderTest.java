package cz.kul.prime;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import cz.kul.prime.detector.SimplePrimeDetector;

public class PrimeFinderTest {

    @Test
    public void testFindPrimes() {
        ArrayNumberSource arrayNumberSource = new ArrayNumberSource(1L, 2L, 3L, 4L, 5L);
        PrimeFinder primeFinder = new PrimeFinder(new SimplePrimeDetector());
        List<Long> primes = primeFinder.findPrimes(arrayNumberSource);
        assertEquals(Arrays.asList(2L, 3L, 5L), primes);
    }

}
