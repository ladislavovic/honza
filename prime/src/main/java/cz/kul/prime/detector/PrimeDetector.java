package cz.kul.prime.detector;

/**
 * Can detect if a nubmer is a prime or not.
 */
public interface PrimeDetector {

    /**
     * Test if the given number is a prime. Prime is a natural number greater than 1 that
     * has no positive divisors other than 1 and itself
     * 
     * Examples:
     * <ul>
     * <li>isPrime(-1) : false</li>
     * <li>isPrime(0) : false</li>
     * <li>isPrime(1) : false</li>
     * <li>isPrime(2) : true</li>
     * <li>isPrime(3) : true</li>
     * <li>isPrime(4) : false</li>
     * <li>isPrime(5) : true</li>
     * </ul>
     * 
     * @param x
     *            the given number
     * @return true if the given number is prime, false if not
     */
    boolean isPrime(long x);

}
