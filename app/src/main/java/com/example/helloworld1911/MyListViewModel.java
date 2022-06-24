package com.example.helloworld1911;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class MyListViewModel extends ViewModel {
    private MutableLiveData<ArrayList<String>> arrayList;
    public LiveData<ArrayList<String>> getArrayList() {
        if (arrayList == null) {
            arrayList = new MutableLiveData<>();
            arrayList.setValue(new ArrayList<>());
        }
        return arrayList;
    }

    public void addNumber(String number) {
        ArrayList<String> l = arrayList.getValue();
        l.add(number);
        arrayList.setValue(l);
    }
    public void removeNumber(int index) {
        ArrayList<String> l = arrayList.getValue();
        l.remove(index);
        arrayList.setValue(l);
    }
    public void updateNumber(int index, String number) {
        ArrayList<String> l = arrayList.getValue();
        l.set(index, number);
        arrayList.setValue(l);
    }
}
