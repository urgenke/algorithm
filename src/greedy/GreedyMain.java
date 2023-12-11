package greedy;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author : mocun
 * @since : 2023/12/2
 */
public class GreedyMain {

    public boolean searchMatrix(int[][] matrix, int target) {
        int iLen = matrix.length;
        int jLen = matrix[0].length;
        // 从右上角开始找
        int i = 0;
        int j = jLen - 1;
        while (i >= 0 && i < iLen && j >= 0 && j < jLen) {
            int v = matrix[i][j];
            if (v == target) {
                return true;
            }
            if (v > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }


    public String largestNumber(int[] nums) {
        String[] res = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(res, (s1, s2) -> (s2 + s1).compareTo((s1 + s2)));
        if (Objects.equals(res[0], "0")) {
            return "0";
        }

        return String.join("", res);
    }

    public int candy(int[] ratings) {
        int length = ratings.length;
        int res = length;

        for (int i = 0; i < length; i++) {
            if (i == 0 && i + 1 <= length - 1 && ratings[i] > ratings[i + 1]) {
                res = res + 1;
                continue;
            }

            if (i == length - 1 && i - 1 >= 0 && ratings[i] > ratings[i - 1]) {
                res = res + 1;
                continue;
            }


            if (i - 1 >= 0 && ratings[i - 1] < ratings[i]) {
                res = res + 1;
            }

            if (i + 1 <= length - 1 && ratings[i + 1] < ratings[i]) {
                res = res + 1;
            }
        }

        return res;
    }

    public int candy2(int[] ratings) {
        int[] left = new int[ratings.length];
        int[] right = new int[ratings.length];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        for(int i = 1; i < ratings.length; i++)
            if(ratings[i] > ratings[i - 1]) left[i] = left[i - 1] + 1;
        int count = left[ratings.length - 1];
        for(int i = ratings.length - 2; i >= 0; i--) {
            if(ratings[i] > ratings[i + 1]) right[i] = right[i + 1] + 1;
            count += Math.max(left[i], right[i]);
        }
        return count;
    }


    public static void main(String[] args) {
//        System.out.println(new GreedyMain().searchMatrix(new int[][]{{1, 1}}, 1));

        System.out.println(new GreedyMain().candy2(new int[]{5, 7, 8, 3, 4, 2, 1}));
        System.out.println(new GreedyMain().candy2(new int[]{1,3,2,2,1}));
    }
}
