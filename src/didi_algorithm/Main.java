package didi_algorithm;

import java.util.Scanner;

/**
 * Date: 2018/9/18 下午8:42
 * Author: qianhangkang
 * Description:
 * 给定三种类型的小球P、Q、R，每种小球的数量分别为np、nq、nr个。
 * 现在想将这些小球排成一条直线，但是不允许相同类型的小球相邻，问有多少种排列方法。
 * 如若np=2，nq=1，nr=1则共有6种排列方式：PQRP，QPRP，PRQP，RPQP，PRPQ以及PQPR。
 * 如果无法组合出合适的结果，则输出0。
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int np = scanner.nextInt();
        int nq = scanner.nextInt();
        int nr = scanner.nextInt();

        System.out.println(cal(np, nq, nr, 0) + cal(np, nq, nr, 1) + cal(np, nq, nr, 2));

    }

    /**
     * @param np
     * @param nq
     * @param nr
     * @param last last=0 if np,last =1 if nq,last =2 if nr
     * @return
     */
    private static int cal(int np, int nq, int nr, int last) {
        if (np < 0 || nq < 0 | nr < 0) {
            return 0;
        }
        if (np == 1 && nq == 0 && nr == 0 && last == 0) {
            return 1;
        } else if (np == 0 && nq == 1 && nr == 0 && last == 1) {
            return 1;
        } else if (np == 0 && nq == 0 && nr == 1 && last == 2) {
            return 1;
        } else {
            switch (last) {
                case 0:
                    return cal(np - 1, nq, nr, 1) + cal(np - 1, nq, nr, 2);
                case 1:
                    return cal(np, nq - 1, nr, 0) + cal(np, nq - 1, nr, 2);
                case 2:
                    return cal(np, nq, nr - 1, 0) + cal(np, nq, nr - 1, 1);
                default:
                    throw new IllegalArgumentException("illegal last");
            }

        }
    }
}
