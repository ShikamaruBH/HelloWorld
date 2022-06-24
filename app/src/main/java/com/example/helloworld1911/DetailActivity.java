package com.example.helloworld1911;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.helloworld1911.databinding.ActivityDetailBinding;
import com.example.helloworld1911.databinding.ActivityMainBinding;

public class DetailActivity extends AppCompatActivity {
    private static final int REQ_CODE = 123;
    ActivityDetailBinding binding;
    MyListViewModel lvModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        lvModel = new ViewModelProvider(this).get(MyListViewModel.class);

        Intent intent = getIntent();

        if (intent != null) {
            String data = intent.getStringExtra("number");
            binding.etNumber.setText(data);
        }

        binding.btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();
                intent1.putExtra("number", binding.etNumber.getText().toString());
                intent1.putExtra("index", intent.getIntExtra("index", 0));
                setResult(REQ_CODE, intent1);
                finish();
            }
        });
    }
}