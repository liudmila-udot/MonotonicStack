import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/buildings-with-an-ocean-view/
 */
public class BuildingWithAnOceanView {

    /**
     * For each building if it has building with >= height to the right,
     * it doesn't see an ocean.
     *
     * @param heights
     * @return array of buildings with ocean view
     */
    public static int[] findBuildings(int[] heights) {
        List<Integer> ret = new ArrayList<>();

        Stack<Integer> stack = new Stack<>();
        for (int i = heights.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() < heights[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                ret.add(i);
            }
            stack.add(heights[i]);
        }
        return ret.stream()
            .sorted()
            .mapToInt(i -> i)
            .toArray();
    }

    public int[] findBuildingsOptimized(int[] heights) {
        int n = heights.length;
        List<Integer> list = new ArrayList<>();
        int maxHeight = -1;

        for (int current = n - 1; current >= 0; --current) {
            // If there is no building higher (or equal) than the current one to its right,
            // push it in the list.
            if (maxHeight < heights[current]) {
                list.add(current);

                // Update max building till now.
                maxHeight = heights[current];
            }
        }

        // Push building indices from list to answer array in reverse order.
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            answer[i] = list.get(list.size() - 1 - i);
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] array = {1, 3, 2, 4};
        // answer [3]
        int[] oceanViewBuildings = findBuildings(array);
        System.out.println(Arrays.toString(oceanViewBuildings));
    }
}
