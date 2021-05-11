package sg.edu.rp.c346.id20011280.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText name;
        EditText number;
        EditText size;
        CheckBox smoking;
        DatePicker dp;
        TimePicker tp;
        Button reserve;
        Button reset;

        name = findViewById(R.id.Name);
        number = findViewById(R.id.Pnumber);
        size = findViewById(R.id.size);
        smoking = findViewById(R.id.smoking);
        dp = findViewById(R.id.datePicker);
        tp = findViewById(R.id.timePicker);
        reserve = findViewById(R.id.ReserveBtn);
        reset = findViewById(R.id.Resetbtn);

        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               if(name.getText().toString().trim().length() != 0 &&
               number.getText().toString().trim().length() != 0 &&
               size.getText().toString().trim().length() != 0)
               {
                   String message = "";

                   message += "Name : " + name.getText().toString() + "\n";
                   message += "Number : " + number.getText().toString() + "\n";


                   if(Integer.parseInt(size.getText().toString()) < 1 || Integer.parseInt(size.getText().toString()) > 5)
                   {
                       Toast.makeText(MainActivity.this,"Total size has to be between 1 to 5 ",Toast.LENGTH_LONG).show();
                   }
                   else
                   {
                       message += "size : " + size.getText().toString() + "\n";
                   }


                   if(smoking.isChecked())
                   {
                       message += "You have selected the smoking area " + "\n";
                   }
                   else
                   {
                       message += "You have selected the non smoking area " + "\n";
                   }


                   DecimalFormat df = new DecimalFormat("00");
                   message += "Date chosen : " + dp.getDayOfMonth() + "/" + dp.getMonth() + "/" + dp.getYear() + "\n";
                   message += "Time chosen : " + tp.getCurrentHour() + ":" + df.format(tp.getCurrentMinute()) + "\n";
                   Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();

               }
               else
               {
                   Toast.makeText(MainActivity.this,"Please key in the relevant information",Toast.LENGTH_LONG).show();

               }
            }

        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                name.setText("");
                number.setText("");
                size.setText("");
                smoking.setChecked(false);
                dp.updateDate(2020,0,1);
                tp.setCurrentHour(0);
                tp.setCurrentMinute(0);
            }
        });
        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute)
            {
                if(tp.getCurrentHour() < 8)
                {
                    tp.setCurrentHour(8);
                    Toast.makeText(MainActivity.this,"We are opened at 8am",Toast.LENGTH_LONG).show();
                }
                else if(tp.getCurrentHour() > 20)
                {
                    tp.setCurrentHour(20);
                    Toast.makeText(MainActivity.this,"We are closed at 9pm",Toast.LENGTH_LONG).show();

                }
            }
        });


    }
}