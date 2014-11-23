package seglo;


import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Unit tests for NameConsumer
 */
public class NameConsumerTests extends TestCase {
    public NameConsumerTests(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(NameConsumerTests.class);
    }

    public void testShouldMatchANameThatIsInInputNames() {
        // arrange
        Set<String> inputNames = new HashSet<>(Arrays.asList("bob"));
        NameConsumer nc = new NameConsumer(inputNames);

        // act
        nc.accept("bob");

        // assert
        Assert.assertEquals("matched names length is 1", 1, nc.matchedNames.size());
        Assert.assertTrue("matched name is bob", nc.matchedNames.contains("bob"));
    }

    public void testShouldNotMatchANameThatIsNotInInputNames() {
        Set<String> inputNames = new HashSet<>(Arrays.asList("bob"));
        NameConsumer nc = new NameConsumer(inputNames);

        nc.accept("george");

        Assert.assertEquals("matched names length is 0", 0, nc.matchedNames.size());
    }

    public void testShouldCountNumberOfComparedNames() {
        Set<String> inputNames = new HashSet<>();
        NameConsumer nc = new NameConsumer(inputNames);

        nc.accept("bonnie");
        nc.accept("clyde");

        Assert.assertEquals("total names analyzed is 2", 2, nc.totalNames);
    }

    public void testShouldAddNameConsumersProperly() {
        Set<String> inputNames = new HashSet<>(Arrays.asList("bob"));
        NameConsumer nc = new NameConsumer(inputNames);
        NameConsumer nc2 = new NameConsumer(inputNames);

        nc.accept("george");
        nc2.accept("bob");
        nc2.combine(nc);

        Assert.assertEquals("matched names length is 1", 1, nc.matchedNames.size());
        Assert.assertEquals("total names analyzed is 2", 2, nc.totalNames);
        Assert.assertEquals("number of unique input names is 1", 1, nc.inputNames.size());
    }
}