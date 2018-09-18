package stack;

import java.util.Stack;

/**
 * Date: 2018/9/17 下午10:09
 * Author: qianhangkang
 * Description: 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 */
public class GetMinFromStack {

    private Stack<Integer> stackA = new Stack<>();
    private Stack<Integer> stackB = new Stack<>();


    public void push(int node) {
        stackA.push(node);
        if (stackB.isEmpty()) {
            //第一次入栈
            stackB.push(node);
        } else {
            int temp = 0;
            //push的值大于等于stackB中的最小值
            if (node >= (temp = stackB.peek())) {
                stackB.push(temp);
            } else {
                //push的值小于stacB中的最小值
                stackB.push(node);
            }
        }
    }

    public void pop() {
        stackA.pop();
        stackB.pop();
    }

    public int top() {
        return stackA.peek();
    }

    public int min() {
        return stackB.peek();
    }

}
