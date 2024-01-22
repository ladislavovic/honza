package cz.kul.prime.detector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetectorTestHelper {

    private PrimeDetector detector;

    public DetectorTestHelper(PrimeDetector detector) {
        this.detector = detector;
    }

    public void test() {
        testPositiveNumbersDetection();
        testNegativeNumbersDetection();
        test_10_000_th_prime();
    }

    private void testPositiveNumbersDetection() {
        List<Long> result = new ArrayList<>();
        for (long x = 0; x <= 100; x++) {
            if (detector.isPrime(x)) {
                result.add(x);
            }
        }
        assertEquals(getPrimesFromZeroToOneHundred(), result);
    }

    private void testNegativeNumbersDetection() {
        assertFalse(detector.isPrime(-1));
        assertFalse(detector.isPrime(-1000));
    }

    private void test_10_000_th_prime() {
        int N = 10_000;
        long N_TH_PRIME = 104_729;
        for (int x = 0, counter = 0; true; x++) {
            if (detector.isPrime(x)) {
                if (++counter == N) {
                    assertEquals(N_TH_PRIME, x);
                    break;
                }
            }
        }
    }

    private List<Long> getPrimesFromZeroToOneHundred() {
        List<Long> primes = new ArrayList<>();
        primes.addAll(Arrays.asList(2L, 3L, 5L, 7L, 11L, 13L, 17L, 19L, 23L));
        primes.addAll(Arrays.asList(29L, 31L, 37L, 41L, 43L, 47L, 53L, 59L));
        primes.addAll(Arrays.asList(61L, 67L, 71L, 73L, 79L, 83L, 89L, 97L));
        return primes;
    }

}
