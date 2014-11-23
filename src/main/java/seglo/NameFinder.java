package seglo;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Created by seglo on 22/11/14.
 */
public class NameFinder {
    public class NameConsumer implements Consumer<String> {
        private int totalNames;
        private Set<String> inputNames;
        private Set<String> matchedNames = new HashSet<String>();

        public NameConsumer(HashSet<String> names) {
            inputNames = names;
        }

        public void accept(String name) {
            totalNames++;
            if (inputNames.contains(name)) {
                matchedNames.add(name);
            }
        }

        public void combine(NameConsumer other) {
            other.totalNames += totalNames;
            other.inputNames.addAll(inputNames);
            other.matchedNames.addAll(matchedNames);
        }
    }

    private String _namesPath;
    private HashSet<String> _names;
    private NameConsumer nameConsumer;

    public NameFinder(String namesPath, HashSet<String> names) {
        _names = names;
        _namesPath = namesPath;
        nameConsumer = new NameConsumer(names);
    }

    public void collect() {
        try {
            long totalNames = 0;
            Set<String> matchedNames = Files.lines(Paths.get(_namesPath), Charset.defaultCharset())
                    .filter(name -> {
                        totalNames++;
                        _names.contains(name);
                        return _names.contains(name);
                    })
                    .collect(Collectors.toSet());
            System.out.format("There are %d lines in the file", matchedNames.size());
        } catch (IOException e) {
            System.out.format("could not load file %s", _namesPath);
        }
    }
}
