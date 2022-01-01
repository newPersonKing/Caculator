package com.gy.caculator;

import java.text.DecimalFormat;
import java.util.Stack;

class Solution {


    // 100 + 5*3
    public int calculate(String s) {
        int res = 0, d = 0;
        char sign = '+';
        Stack<Integer> nums = new Stack<Integer> ();
        for (int i = 0; i < s.length(); i++) {
            //加减乘除和空格的ASCII码都小于'0'
            // 累计 算数字
            if (s.charAt(i) >= '0'){
                d = d * 10 + s.charAt(i) - '0';
            }
            // 遇到运算符
            if((s.charAt(i) < '0' && s.charAt(i)!=' ') || i == s.length() - 1){
                if (sign == '+') {
                    nums.push(d);
                } else if (sign == '-') {
                    nums.push(-d);
                } else if (sign == '*' || sign == '/'){
                    int temp = sign == '*' ? nums.pop() * d : nums.pop() / d;
                    nums.push(temp);
                }
                sign = s.charAt(i);
                d = 0;
            }
        }
        for(int t : nums){
            res += t;
        }
        return res;
    }


    public String calculateA(String s) {
        double res = 0;
        String d = "";
        char sign = '+';
        Stack<Double> nums = new Stack<Double> ();
        for (int i = 0; i < s.length(); i++) {
            //加减乘除和空格的ASCII码都小于'0'
            // 累计 算数字
            if (s.charAt(i) >= '0' || s.charAt(i) == '.'){
                // 直接拼接字符串
                d = d + s.substring(i,i+1);
            }
            // 遇到运算符
            if(((s.charAt(i) < '0' && s.charAt(i)!=' ') || i == s.length() - 1)&& s.charAt(i) != '.'){
                if (sign == '+') {
                    nums.push(Double.parseDouble(d));
                } else if (sign == '-') {
                    nums.push(-Double.parseDouble(d));
                } else if (sign == '*' || sign == '/'){
                    double temp = sign == '*' ? nums.pop() * Double.parseDouble(d) : nums.pop() / Double.parseDouble(d);
                    nums.push(temp);
                }
                sign = s.charAt(i);
                d = "";
            }
        }
        for(Double t : nums){
            res += t;
        }
        int iResult  = (new Double(res)).intValue();
        DecimalFormat df;
        if(res > iResult){
             df = new DecimalFormat("0.00");
        }else {
            df = new DecimalFormat("0");
        }

        return df.format(res);
    }

}