package com.example.holafirebaseupt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MiRecyclerView extends AppCompatActivity {

    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etPrice)
    EditText etPrice;
    @BindView(R.id.btnSave)
    Button btnSave;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterProducto adaptador;
    private ArrayList<Producto> misdatos;

    private static final String PATH_PRODUCTO = "PRODUCTOS";
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_recycler_view);
        ButterKnife.bind(this);
        recyclerView = findViewById(R.id.recycler_view);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference(PATH_PRODUCTO);
        misdatos = new ArrayList<>();
     /*   misdatos.add( new Producto("123", "Camisa", "200"));
        misdatos.add( new Producto("432", "Polo", "400"));
        misdatos.add(new Producto("5674", "Jean", "300"));
        misdatos.add( new Producto("345", "Zapato", "150"));
        misdatos.add(new Producto("678", "Chompa", "350"));*/
        adaptador = new AdapterProducto(this,
                misdatos);
        recyclerView.setAdapter(adaptador);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }
    @OnClick(R.id.btnSave)
    public void onViewClicked() {
        Toast.makeText(this, "Probando boton",Toast.LENGTH_LONG).show();
        Producto producto = new Producto(etName.getText().toString().trim(),
                etPrice.getText().toString().trim());
        reference.push().setValue(producto);
        etName.setText("");
        etPrice.setText("");
    }

}
