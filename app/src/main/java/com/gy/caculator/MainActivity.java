package com.gy.caculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_0;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;

    private Button btn_point;// 小数点
    private Button btn_divide;// 除以
    private Button btn_multiply;// 乘以
    private Button btn_minus;// 减去
    private Button btn_pluse;// 加
    private Button btn_equal;// 等于

    private Button btn_clear;  //清空
    private Button btn_del;  //取消

    private EditText etResult;  //输入框

    private Solution solution = new Solution();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }
    private void initView(){

        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_point = (Button) findViewById(R.id.btn_point);// 小数点
        btn_divide = (Button) findViewById(R.id.btn_divide);// 除以
        btn_multiply = (Button) findViewById(R.id.btn_multiply);// 乘以
        btn_minus = (Button) findViewById(R.id.btn_minus);// 减去
        btn_pluse = (Button) findViewById(R.id.btn_pluse);// 加
        btn_equal = (Button) findViewById(R.id.btn_equal);// 等于

        btn_clear = (Button) findViewById(R.id.btn_clear);
        btn_del = (Button) findViewById(R.id.btn_del);
        etResult = (EditText) findViewById(R.id.et_result);

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);

        btn_pluse.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String str = etResult.getText().toString();
        String showStr = "";
        switch (v.getId()) {
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
                if(isGetResult){
                    showStr = ((Button) v).getText().toString();
                    isGetResult = false;
                }else {
                    if(str.equals("0")){
                        str = "";
                    }
                    showStr = str + ((Button) v).getText();
                }

                break;
            case R.id.btn_point:
                if(str.isEmpty()){
                    showStr = "0.";
                }else if(str.endsWith(".") || !isEndWidthNum(str)){
                    return;
                }else {
                    showStr = str +".";
                }
                break;
            case R.id.btn_pluse:
            case R.id.btn_minus:
            case R.id.btn_multiply:
            case R.id.btn_divide:
                isGetResult = false;
                if(!isEndWidthNum(str)){
                    return;
                }else {
                    showStr = str + ((Button) v).getText().toString();
                }

                break;
            case R.id.btn_del:
                isGetResult = false;
                if (str.length() == 1) {
                    showStr = "0";
                } else {
                    showStr = str.substring(0, str.length() - 1);
                }
                break;
            case R.id.btn_clear:
                isGetResult = false;
                showStr = "0";
                break;
            case R.id.btn_equal:
                showStr = getResult();
                break;
            default:
                break;
        }
        etResult.setText(showStr);
    }

    // 代表点击按钮 前是否 计算过 如果计算过 点击数字重新开始算 点击运算符 继续计算
    private boolean isGetResult = false;
    String getResult() {
        String str=etResult.getText().toString();
        // 2.0 支持 小数点 支持连续运算
        String  result= solution.calculatePoint(str);
        etResult.setText(result);
        isGetResult = true;

        SolutionOneImpl solutionOneImpl = new SolutionOneImpl();
        double  ccc =  solutionOneImpl.calculate("1+2+(2.1*3)+1.5+2.11+(2*3)/2");
        return result;
    }

    boolean isEndWidthNum(String str){
        if(str.isEmpty())return false;
        String lastElement  = str.substring(str.length()-1,str.length());
        try {
            Integer.parseInt(lastElement);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
