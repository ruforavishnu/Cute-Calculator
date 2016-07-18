package com.efgh.cutecalculator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;


public class BasicCalculatorFragment extends Fragment
{

    private TextView LCDScreen;
    private TextView logText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.basic_calculator_fragment,container,false);


        LCDScreen =(TextView)rootView.findViewById(R.id.LCDTextView);
        logText =(TextView)rootView.findViewById(R.id.logText);

        LCDScreen.setText("");


        return rootView;

    }

    public void pushNumber(View view)
    {
        Log.i("logtest", "view invoked:" + view.getClass().getName());
        /*

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

        LCDScreen.append(currentPushValue);*/

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
            Log.i("logtest", "sin45:" + evaluator.evaluate("sin(45)"));
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
