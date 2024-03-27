package com.calcy.ios_calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.data.EvaluationValue;
import com.ezylang.evalex.parser.ParseException;

public class MainActivity extends AppCompatActivity {

    private EditText inputEditText,outputEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initializeViews();

    }

    private void initializeViews() {
        inputEditText=findViewById(R.id.inputTextField);
        outputEditText=findViewById(R.id.outputTextField);
    }

    public void buttonClicked(View view) {

         Button button =(Button)view;
         String textButton=button.getText().toString();
        String oldInputText=inputEditText.getText().toString();


         if(textButton.equalsIgnoreCase("C")){
            int length= oldInputText.length();
            String resultString=oldInputText.substring(0,length-1);
            inputEditText.setText(resultString);

         }
         else if(textButton.equalsIgnoreCase("D")){
             inputEditText.setText("");
             outputEditText.setText("");

         }
         else if(textButton.equalsIgnoreCase("X")){
             inputEditText.setText(oldInputText+"*");

         }

         else if(textButton.equalsIgnoreCase("=")){

             if(oldInputText.trim().equalsIgnoreCase("")){
                 inputEditText.setText(outputEditText.getText().toString());
                 outputEditText.setText("");
                 return;
             }
//             calculate the expression and set to answer
             try{
                 Expression expression=new Expression(oldInputText);
                 EvaluationValue result=expression.evaluate();
                 outputEditText.setText(result.getStringValue());
                 inputEditText.setText("");
             } catch (EvaluationException e) {

                 Toast.makeText(this,"Cannot evaluate expression",Toast.LENGTH_SHORT).show();
             } catch (ParseException e) {
                 Toast.makeText(this,"Invalid expression",Toast.LENGTH_SHORT).show();

             }

         }
         else{
             inputEditText.setText(oldInputText+textButton);

         }




    }
}