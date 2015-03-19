package seglo;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class App {
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("usage: java -cp java-8-fp.jar names.txt Name1 [, Name2, ... NameN]");
            System.exit(0);
        }

        String namesPath = args[0];
        HashSet<String> names = getNameSet(Arrays.copyOfRange(args, 1, args.length));

        NameFinder nf = new NameFinder(namesPath, names);

        nf.collect();

        System.exit(0);
    }

    private static HashSet<String> getNameSet(String[] names) {
        HashSet<String> nameSet = new HashSet<>();
        Collections.addAll(nameSet, names);
        return nameSet;
    }
}