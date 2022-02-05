# Monotonic Stack

**Monotonic stack** elements are monotonically decreasing or increasing.

If we need to pop smaller elements from the stack before pushing a new element, the stack is **decreasing** from bottom to top.
Otherwise, it's **increasing** from bottom to top.

Mono-decreasing Stack:

```
Before: [5,4,2,1]
To push 3, we need to pop smaller (or equal) elements first
After: [5,4,3]
```
5 - bottom, 3 - top element.

# Problems overview
Monotonic stack/deque is often used to implement O(N) algorithm (generally need O(N^2) otherwise).
When need push/pop from both ends, we need use monotonic deque.

This topic is generally hard, and not intuitive. It is critical to understand why we do this.

Monotonic stack problem is mostly previous/next smaller/larger problems.

**Next smaller/larger problems:**
find the right first element which smaller or greater than current element.

**Next smaller**: use monotonic increasing stack.

**Next greater**: use monotonic decreasing stack.

https://leetcode.com/discuss/general-discussion/1061744/topic-2-monotonic-stack-or-deque

## Complexity:

Example:

```Java
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
            while (!stack.isEmpty() && stack.peek() < array[i]){
                stack.pop();
            }
            ret[i] = !stack.isEmpty() ? stack.peek() : -1;
            stack.push(array[i]);
        }
        return ret;
    }
```

https://labuladong.gitbook.io/algo-en/ii.-data-structure/monotonicstack

Each element added and popped from the stack just once.
Complexity is O(N).