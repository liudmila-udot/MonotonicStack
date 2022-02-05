import java.util.Arrays;
import java.util.Stack;

/**
 * https://leetcode.com/problems/number-of-visible-people-in-a-queue/
 *
 * All the values of heights are unique.
 */
public class NumberOfVisiblePeopleInQueue {

    /**
     * Person can see in front of him all the elements removed from
     * mono-decreasing stack to add him
     * and next element in mono-decreasing stack (if exists).
     * Mono-decreasing stack in terms of from bottom to top.
     *
     * @param heights
     * @return
     */
    public static int[] canSeePersonsCount(int[] heights) {
        int[] ret = new int[heights.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = heights.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[i] > stack.peek()) {
                stack.pop();
                ret[i]++;
            }
            if (!stack.isEmpty()){
                ret[i]++;
            }
            stack.push(heights[i]);
        }
        return ret;
    }

    public static int[] canSeePersonsCountQuadratic(int[] heights) {
        int[] ret = new int[heights.length];
        for (int i = 0; i < heights.length - 1; i++) {
            int height = heights[i];
            // minion always can see next minion
            int canSee = 1;
            int maxFromTheRightSoFar = heights[i + 1];
            for (int j = i + 2; j <= heights.length - 1; j++) {
                if (maxFromTheRightSoFar >= height) {
                    break;
                }
                if (heights[j] > maxFromTheRightSoFar) {
                    canSee++;
                }
                maxFromTheRightSoFar = Math.max(maxFromTheRightSoFar, heights[j]);
            }
            ret[i] = canSee;
        }
        return ret;
    }



    public static void main(String[] args) {
        int[] heights = {5, 1, 2, 3, 10};
        int[] canSeeQuadratic = canSeePersonsCountQuadratic(heights);
        System.out.println(Arrays.toString(canSeeQuadratic));
        int[] canSee = canSeePersonsCount(heights);
        System.out.println(Arrays.toString(canSee));
    }
}
