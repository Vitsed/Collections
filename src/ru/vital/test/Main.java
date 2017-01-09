package ru.vital.test;

import java.util.*;

/**
 * Created by Newshka on 07.12.2016.
 */
public class Main {

    public static void main(String[] args) {
//        ArrayCollection<String> myCollection = new ArrayCollection<>();
//        LinkedList<String> myCollection = new LinkedList<>();
//        List<String> myCollection = new java.util.LinkedList<>();
//        ArrayList<String > myCollection = new ArrayList<>();
        //List<String > testCol = new ArrayList<>();
        //testCol.add("test");
//        testCol.add("test");
//        testCol.add("boom");
//        testCol.add("bom");
//        testCol.add("test");
////        String [] st = {"A"};//, "E", "F", "G"}
//        myCollection.add("lol");
//        myCollection.add(null);
//        myCollection.add("tet");
//        myCollection.add("test");
//        myCollection.add("boom");
//        myCollection.add(null);
//        myCollection.add(null);
//        myCollection.add(null);
//        myCollection.add("took");
//        Object[] testToArray = myCollection.toArray();
//        for (Object  s : testToArray) {
//            System.out.println(s);
//        }
        borderLine();
//        System.out.println(myCollection.contains(null));
//        borderLine();
//        System.out.println(myCollection.indexOf(null));
//        showList(myCollection);
        borderLine();
//        System.out.println(myCollection.remove("tet") + "removeTest");
//        System.out.println(myCollection.indexOf(null));
        borderLine();
//        System.out.println(myCollection.size());
        borderLine();
        final Collection<Integer> testInstance = new LinkedList<>();
        testInstance.add(1);
      //  testInstance.add(2);
     //   testInstance.add(3);
        showList(testInstance);
//        System.out.println(myCollection.size());
//        System.out.println(myCollection.get(0));
//        System.out.println(myCollection.listIterator(0).previous());
        borderLine();
        //System.out.println(myCollection.size());

//        myCollection.remove("lool");
        //myCollection.remove(0);
        //myCollection.addAll(testCol);
        //System.out.println(myCollection.retainAll(testCol));
        //myCollection.removeAll(testCol);

        //myCollection.removeAll(testCol);
//        String[] test = myCollection.toArray(st);
//        for (String  s : test) {
//            System.out.printf("%s ", s);
//        }
//        System.out.println();
        //System.out.println(myCollection.toArray(m));
//        System.out.println(myCollection.size());
//        myCollection.clear();
//        System.out.println(myCollection.size());

        /*showList(myCollection);
        System.out.println(myCollection.size());
        System.out.println("");*/
        /*for (String s : test) {
            System.out.println(s);
        }*/
        //System.out.println(myCollection.size());
        //System.out.println(test.length);

//        Object[] test1 = myCollection.toArray();
//        for (Object s : test1) {
//            System.out.println(s);
//        }

//        final Collection<Integer> testInstance = new java.util.LinkedList<>();

//        testInstance.add(4);


        final Iterator<Integer> iter = testInstance.iterator();
        iter.next();
        iter.remove();
        iter.next();
        iter.remove();
     //   iter.next();
        showList(testInstance);
        System.out.println(testInstance.size());
    }

    static void borderLine() {
        System.out.println("----------------");
    }

    static <T>void showList(Collection<T> myCollection) {
        for (T s : myCollection) {
            System.out.println(s);
        }
    }
}
