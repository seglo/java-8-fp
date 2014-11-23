package seglo;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Unit tests for NameConsumer
 */
public class TestNameConsumer {

    @Test
    public void shouldMatchANameThatIsInInputNames() {
        // arrange
        Set<String> inputNames = new HashSet<>(Arrays.asList("bob"));
        NameConsumer nc = new NameConsumer(inputNames);

        // act
        nc.accept("bob");

        // assert
        assertEquals("matched names length is 1", 1, nc.matchedNames.size());
        assertTrue("matched name is bob", nc.matchedNames.contains("bob"));
    }

    @Test
    public void shouldNotMatchANameThatIsNotInInputNames() {
        Set<String> inputNames = new HashSet<>(Arrays.asList("bob"));
        NameConsumer nc = new NameConsumer(inputNames);

        nc.accept("george");

        assertEquals("matched names length is 0", 0, nc.matchedNames.size());
    }

    @Test
    public void shouldCountNumberOfComparedNames() {
        Set<String> inputNames = new HashSet<>();
        NameConsumer nc = new NameConsumer(inputNames);

        nc.accept("bonnie");
        nc.accept("clyde");

        assertEquals("total names analyzed is 2", 2, nc.totalNames);
    }

    @Test
    public void shouldAddNameConsumersProperly() {
        Set<String> inputNames = new HashSet<>(Arrays.asList("bob"));
        NameConsumer nc = new NameConsumer(inputNames);
        NameConsumer nc2 = new NameConsumer(inputNames);

        nc.accept("george");
        nc2.accept("bob");
        nc2.combine(nc);

        assertEquals("matched names length is 1", 1, nc.matchedNames.size());
        assertEquals("total names analyzed is 2", 2, nc.totalNames);
        assertEquals("number of unique input names is 1", 1, nc.inputNames.size());
    }
}