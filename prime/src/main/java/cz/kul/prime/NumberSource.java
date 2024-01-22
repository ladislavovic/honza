package cz.kul.prime;

import java.util.List;

/**
 * It provides list of the numbers.
 */
public interface NumberSource {

    /**
     * Returns list of the numbers.
     * 
     * @return list of the numbers
     */
    List<Long> getNumbers();

}
