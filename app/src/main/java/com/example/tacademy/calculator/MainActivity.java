package com.example.tacademy.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultText;
    Button btn;
    int val1, val2, operator, result;
    boolean isOperated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultText = (TextView) findViewById(R.id.resultText);

        for (int i = 0; i < 16; i++) {
            btn = (Button) findViewById(R.id.btn1 + i);
            btn.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() != R.id.btnGo) {
            sendText(v.getId());
        } else {    // 계산 버튼이 눌렸을 때
            if (!isOperated) {
                if (!resultText.getText().toString().equals("")) {
                    val2 = Integer.parseInt(resultText.getText().toString());

                    switch (operator) {
                        case -2:
                            result = val1 / val2;
                            setHint(result);
                            break;
                        case -1:
                            result = val1 - val2;
                            setHint(result);
                            break;
                        case 1:
                            result = val1 + val2;
                            setHint(result);
                            break;
                        default:
                            result = val1 * val2;
                            setHint(result);
                            break;
                    }
                    val1 = result;
                    isOperated = true;
                }
                changeBtnCondition(true);
            }
        }
    }

    private void sendText(int i) {
        if (i != R.id.btnC) {
            btn = (Button) findViewById(i);
            try {       // 숫자가 눌렸을 때
                Integer.parseInt(btn.getText().toString());
                resultText.append(btn.getText());
            } catch (Exception e) {     // 연산자가 눌렸을 때
                isOperated = false;
                changeBtnCondition(false);
                if (!resultText.getText().toString().equals("")) {
                    val1 = Integer.parseInt(resultText.getText().toString());
                }
                setHint(val1);

                switch (i) {        // 연산 버튼이 눌렸을 때 operator에서 값을 꺼내, 상황 별로 처리하기 위한 셋팅
                    case R.id.btnD:
                        operator = -2;
                        break;
                    case R.id.btnM:
                        operator = -1;
                        break;
                    case R.id.btnP:
                        operator = 1;
                        break;
                    default:
                        operator = 2;
                        break;
                }
            }
        } else {        // C가 눌렸을 때
            setHint(0);
            val1 = val2 = operator = 0;
            changeBtnCondition(true);
        }
    }

    private void changeBtnCondition(boolean b) {        // 연산자 활성 or 비활성화
        btn = (Button) findViewById(R.id.btnD);
        btn.setEnabled(b);

        btn = (Button) findViewById(R.id.btnM);
        btn.setEnabled(b);

        btn = (Button) findViewById(R.id.btnP);
        btn.setEnabled(b);

        btn = (Button) findViewById(R.id.btnT);
        btn.setEnabled(b);
    }

    private void setHint(int hint) {        // 힌트 셋팅
        resultText.setText("");
        resultText.setHint(hint + "");
    }

}
