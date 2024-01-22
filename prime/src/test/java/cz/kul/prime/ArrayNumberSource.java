package cz.kul.prime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayNumberSource implements NumberSource {

    private List<Long> numbers = new ArrayList<Long>();

    public ArrayNumberSource(List<Long> numbers) {
        this.numbers = numbers;
    }

    public ArrayNumberSource(Long... num) {
        this.numbers = Arrays.asList(num);
    }

    @Override
    public List<Long> getNumbers() {
        return numbers;
    }

}
