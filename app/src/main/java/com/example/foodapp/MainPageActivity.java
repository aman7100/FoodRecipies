package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inspector.IntFlagMapping;

import com.example.foodapp.Adapters.RecipieAdapter;
import com.example.foodapp.Classes.RecyclerItemClickListener;
import com.example.foodapp.Models.RecipieModel;
import com.example.foodapp.databinding.ActivityMainPageBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainPageActivity extends AppCompatActivity {
RecyclerView recyclerView;
ActivityMainPageBinding binding;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth=FirebaseAuth.getInstance();
        recyclerView=findViewById(R.id.recyclarview);
        ArrayList<RecipieModel> List=new ArrayList<>();
        List.add(new RecipieModel(R.drawable.food1,"Burger"));
        List.add(new RecipieModel(R.drawable.food2,"Pizza"));
        List.add(new RecipieModel(R.drawable.food3,"Fried Chicken"));
        List.add(new RecipieModel(R.drawable.food4,"Chicekn Biryanir"));
        List.add(new RecipieModel(R.drawable.food5,"Noodles"));
        List.add(new RecipieModel(R.drawable.food6,"Pasta"));
        List.add(new RecipieModel(R.drawable.food7,"Bread Omlette"));
        List.add(new RecipieModel(R.drawable.food8,"Chola Bhature"));

        RecipieAdapter adapter=new RecipieAdapter(List,this);
        recyclerView.setAdapter(adapter);

//        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener
                (this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        switch (position){
                            case 0:
                              Intent intent=new Intent(MainPageActivity.this,BurgerActivity.class);
                              startActivity(intent);
                                break;
                            case 1:
                                Intent intent1 =new Intent(MainPageActivity.this,PizzaActivity.class);
                                startActivity(intent1);
                                break;
                            case 2:
                                Intent intent2=new Intent(MainPageActivity.this,FriedChickenActivity.class);
                                startActivity(intent2);
                                break;
                            case 3:
                                Intent intent3=new Intent(MainPageActivity.this,BiryaniActivity.class);
                                startActivity(intent3);
                                break;
                            case 4:
                                Intent intent4=new Intent(MainPageActivity.this,NoodlesActivity.class);
                                startActivity(intent4);
                                break;
                            case 5:
                                Intent intent5=new Intent(MainPageActivity.this,PastaActivity.class);
                                startActivity(intent5);
                                break;
                            case 6:
                                Intent intent6=new Intent(MainPageActivity.this,NoodlesActivity.class);
                                startActivity(intent6);
                                break;
                            case 7:
                                Intent intent7=new Intent(MainPageActivity.this,CholeBhatureActivity.class);
                                startActivity(intent7);
                                break;
                            default:
                        }
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                }

                ));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){

            case R.id.Logout:
                auth.signOut();
                Intent intent=new Intent(MainPageActivity.this,MainActivity.class);
                startActivity(intent);
                break;

        }
        return true;
    }
}