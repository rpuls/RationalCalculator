package dk.rpuls.template;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class JavaActivity extends Activity {

    private TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);
        message = (TextView)findViewById(R.id.message);
        message.setText("Hello from Java");
        Button button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(JavaActivity.this, "Button pressed", Toast.LENGTH_LONG).show();
                }
            } );
        }

    public void button2Clicked(View view) {
        message.setText("Button 2 was clicked");
        }

    }
