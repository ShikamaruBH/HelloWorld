package com.example.helloworld1911;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.helloworld1911.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final int REQ_CODE = 123;
    private ActivityMainBinding binding;
    private MyViewModel model;
    private MyListViewModel lvModel;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        model = new ViewModelProvider(this).get(MyViewModel.class);
        lvModel = new ViewModelProvider(this).get(MyListViewModel.class);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lvModel.getArrayList().getValue());
        binding.lvCount.setAdapter(arrayAdapter);

        model.getNumber().observe(this, integer -> {
            binding.tvCount.setText("" + integer);
        });

        binding.lvCount.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                lvModel.removeNumber(i);
                arrayAdapter.notifyDataSetChanged();
                return false;
            }
        });

        binding.lvCount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("number", lvModel.getArrayList().getValue().get(i));
                intent.putExtra("index", i);
                startActivityForResult(intent, REQ_CODE);
            }
        });
//        btnUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int count = Integer.parseInt(tvCount.getText().toString());
//                tvCount.setText("" + ++count);
//            }
//        });
//
//        btnDown.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int count = Integer.parseInt(tvCount.getText().toString());
//                tvCount.setText("" + --count);
//            }
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE) {
            String number = data.getStringExtra("number");
            int index = data.getIntExtra("index", 0);
            lvModel.updateNumber(index, number);
            arrayAdapter.notifyDataSetChanged();
        }
    }

    public void btnClicked(View b) {
        if (b.getId() == binding.btnUp.getId()) {
            model.increaseNumber();
        } else {
            model.decreaseNumber();
        }
        lvModel.addNumber("" + model.getNumber().getValue());
        arrayAdapter.notifyDataSetChanged();
    }
}