package project20280.exercises;

import java.util.Arrays;

public class Wk1 {

    public static void q1() {
        int[] my_array = {25, 14, 56, 15, 36, 56, 77, 18, 29, 49};

        int sum = 0;
        for (int num : my_array) {
            sum += num;
        }

        double average = (double) sum / my_array.length;

        System.out.println(Arrays.toString(my_array));
        System.out.println(average);
    }


    public static void q2() {
        int [] arr = {90 , 77, 67, 55, 75, 57, 98, 17, 50, 23, 30, 100 , 34, 75, 28, 43, 14, 52, 64, 13};

        int x = 64;

        //System .out. println (" index of " + x + " : " + indexOf );
    }

    public static void main(String[] args) {
        q1();
    }
}