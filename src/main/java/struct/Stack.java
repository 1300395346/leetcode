package struct;

import java.util.Arrays;

public class Stack<T> {
    private T[] stack; //数组
    private int top;   //栈顶指针

    public Stack() {
        this.stack = (T[]) new Object[10];  //泛型不能实例化对象，但是可以烈性转换
    }

    //判断栈是否满了
    public boolean isFull() {
        return (stack.length == this.top);
    }

    //判断栈是否为空
    public boolean isEmpty() {
        return this.top == 0;
    }

    public void push(T value) {
        if (isFull()) {
            this.stack = Arrays.copyOf(stack, 2 * stack.length); //若栈满了就进行扩容操作，将容量扩充为原来的两倍
        }

        this.stack[this.top] = value; //给top位置添加元素
        this.top++;//top指针指向下一个可用空间
    }

    //出栈操作，并返回弹出的栈顶元素
    public T pop() {
        //先判断栈是否为空
        if (isEmpty()) {
            throw new IllegalStateException("栈为空");
        }

        //弹出元素
        T ret = this.stack[this.top - 1];
        this.top--;
        return ret; //返回栈顶元素
    }

    //得到栈顶元素，但是不弹出
    public T peek() {
        //先判断栈是否为空
        if (isEmpty()) {
            throw new IllegalStateException("栈为空");
        }

        //返回栈顶元素，但是不弹出
        return this.stack[top - 1];
    }
}
