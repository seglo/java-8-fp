package seglo;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Demonstrate usage of Predicates in Java 8 streams
 */
public class PredicateTests {

    @Test
    public void oneToTenFiltersEvenNumbers() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> evenNumbers = numbers.stream()
                        .filter(n -> n % 2 == 0)
                        .collect(Collectors.toList());

        assertEquals(evenNumbers.size(), 5);
        assertArrayEquals(new Integer[]{2, 4, 6, 8, 10}, evenNumbers.toArray());
    }

    @Test
    public void oneToTenContainsOddNumbers() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        boolean containsOddNumbers = numbers.stream()
                        .anyMatch(number -> number % 2 != 0);

        assertEquals(containsOddNumbers, true);
    }

    @Test
    public void oneToTenContainsAllNumbersLessThanEleven() {
        Predicate<Integer> foo = num -> num % 2 == 0;
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        boolean allNumbersLessThanEleven = numbers.stream()
                        .allMatch(number -> number < 11);

        assertEquals(allNumbersLessThanEleven, true);
    }
}
