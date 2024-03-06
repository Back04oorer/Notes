import java.util.*;

public class Tests {
    public static void main(String[] args) {
        IteratorTest(); // Corrected method call
    }

    public static void IteratorTest() {
        System.out.println("Iterator Test started");
        String[] sentence = {"Syf", "is ", "dummy ", "person in the world!"};
        ArrayIterator<String> intListIterator = new ArrayIterator<>(sentence);
        while (intListIterator.hasNext()) { // Corrected method name
            System.out.println(intListIterator.next());
        }
        System.out.println("Iterator Test ended");
    }
}
