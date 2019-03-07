package com.example.mgebhart16woche22;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Spinner spinner;
    SearchView searchView;
    List<Cars> carList = new ArrayList<>();
    List<Cars> searchList = new ArrayList<>();
    String fileName = "cars.csv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listViewCar);
        spinner = findViewById(R.id.spinner_Brand);
        searchView = findViewById(R.id.searchView_Cars);

        readCSV();
        readHersteller(carList);
        searchList = carList;

        ArrayAdapter<Cars> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, carList);
        listView.setAdapter(adapter);



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                for (Cars items : carList) {
                    if (!(items.toString().toUpperCase()).contains(newText.toUpperCase())) {
                        searchList.remove(items);
                        
                    }else {
                        Toast.makeText(MainActivity.this, "No Match found", Toast.LENGTH_LONG);
                    }
                }
                return false;
            }
        });
    }
    public void readCSV()
    {
        String[] splited;
        String firstName;
        String lastName;
        String hersteller;
        String model;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("cars.csv")));
            String line = reader.readLine();
            while((line = reader.readLine())!= null)
            {
                splited = line.split(",");
                firstName = splited[1];
                lastName = splited[2];
                hersteller = splited[11];
                model = splited[12];

                Cars cars = new Cars(firstName, lastName, hersteller, model);
                carList.add(cars);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void readHersteller(List<Cars> list)
    {
        List<String> herstellerList = new ArrayList<>();
        for (Cars car : list) {
            herstellerList.add(car.getHersteller());
        }
        removeSameEntries(herstellerList);

        ArrayAdapter<String> herstellerAdp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, herstellerList);
        spinner.setAdapter(herstellerAdp);
    }
    public void removeSameEntries(List<String> herstellerList)
    {
        HashSet<String> hashSet = new HashSet<String>(herstellerList);
        herstellerList.clear();
        herstellerList.addAll(hashSet);

        ArrayAdapter<String> herstellerAdp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, herstellerList);
        spinner.setAdapter(herstellerAdp);
    }
}

