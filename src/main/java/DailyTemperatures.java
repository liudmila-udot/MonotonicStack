import java.util.Arrays;
import java.util.Stack;

/**
 * https://leetcode.com/problems/daily-temperatures/
 * <p>
 * Given an array of integers temperatures represents the daily temperatures,
 * return an array answer such that answer[i] is the number of days you have to wait after
 * the ith day to get a warmer temperature.
 * If there is no future day for which this is possible, keep answer[i] == 0 instead.
 *
 * We are looking for the Next Greater element. We should use monotonic decreasing stack.
 */
public class DailyTemperatures {

    public static int[] dailyTemperatures(int[] temperatures) {
        int[] ret = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = temperatures.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && temperatures[i] >= temperatures[stack.peek()]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                ret[i] = stack.peek() - i;
            }
            stack.add(i);
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] array = {73, 74, 75, 71, 69, 72, 76, 73};
        // answer [1,1,4,2,1,1,0,0]
        int[] dailyTemperatures = dailyTemperatures(array);
        System.out.println(Arrays.toString(dailyTemperatures));
    }
}
