package test.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

class StreamTest {
    public static void main(String[] args) {
/*
        Iterable<String> iterable = Arrays.asList("Testing", "Iterable", "conversion", "to", "Stream");
        Stream<String> stream = StreamSupport.stream(iterable.spliterator(), false);
        stream.forEach((System.out::println));
*/

        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");

        Spliterator<String> spliterator1 = list.spliterator();
        Spliterator<String> spliterator2 = spliterator1.trySplit();

        System.out.println(spliterator1.estimateSize());
        System.out.println(spliterator1.getExactSizeIfKnown());
        System.out.println(spliterator2.estimateSize());
        System.out.println(spliterator2.getExactSizeIfKnown());

        System.out.println("============================");

        spliterator1.forEachRemaining(System.out::println);
        spliterator2.forEachRemaining(System.out::println);

        System.out.println("============================");

        if (spliterator1.hasCharacteristics(Spliterator.ORDERED)) {
            System.out.println("ORDERED");
        }

        if (spliterator1.hasCharacteristics(Spliterator.DISTINCT)) {
            System.out.println("DISTINCT");
        }

        if (spliterator1.hasCharacteristics(Spliterator.SORTED)) {
            System.out.println("SORTED");
        }

        if (spliterator1.hasCharacteristics(Spliterator.SIZED)) {
            System.out.println("SIZED");
        }

        if (spliterator1.hasCharacteristics(Spliterator.CONCURRENT)) {
            System.out.println("CONCURRENT");
        }

        if (spliterator1.hasCharacteristics(Spliterator.IMMUTABLE)) {
            System.out.println("IMMUTABLE");
        }

        if (spliterator1.hasCharacteristics(Spliterator.NONNULL)) {
            System.out.println("NONNULL");
        }

        if (spliterator1.hasCharacteristics(Spliterator.SUBSIZED)) {
            System.out.println("SUBSIZED");
        }
    }
}
