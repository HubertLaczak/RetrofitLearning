package com.example.retrofitlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button btn_getPosts;

    int switchh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        btn_getPosts = findViewById(R.id.btn_getPosts);

        switchh = 0;

        btn_getPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(switchh == 0) {
                    switchh++;
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://jsonplaceholder.typicode.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
                    Call<List<Post>> call = jsonPlaceHolderApi.getPosts();

                    call.enqueue(new Callback<List<Post>>() {
                        @Override
                        public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                            if (!response.isSuccessful()) {
                                textView.setText("Code: " + response.code());
                                return;
                            }
                            textView.setText("");

                            List<Post> posts = response.body();
                            for (Post post : posts) {
                                String content = "";
                                content += "ID: " + post.getId() + "\n";
                                content += "User ID: " + post.getUserId() + "\n";
                                content += "Title: " + post.getTitle() + "\n";
                                content += "Text: " + post.getText() + "\n\n";
                                textView.append(content);
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Post>> call, Throwable t) {
                            textView.setText(t.getMessage());
                        }
                    });
                } else {
                    switchh--;
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://my-json-server.typicode.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
                    Call<List<Demo>> call = jsonPlaceHolderApi.getDemos();

                    call.enqueue(new Callback<List<Demo>>() {
                        @Override
                        public void onResponse(Call<List<Demo>> call, Response<List<Demo>> response) {
                            if (!response.isSuccessful()) {
                                textView.setText("Code: " + response.code());
                                return;
                            }
                            textView.setText("");

                            List<Demo> demos = response.body();
                            for (Demo demo : demos) {
                                String content = "";
                                content += "ID: " + demo.getId() + "\n";
                                content += "Title: " + demo.getTitle() + "\n\n";
                                textView.append(content);
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Demo>> call, Throwable t) {
                            textView.setText(t.getMessage());
                        }
                    });






                }
            }
        });




    }
}
