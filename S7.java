package org.example;

import java.util.Scanner;

public class S7 {
    static final int MOD = 998244353;
    static final int div2 = 499122177;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] nums = new int[n];
        
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        for(int i : solve(n,k,nums)) {
            System.out.println(i);
        }
    }

    private static int[] solve(int n, int k, int[] nums) {
        int[] res = new int[k];

        int[] sumA = new int[k + 1];
        sumA[0] = n;

        for (int i = 0; i < n; i++) {
            int r = 1;
            for (int j = 1; j <= k; j++) {
                r = mult(r, nums[i]);
                sumA[j] = add(sumA[j], r);
            }
        }

        int[] pows2 = new int[k + 1];
        pows2[0] = 1;
        for (int i = 1; i <= k; i++) {
            pows2[i] = mult(pows2[i - 1], 2);
        }

        int[][] Cij = new int[k + 1][k + 1];
        for (int i = 0; i <= k; i++) {
            Cij[i][0] = Cij[i][i] = 1;
            for (int j = 1; j < i; j++) {
                Cij[i][j] = add(Cij[i - 1][j - 1], Cij[i - 1][j]);
            }
        }

        for (int i = 1; i <= k; i++) {
            int A = 0;
            for (int j = 0; j <= i; j++) A = add(A, mult(Cij[i][j], mult(sumA[j], sumA[i - j])));
            A = mult(A, div2);
            int B = mult(pows2[i - 1], sumA[i]);
            res[i-1] = (A - B + MOD) % MOD;
        }
        return res;
    }

    private static int add(int x, int y) {
        return (x + y) % MOD;
    }

    private static int mult(int x, int y) {
        return (int)((long)x * y % MOD);
    }
}