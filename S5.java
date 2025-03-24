package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class S5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long s = scanner.nextLong();
        long[] arr = new long[n];
        for(int i = 0;i < n;i++) {
            arr[i] = scanner.nextLong();
        }
        System.out.println(solve(arr,n,s));
    }

    private static long solve(long[] arr, int n, long s) {

        long totalSum = 0;
        totalSum += ((long) n *(n+1))/2;

        long[] prefix = new long[n+1];
        for(int i = 1;i <= n;i++) {
            prefix[i] = prefix[i-1] + arr[i-1];
        }

        int right = 1;
        long[] cuts = new long[n];
        for(int left = 1; left <= n; ++left) {
            while(right <= n && prefix[right] - prefix[left-1] <= s) {
                right++;
            }
            if(right == n+1) break;
            right--;
            cuts[right] = 1;
        }

        System.out.println(Arrays.toString(cuts));
        for(int i = 1; i < n;i++) {
            totalSum += i * cuts[i] * (n-i);
        }
        return totalSum;
    }
}
