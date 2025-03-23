package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class S3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        long[] nums = new long[n];

        for(int i = 0;i < n;i++) {
            nums[i] = scanner.nextLong();
        }

        System.out.println(solve(nums,n,m));
    }

    public static long solve(long[] nums,int n,int m) {
        long down = nums[0];
        long up = nums[1];
        long[] runs = Arrays.copyOfRange(nums,2, n);
        Arrays.sort(nums);
        long res = Long.MAX_VALUE;

        for(int from = 0; from < runs.length - m + 1; from++) {
            int to = from + m - 1;
            res = Math.min(res, Math.max(0, down - runs[from]) + Math.max(0, runs[to] - up));
        }

        return res;
    }
}
