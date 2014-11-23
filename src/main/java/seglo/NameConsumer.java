package seglo;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * NameConsumer
 *
 * A stream.collect, parallel-friendly, consumer that will find all matching names in a stream given a set of input
 * names to compare against.
 */
public class NameConsumer implements Consumer<String> {
    int totalNames;
    Set<String> inputNames;
    Set<String> matchedNames = new HashSet<>();

    public NameConsumer(Set<String> inputNames) { this.inputNames = inputNames; }

    /**
     * accept accumulates results of the collect operation
     * @param name name to match
     */
    public void accept(String name) {
        totalNames++;
        if (inputNames.contains(name)) {
            matchedNames.add(name);
        }
    }

    /**
     * combine is used during parallel operations to combine collected results
     * @param other another instance of NameConsumer to combine results into
     */
    public void combine(NameConsumer other) {
        other.totalNames += totalNames;
        other.inputNames.addAll(inputNames);
        other.matchedNames.addAll(matchedNames);
    }
}
