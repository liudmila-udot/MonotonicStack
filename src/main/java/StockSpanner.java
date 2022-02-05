import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/online-stock-span/
 * <p>
 * The span of the stock's price today is defined as the maximum number of consecutive days
 * (starting from today and going backward)
 * for which the stock price was less than or equal to today's price.
 * <p>
 * For example, if the price of a stock over the next 7 days were [100,80,60,70,60,75,85],
 * then the stock spans would be [1,1,1,2,1,4,6].
 * <p>
 * Try monotonically decreasing stack initialized from left to right.
 * Store index of array elements in stack.
 * Number of days difference between current index ana top element in decreasing stack before adding current.
 */
public class StockSpanner {

    private final List<Integer> stocks;
    private final Stack<Integer> stack;

    public StockSpanner() {
        stocks = new ArrayList<>();
        stack = new Stack<>();
    }

    public int next(int price) {
        while (!stack.isEmpty() && stocks.get(stack.peek()) <= price) {
            stack.pop();
        }
        // if stack is empty then first element in mono decreasing stack is current price
        // and for all days before price was lower
        int ret = !stack.isEmpty() ? (stocks.size() - stack.peek()) : (stocks.size() + 1);
        stack.add(stocks.size());
        stocks.add(price);
        return ret;
    }
}
