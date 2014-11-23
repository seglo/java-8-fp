package seglo;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.stream.Stream;

/**
 * NameFinder
 *
 * Given a path to a text file of names and a list of names, NameFinder will identify all matching names.
 *
 * Streams: https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html
 * Reduction Tutorial: https://docs.oracle.com/javase/tutorial/collections/streams/reduction.html
 */
public class NameFinder {
    private String namesPath;
    private HashSet<String> names;

    public NameFinder(String namesPath, HashSet<String> names) {
        this.names = names;
        this.namesPath = namesPath;
    }

    public NameConsumer collect() throws IOException {
        Stream<String> nameStream;
        try {
            nameStream = Files.lines(Paths.get(namesPath), Charset.defaultCharset());
        } catch (IOException e) {
            System.out.format("could not load file %s", namesPath);
            throw e;
        }

        NameConsumer result = nameStream
                .parallel()
                .collect(() -> new NameConsumer(names), NameConsumer::accept, NameConsumer::combine);

        printSummary(result);
        return result;
    }

    private void printSummary(NameConsumer consumer) {
        /*
        Word list searched for is: bob, jane, foo
        11 words analyzed. 2 matches found:

        foo
        jane
         */
        System.out.format("Word list searched for is: %s\n", String.join(", ", consumer.inputNames));
        System.out.format("%d words analyzed. %d matches found:\n\n",
                consumer.matchedNames.size(), consumer.totalNames);
        consumer.matchedNames.forEach(System.out::println);
    }
}
