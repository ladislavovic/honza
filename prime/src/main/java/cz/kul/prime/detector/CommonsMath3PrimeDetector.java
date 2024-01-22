package cz.kul.prime.detector;

import org.apache.commons.math3.primes.Primes;
import org.springframework.stereotype.Component;

/**
 * Implebentation based on {@link Primes} class from commons-math3 library.
 * 
 * The limitation of this implememntation is it is for integer only, not for long.
 */
@Component
public class CommonsMath3PrimeDetector implements PrimeDetector {

    /**
     * Test if a number is a prime. The limitation of this implementation is it is for int
     * only. Not for long. If The given number can not be converted to int an exception is
     * throwen.
     * 
     * @param x
     *            the givenn number.
     * @return true if the number is a prime
     * @throws ArithmeticException
     *             when a given number can not be cast to int without overflow
     */
    @Override
    public boolean isPrime(long x) {
        int intX = Math.toIntExact(x);
        return Primes.isPrime(intX);
    }

}
