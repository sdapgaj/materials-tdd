package examples.greet;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Greeter {

    public static String greet(String ...names) {
        if (names.length == 0 || (names.length == 1 && names[0] == null)) {
            return "Hello my friend.";
        }

        Map<Boolean, List<String>> groupedNames = Stream.of(names)
                .collect(Collectors.partitioningBy(Greeter::isAllCapital));

        List<String> namesInCapitals = groupedNames.get(true);
        String screamingHello;
        switch (namesInCapitals.size()) {
            case 0:
                screamingHello = "";
                break;
            case 1:
                screamingHello = "HELLO " + namesInCapitals.get(0) + "!";
                break;
            default:
                Integer lastNameIndex = namesInCapitals.size() - 1;
                screamingHello = "HELLO " + String.join(", ", namesInCapitals.subList(0, lastNameIndex)) + " AND " + namesInCapitals.get(lastNameIndex) + "!";
        }

        List<String> regularNames = groupedNames.get(false);
        String regularHello;
        switch (regularNames.size()) {
            case 0:
                regularHello = "";
                break;
            case 1:
                regularHello = "Hello " + regularNames.get(0) + ".";
                break;
            default:
                Integer lastNameIndex = regularNames.size() - 1;
                regularHello = "Hello " + String.join(", ", regularNames.subList(0, lastNameIndex)) + " and " + regularNames.get(lastNameIndex) + ".";
        }

        String delimiter = !regularHello.isEmpty() && !screamingHello.isEmpty() ? " " : "";
        return regularHello + delimiter + screamingHello;
    }

    private static boolean isAllCapital(String text) {
        return text.matches("^[A-Z]*$");
    }

}
