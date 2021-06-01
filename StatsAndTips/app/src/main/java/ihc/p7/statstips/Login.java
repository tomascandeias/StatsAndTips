package ihc.p7.statstips;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {
    private Button btn_login;
    private TextView txt_loginGuest;
    private TextInputEditText txtEmail;
    private EditText txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = (TextInputEditText) findViewById(R.id.textInputEditText);
        txtPass = (EditText) findViewById(R.id.editTextNumberPassword);



        btn_login = (Button) findViewById(R.id.btnLogin);
        btn_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String email = txtEmail.getText().toString();
                String pass= txtPass.getText().toString();
                if (!email.contains("@") || email.contains(" "))
                    Toast.makeText(Login.this, "You did not enter a valid Email", Toast.LENGTH_SHORT).show();
                else {
                    if (email.length() > 7 && pass.length() > 3)
                        startActivity(new Intent(Login.this, HomePage.class));
                    else if (email.length() < 8 && pass.length() > 3)
                        Toast.makeText(Login.this, "You did not enter a valid Email", Toast.LENGTH_SHORT).show();
                    else if (email.length() > 7 && pass.length() < 4)
                        Toast.makeText(Login.this, "You did not enter a valid Password", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(Login.this, "Please enter a valid email and password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        txt_loginGuest = (TextView) findViewById(R.id.textLoginGuest);
        txt_loginGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, HomePage.class));
            }
        });
    }
}