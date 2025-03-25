package org.example;

import java.util.Scanner;

public class S6 {
    // распределить точки по прямым ->

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] points = new int[n][2];
        for(int i = 0; i < n;i++) {
            points[i][0] = scanner.nextInt();
            points[i][1] = scanner.nextInt();
        }
        System.out.println(solve(n,points));
    }

    private static int solve(int n, int[][] points) {
        int maxColin = 2;
        for(int i = 0; i < n-1 && maxColin <= 4*n/6; i++) {
            int currColin = 2;
            for(int j = i + 1; j < n && maxColin <= 4*n/6; j++) {
                for(int k = 0 ;k < n;k++) {
                    if(k == i || k == j) continue;
                    if(  (points[k][0] - points[i][0]) * (points[i][1] - points[j][1]) == (points[k][1] - points[i][1]) * (points[i][0] - points[j][0])  ){
                        currColin++;
                    }
                }
                if(currColin > 4*n/6) return n - currColin;
            }
            maxColin = Math.max(currColin,maxColin);
        }
        return n/3;
    }
}
