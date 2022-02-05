import java.util.Stack;

/**
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 * <p>
 * Good explanation:
 * https://www.youtube.com/watch?v=VNbkzsnllsU
 */
public class LargestRectangleInHistogram {

    /**
     * Height of the largest rectangle is equals to the height of one of the bars.
     * <p>
     * We move from left looking at each height.
     * Each new height at index i introduces possible new rectangle with height[i].
     * Rectangle with height[i] is done when we encountered height[j],
     * where height[j] < height[i], i< j.
     * <p>
     * A height is popped from stack when a smaller height is seen.
     * When a height is popped, we calculate the area with the popped height.
     * <p>
     * We mantaining monotonically increasing stack.
     *
     * @param heights histogram
     * @return largest rectangle area
     */
    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i <= heights.length; i++) {
            // if current element >= then previous in stack
            // we can continue previous rectangle
            if (stack.isEmpty() || (i != heights.length && heights[i] >= heights[stack.peek()])) {
                stack.push(i);
            } else {
                // current height is less than previous max
                // we pop previous max
                int doneRectangle = stack.pop();
                maxArea = Math.max(maxArea, heights[doneRectangle] * (stack.isEmpty() ? i : i - 1 - stack.peek()));
                // decrease i and try to add height[i] to stack in next step
                // it will be added or will complete another rectangle
                i--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] array = {2, 1, 5, 6, 2, 3};
        // answer 10
        int largestRectangle = largestRectangleArea(array);
        System.out.println(largestRectangle);
    }
}
