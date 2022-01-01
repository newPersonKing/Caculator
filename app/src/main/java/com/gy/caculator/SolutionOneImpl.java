package com.gy.caculator;

import java.util.Stack;

//这个只处理了  + - （）
class SolutionOneImpl {

    private Solution solution = new Solution();

    public double evaluateExpr(Stack<Object> stack) {


        // 一直循环 直到找到 第一个对应的 )
        // Evaluate the expression till we get corresponding ')'
        String needResolveStr = ""; // 前后括号中的内容
        // stack 中的类型 就是 int 类型 与 char 类型
        while (!stack.empty() && !((stack.peek() instanceof  Character ) && ((char)stack.peek()) == ')')) {

            Object sign =  stack.pop();

            needResolveStr += String.valueOf(sign);
        }
        return solution.calculatePointDouble(needResolveStr);
    }

    public double calculate(String s) {

        String operand = "";
        int n = 0;
        Stack<Object> stack = new Stack<Object>();

        for (int i = s.length() - 1; i >= 0; i--) {

            char ch = s.charAt(i);

            // 如果是数字
            if (Character.isDigit(ch)||String.valueOf(ch).equals(".")) {

                // Forming the operand - in reverse order.
                // ch - '0'代表 字符ch 的ASCIII值 减去 '0'的ASCII值 算出的就是 数字字符ch 对应的真正的数字值
                // 比如是123
                // 第一次算出的是 3 第二次 是 20 +3 第三次 是 100 + 20 + 3
//                operand = (int) Math.pow(10, n) * (int) (ch - '0') + operand;
                operand = ch +operand;
                n += 1;

            } else if (ch != ' ') {
                if (n != 0) {
                    // 遇到运算符 把之前的计算的num 推入 stack
                    stack.push(operand);
                    n = 0;
                    operand = "";

                }
                // 如果是 （ 代表 有一对括号需要执行
                if (ch == '(') {

                    double res = evaluateExpr(stack);
                    stack.pop();

                    // Append the evaluated result to the stack.
                    // This result could be of a sub-expression within the parenthesis.
                    stack.push(res);

                } else {
                    // For other non-digits just push onto the stack.
                    // 如果是其他字符 这里指运算符 包括 （）
                    stack.push(ch);
                }
            }
        }

        //Push the last operand to stack, if any.
        if (n != 0) {
            stack.push(operand);
        }

        // Evaluate any left overs in the stack.
        return evaluateExpr(stack);
    }
}