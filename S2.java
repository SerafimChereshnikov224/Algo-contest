package org.example;

import java.util.Scanner;
import java.util.stream.Stream;

public class S2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        System.out.println(solve(Integer.parseInt(str)));
    }

    public static int solve(int num) {
        // 1. число в двоичную систему .
        // 2. если 3 единицы возвращаем сумму степеней 2 в позициях первых трех единиц
        // 3. если меньше 3 , то вычетаем 1 и чекаем пункт 2)
        // если число стало меньше 7 то нафиг

        if(num < 7) return -1;

        int res = 0;

        var bNum = Integer.toBinaryString(num);
        int oneCount = 0;
        int position = bNum.length() - 1;
        for(char ch : bNum.toCharArray()) {
            if(ch == '1') {
                oneCount++;
                res += (int) Math.pow(2,position);
            }
            position--;
            if(oneCount == 3) return res;
        }

        return solve(num - 1);
    }
}
