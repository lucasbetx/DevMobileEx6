package com.lucasbezerrateixeira.exercicioo6;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout refreshLayout;
    private ListView listView;
    private TextView Title_txt;
    private ArrayAdapter adapter;
    private List list;
    private int refresh_count = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshLayout = findViewById(R.id.swipe_refresh_layout);
        listView = findViewById(R.id.items_listview);
        Title_txt = findViewById(R.id.items_title);
        Title_txt.setText("Arraste para atualizar..");


        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                refreshItems();

            }
        });


    }

    private void refreshItems(){

        switch (refresh_count){

            case 0:
                list = Arrays.asList(getResources().getStringArray(R.array.fiat_nomes));
                Title_txt.setText("Fiat");
                break;
            case 1:
                list = Arrays.asList(getResources().getStringArray(R.array.renault_nomes));
                Title_txt.setText("Renault");
                break;
            case 2:
                list = Arrays.asList(getResources().getStringArray(R.array.Chevrolet_nomes));
                Title_txt.setText("Chevrolet");
                break;
            case 3:
                list = Arrays.asList(getResources().getStringArray(R.array.marcas_aleatorias));
                Title_txt.setText("Marcas aleatorias");
                break;
        }

        refresh_count++;
        adapter = new ArrayAdapter(this,R.layout.item_layout,list);
        listView.setAdapter(adapter);
        if (refresh_count>3){

            refresh_count=0;
        }

        refreshLayout.setRefreshing(false);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.commonmenus,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id== R.id.mnuAttach){
            Toast.makeText(this, "Clicou em Activity 2", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,Activity2.class));


        }else if (id==R.id.mnuSettings){
            Toast.makeText(this,"Clicou em Activity 3",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,Activity3.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
