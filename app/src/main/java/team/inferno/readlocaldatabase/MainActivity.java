package team.inferno.readlocaldatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import APIs.MyAPI;
import results.LogInResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private EditText userNameField,passwordField;
    private MyAPI api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userNameField=findViewById(R.id.username);
        passwordField=findViewById(R.id.password);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(MyAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api=retrofit.create(MyAPI.class);
    }

    public void log_in(View view) {
        String username=userNameField.getText().toString();
        String password=passwordField.getText().toString();
        Call<LogInResult>result=api.logIn(username,password);
        result.enqueue(new Callback<LogInResult>() {
            @Override
            public void onResponse(Call<LogInResult> call, Response<LogInResult> response) {
                LogInResult result=response.body();
                if (result!=null)
                Toast.makeText(MainActivity.this, result.getFound(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LogInResult> call, Throwable t) {
                Log.e("TAG",t.getMessage());
            }
        });

    }
}
