package com.example.orderjavaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView ord, toPay, cremeValue, dispName;
    EditText custName;
    int cups = 1;
    Button plusButton;
    CheckBox creme;
    boolean cremeAns;
    int price = 5;

    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ord = (TextView) findViewById(R.id.order_value);

        plusButton = (Button) findViewById(R.id.plus_button);
        creme = (CheckBox) findViewById(R.id.checked_value);

        custName = (EditText) findViewById(R.id.customer_name);


    }

    public void incrementByOne(View view) {
        if (cups>=100)
        {
            Toast.makeText(this,"order cannot be more than 100 cups!!!",Toast.LENGTH_SHORT).show();
            return;
        }
        cups += 1;
        ord.setText("" + cups);
        createOrder(cups);


    }

    public void decrementByOne(View view) {
        if (cups==1)
        {
            Toast.makeText(this,"order cannot be less than 1 cup!!!",Toast.LENGTH_SHORT).show();
            return;
        }
        cups -= 1;
        ord.setText("" + cups);
        createOrder(cups);
    }

    public int createOrder(int cups) {

        price = cups * 5;
        if (cremeAns) {
            price = cups*7;
        }

        return price;
    }

    public void createOrder(View view) {


        //Toast maketoast(context,"welcome!!!",Toast.LENGTH_SHORT).show();

        name = custName.getText().toString();

String message=createSummary(name,cremeAns,price);
        String subject="The java order for ";
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, subject+ name);
        intent.putExtra(Intent.EXTRA_TEXT,message );


        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }
private String createSummary(String name,boolean cremeAns, int price)
{
    String mess="Name: "+name;
    name+="\nAdd whipped cream? "+cremeAns;
    name+="\nQuntity "+cups;
    name+="\nTotal price "+price;
    return name;
}

    public void addCremePrice(View view) {
        cremeAns = creme.isChecked();
        if (cremeAns) {
            price = cups*7;
        } else
            price = cups*5;
    }
}
