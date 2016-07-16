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
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LCDScreen =(TextView)findViewById(R.id.LCDTextView);
        LCDScreen.setText("");



    }
    public void pushNumber(View view)
    {
        String LCDCurrentString = LCDScreen.getText().toString();
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
    public void evaluateExpression(View view)
    {
        String expr = LCDScreen.getText().toString().trim();
        Evaluator evaluator = new Evaluator();

        try
        {
            String result = evaluator.evaluate(expr);
            Double dResult = Double.parseDouble(result);
            long lResult = 0l;

            if(dResult.equals(Math.ceil(dResult)))
            {
                String str = dResult.toString();
                lResult = Long.parseLong(str);
                result = String.valueOf(lResult);

            }
            LCDScreen.setText(result);
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
