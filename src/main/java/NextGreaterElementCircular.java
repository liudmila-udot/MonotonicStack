import java.util.Arrays;
import java.util.Stack;

/**
 * Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]), return the next greater number for every element in nums.
 * <p>
 * The next greater number of a number x is the first greater number to its traversing-order next in the array,
 * which means you could search circularly to find its next greater number.
 * If it doesn't exist, return -1 for this number.
 * <p>
 * https://leetcode.com/problems/next-greater-element-ii/
 */
public class NextGreaterElementCircular {

    public static int[] nextGreaterElements(int[] array) {
        int[] ret = new int[array.length];
        Stack<Integer> stack = new Stack<>();
        // go 2 times - like doubled array to reflect circular nature
        for (int i = 2 * array.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= array[i % array.length]) {
                stack.pop();
            }
            ret[i % array.length] = !stack.isEmpty() ? stack.peek() : -1;
            stack.push(array[i % array.length]);
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 3};
        // Output: [2, 3, 4, -1, 4]
        int[] nextGreater = nextGreaterElements(array);
        System.out.println(Arrays.toString(nextGreater));
    }
}
