package org.example;

import java.util.*;

public class S4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long x = scanner.nextLong();
        long y = scanner.nextLong();
        long z = scanner.nextLong();
        long[] nums = new long[n];
        for(int i = 0;i < n;i++) {
            nums[i] = scanner.nextLong();
        }
        System.out.println(solve(n,x,y,z,nums));
    }

    private static long solve(int n, long x, long y, long z, long[] nums){

        long xy = lcm(x,y);
        long xz = lcm(x,z);
        long yz = lcm(y,z);
        long xyz = lcm(lcm(x,y),z);

        long bestXYZ = Long.MAX_VALUE;
        long[][] needs = new long[n][6];

        for(int i = 0; i < n; i++) {
            long xNeed = bestAdd(nums[i], x);
            long yNeed = bestAdd(nums[i], y);
            long zNeed = bestAdd(nums[i], z);
            long xyNeed = bestAdd(nums[i], xy);
            long xzNeed = bestAdd(nums[i], xz);
            long yzNeed = bestAdd(nums[i], yz);
            long xyzNeed = bestAdd(nums[i], xyz);

            bestXYZ = Math.min(xyzNeed,bestXYZ);
            needs[i][3] = xyNeed;
            needs[i][4] = xzNeed;
            needs[i][5] = yzNeed;
            needs[i][0] = xNeed;
            needs[i][1] = yNeed;
            needs[i][2] = zNeed;
        }

        long minCount = bestXYZ;

        if(n == 1) return  minCount;

        if(n == 2) {

            minCount = Math.min(minCount,Math.min(needs[0][0] + needs[1][5],needs[1][0] + needs[0][5]));
            minCount = Math.min(minCount,Math.min(needs[0][1] + needs[1][4],needs[1][1] + needs[0][4]));
            minCount = Math.min(minCount,Math.min(needs[0][2] + needs[1][3],needs[1][2] + needs[0][3]));

            minCount = Math.min(minCount,Math.min(needs[0][3] + needs[1][4],needs[1][3] + needs[0][4]));
            minCount = Math.min(minCount,Math.min(needs[0][4] + needs[1][5],needs[1][4] + needs[0][5]));
            minCount = Math.min(minCount,Math.min(needs[0][3] + needs[1][5],needs[1][3] + needs[0][5]));

            return minCount;
        }


        for(int i = 0; i < n-2;i++) {
            for(int j = i + 1; j < n-1; j++) {
                for(int k = j + 1; k < n; k++) {
                        minCount = Math.min(minCount, needs[i][0] + needs[j][1] + needs[k][2]);
                        minCount = Math.min(minCount, needs[i][0] + needs[j][2] + needs[k][1]);
                        minCount = Math.min(minCount, needs[i][1] + needs[j][0] + needs[k][2]);
                        minCount = Math.min(minCount, needs[i][1] + needs[j][2] + needs[k][0]);
                        minCount = Math.min(minCount, needs[i][2] + needs[j][0] + needs[k][1]);
                        minCount = Math.min(minCount, needs[i][2] + needs[j][1] + needs[k][0]);
                }
            }
        }

        for(int i = 0; i < n-2; i++) {
            for(int j = i + 1; j < n - 1;j++) {
                    minCount = Math.min(minCount, needs[i][3] + needs[j][4]);
                    minCount = Math.min(minCount, needs[i][4] + needs[j][3]);
                    minCount = Math.min(minCount, needs[i][3] + needs[j][5]);
                    minCount = Math.min(minCount, needs[i][5] + needs[j][3]);
                    minCount = Math.min(minCount, needs[i][4] + needs[j][5]);
                    minCount = Math.min(minCount, needs[i][5] + needs[j][4]);

                    minCount = Math.min(minCount, needs[i][0] + needs[j][5]);
                    minCount = Math.min(minCount, needs[i][5] + needs[j][0]);

                    minCount = Math.min(minCount, needs[i][1] + needs[j][4]);
                    minCount = Math.min(minCount, needs[i][4] + needs[j][1]);

                    minCount = Math.min(minCount, needs[i][2] + needs[j][3]);
                    minCount = Math.min(minCount, needs[i][3] + needs[j][2]);
            }
        }

        return minCount;
    }

    private static long bestAdd(long i, long d) {
        if(i % d == 0) return 0;
        return  i <= d ? (d - i) : (i/d + 1)*d - i;
    }

    private static long lcm(long a, long b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        return Math.abs(a * b) / gcd(a, b);
    }

    private static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return Math.abs(a);
    }
}
