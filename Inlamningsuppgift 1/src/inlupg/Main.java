package inlupg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

	List<MyObject> myObjects = new ArrayList<MyObject>();
	generateObjects(myObjects);

	List<MyObject> firstStream = myObjects;
	MyObject[] taskOne = getTaskOneArray(firstStream);

	Stream<MyObject> myStream = Arrays.stream(taskOne);
	runTaskTwo(myStream);

	myStream = Arrays.stream(taskOne);
	runTaskThree(myStream);

	myStream = Arrays.stream(taskOne);
	runExtraTaskOne(myStream);

	myStream = Arrays.stream(taskOne);
	runExtraTaskTwo(myStream);
    }

    public static MyObject[] getTaskOneArray(List<MyObject> firstStream) {
	MyObject[] taskOne = firstStream
	        .stream()
	        .filter(x -> x.getValue() > 20)
	        .toArray(x -> new MyObject[x]);

	System.out.println("3.1) Number of objects with value over 20: " + taskOne.length);
	return taskOne;
    }

    public static void runTaskTwo(Stream<MyObject> myStream) {
	OptionalDouble taskTwo = myStream
	        .mapToInt(x -> x.getValue())
	        .average();

	System.out.println("3.2) Avarage value: " + taskTwo.getAsDouble());
    }

    public static void runTaskThree(Stream<MyObject> myStream) {
	List<MyObject> taskThree = myStream
	        .filter(x -> x.getBool() == true)
	        .peek(x -> x.setName("this is true"))
	        .collect(Collectors.toList());

	System.out.println("\n3.3) Changed objects on true: " + taskThree.size());
	taskThree.forEach((obj) ->
	    {
		System.out.println(obj.toString());
	    });
    }

    public static void runExtraTaskOne(Stream<MyObject> myStream) {
	List<MyObject> extraOne = myStream
	        .filter(x -> x.getBool() == false && x.getValue() < 30)
	        .peek(x -> x.setName("Winner!"))
	        .collect(Collectors.toList());

	System.out.println("\nExtra 1) Special objects: " + extraOne.size());
	extraOne.forEach((obj) ->
	    {
		System.out.println(obj.toString());
	    });
    }

    public static void runExtraTaskTwo(Stream<MyObject> myStream) {
	long extraTwo = myStream
	        .map(x -> x.getValue())
	        .distinct()
	        .count();

	System.out.println("\nExtra 2) Unique numbers: " + extraTwo);
    }

    public static void generateObjects(List<MyObject> myObjects) {
	int max = 50;

	for (int i = 0; i < max; i++) {
	    myObjects.add(new MyObject());
	}
    }

}
