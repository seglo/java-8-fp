package seglo;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Demonstrate usage of pipelining in Java 8 streams
 */
public class PipeliningTests {
    @Test
    public void limitFirstTwoEvenSquares() {
        Predicate<Integer> foo = (num) -> num % 2 == 0;
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> twoEvenSquares = numbers.stream()
                        .filter(foo) // Class::foo
                        .map(n -> n * n)
                        .limit(2)
                        .collect(Collectors.toList());

        assertEquals(twoEvenSquares.size(), 2);
        assertArrayEquals(new Integer[]{4, 16}, twoEvenSquares.toArray());
    }

    @Test
    public void limitFirstTwoEvenSquaresImperative() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);

        List<Integer> twoEvenSquares = new ArrayList<Integer>();
        for(int i = 0; i < numbers.size(); i++) {
            Integer num = numbers.get(i);

            if (num % 2 != 0)
                continue;

            twoEvenSquares.add(num*num);

            if (twoEvenSquares.size() == 2)
                break;
        }

        assertEquals(twoEvenSquares.size(), 2);
        assertArrayEquals(new Integer[]{4, 16}, twoEvenSquares.toArray());
    }
}
