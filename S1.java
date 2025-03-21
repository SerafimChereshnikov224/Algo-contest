package org.example;

import java.util.Scanner;

public class S1 {

    public static String solve(String str) {

        int indexR = str.indexOf('R');
        int indexM = str.indexOf('M');

        return indexR < indexM ? "Yes" : "No";
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        System.out.println(solve(str));
    }
}
