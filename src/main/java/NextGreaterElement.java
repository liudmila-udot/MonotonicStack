import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElement {

    /**
     * Next greater element
     * @param array input array
     * @return Array with value of the closest element from the right greater than current element.
     * -1 - if there's no such element.
     */
    public static int[] nextGreater(int[] array) {
        int[] ret = new int[array.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = array.length -1; i >= 0; i--){
            //Before: [5,4,2,1]
            // To push 3, we need to pop smaller (or equal) elements first
            // After: [5,4,3]
            // Mono-decreasing Stack, because new top - array[i] should be less than current top.
            // Mono-decreasing stack in terms of from bottom to top.
            while (!stack.isEmpty() && stack.peek() <= array[i]){
                stack.pop();
            }
            ret[i] = !stack.isEmpty() ? stack.peek() : -1;
            stack.push(array[i]);
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] array = {5, 1, 2, 3, 10};
        // answer [10, 2, 3, 10, -1]
        int[] nextGreater = nextGreater(array);
        System.out.println(Arrays.toString(nextGreater));
    }
}
