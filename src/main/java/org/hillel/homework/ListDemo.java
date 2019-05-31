package org.hillel.homework;

import java.util.*;

public class ListDemo {

    public static void main(String[] args) {

        // сгенерировать 100 000 случайных чисел, сохранить в list, посчитать максимум, количество уникальных значений

        List<Integer> randomNumbers = new ArrayList<>();
        int maxValue = 100;
        int minValue = 1;
        final int listSize = 10000;


        for (int i = 0; i < listSize; i++) {
            int element = (int) (Math.random() * (maxValue - minValue + 1)) + minValue;
            randomNumbers.add(element);
        }
        Iterator iterator = randomNumbers.listIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        HashSet<Integer> uniqueSet = new HashSet<>();
        uniqueSet.addAll(randomNumbers);
        System.out.println("The of unique number of list is: " + uniqueSet.size());

        Integer maxListElement = Collections.max(randomNumbers);
        System.out.println("This is maximum numeric of list collection: " + maxListElement);

        Integer minListElement = Collections.min(randomNumbers);
        System.out.println("This is minimum numeric of list collection: " + minListElement);
    }
}
