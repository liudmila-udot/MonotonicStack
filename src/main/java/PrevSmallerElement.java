import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * https://www.interviewbit.com/problems/nearest-smaller-element/
 */
public class PrevSmallerElement {

    public static ArrayList<Integer> prevSmaller(ArrayList<Integer> input) {
        int[] ints = prevSmaller(input.stream().mapToInt(i -> i).toArray());
        return Arrays.stream(ints).boxed().collect(Collectors.toCollection(ArrayList::new));
    }

    public static int[] prevSmaller(int[] array) {
        int[] ret = new int[array.length];
        Stack<Integer> stack = new Stack<>();
        // use monotonic increasing stack (from the bottom to top)
        // we are looking to the smaller element from the left
        for (int i = 0; i <= array.length - 1; i++) {
            while (!stack.isEmpty() && array[i] <= stack.peek()) {
                stack.pop();
            }
            ret[i] = !(stack.isEmpty()) ? stack.peek() : -1;
            stack.push(array[i]);
        }

        return ret;
    }

    public static void main(String[] args) {
        int[] array = {4, 5, 2, 10, 8};
        // answer [-1, 4, -1, 2, 2]
        int[] prevSmaller = prevSmaller(array);
        System.out.println(Arrays.toString(prevSmaller));
    }
}
