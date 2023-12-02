package greedy;

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

    public static void main(String[] args) {
        System.out.println(new GreedyMain().searchMatrix(new int[][]{{1,1}}, 1));
    }
}
