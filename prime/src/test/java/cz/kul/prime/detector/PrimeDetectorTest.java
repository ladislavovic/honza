package cz.kul.prime.detector;

import org.junit.Test;

public class PrimeDetectorTest {

    @Test
    public void testSimpleDetector() {
        DetectorTestHelper helper = new DetectorTestHelper(new SimplePrimeDetector());
        helper.test();
    }

    @Test
    public void testStandardDetector() {
        DetectorTestHelper helper = new DetectorTestHelper(new StandardPrimeDetector());
        helper.test();
    }

    @Test
    public void testCommonMath3Detector() {
        DetectorTestHelper helper = new DetectorTestHelper(new CommonsMath3PrimeDetector());
        helper.test();
    }

}
