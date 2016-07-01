package exercise.exercise0;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by Radu.Hoaghe on 4/20/2015.
 * <p>
 * Exercise 0: Create a List (ArrayList or LinkedList), add elements to it and print all of them using ListIterator
 * for loop and foreach loop.
 */
public class Exercise0 {

    public Exercise0() {





    }

    public void iterateThroughList() {

        // TODO Exercise #0 a) Create a list (ArrayList or LinkedList) and add elements to it
        // TODO Exercise #0 a) Don't forget to specify the type of the list (Integer, String etc.)

        // TODO Exercise #0 b) Iterate through the list using ListIterator and print all its elements
        ArrayList<String> arrayList = new ArrayList <String>();
        arrayList.add("element1");
        arrayList.add("element2");
        arrayList.add("element3");
        ListIterator it = arrayList.listIterator();
        while(it.hasNext()){
            System.out.println(it.next());
            System.out.println("Previous Index is : " + it.previousIndex());
            System.out.println("Next Index is : " + it.nextIndex());
        }
        while(it.hasPrevious()) {
            System.out.println(it.previous());
            System.out.println("Previous Index is : " + it.previousIndex());
            System.out.println("Next Index is : " + it.nextIndex());
        }



        // TODO Exercise #0 c) Iterate through the list using classic for loop and print all its elements
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
        }

            // TODO Exercise #0 d) Iterate through the list using foreach loop and print all its elements
        for (String elementcurent : arrayList){
            System.out.println(elementcurent);
        }
    }

    public static void main(String[] args) {
        // TODO Exercise #0 e) Create a new instance of Exercise0 class and call the iterateThroughList() method

        Exercise0 exercise0 = new Exercise0();
        exercise0.iterateThroughList();


    }
}
