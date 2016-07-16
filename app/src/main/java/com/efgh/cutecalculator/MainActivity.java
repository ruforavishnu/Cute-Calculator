package com.efgh.cutecalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;


public class MainActivity extends AppCompatActivity
{

    private TextView LCDScreen;
    private TextView logText;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LCDScreen =(TextView)findViewById(R.id.LCDTextView);
        logText =(TextView)findViewById(R.id.logText);

        LCDScreen.setText("");



    }
    public void pushNumber(View view)
    {

        String LCDCurrentString = LCDScreen.getText().toString();
        if(LCDCurrentString.equals("Error"))
        {
            return;
        }
        Button pushedButton = (Button) findViewById(view.getId());

        String currentPushValue = pushedButton.getText().toString().trim();
        if(currentPushValue.equals("x"))
        {
            currentPushValue = "*";
        }
        else if(currentPushValue.equals("÷"))
        {
            currentPushValue = "/";
        }

        LCDScreen.append(currentPushValue);

    }
    public void clearLCD(View view)
    {
        LCDScreen.setText("");
    }
    public void evaluateExpression(View view)
    {
        String expr = LCDScreen.getText().toString().trim();
        logText.setText(expr);
        Evaluator evaluator = new Evaluator();

        try
        {
            String result = evaluator.evaluate(expr);
            Double dResult = Double.parseDouble(result);
            logText.setText(dResult.toString());
            long lResult = 0l;

            if(dResult.equals(Math.ceil(dResult)))
            {
                int res = (int)Math.round(dResult);
                LCDScreen.setText(String.valueOf(res));

            }
            else
            {
                LCDScreen.setText(result);
            }

        }
        catch (EvaluationException e)
        {
            e.printStackTrace();
            LCDScreen.setText("Error");
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
            LCDScreen.setText("Error");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
