package com.example.holafirebaseupt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistroUsuarios extends AppCompatActivity {
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etPrice)
    EditText etPrice;
    @BindView(R.id.btnSave)
    Button btnSave;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterUsuario adaptador;
    private ArrayList<Usuario> midatos;

    private static final String PATH_USUARIO = "USUARIOS";
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);
        ButterKnife.bind(this);
        recyclerView = findViewById(R.id.recycler_view);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference(PATH_USUARIO);
        midatos = new ArrayList<>();
    /*    misdatos.add( new Producto("123", "Camisa", "200"));
        misdatos.add( new Producto("432", "Polo", "400"));
        misdatos.add(new Producto("5674", "Jean", "300"));
        misdatos.add( new Producto("345", "Zapato", "150"));
        misdatos.add(new Producto("678", "Chompa", "350"));*/
        adaptador = new AdapterUsuario(this,
                midatos);
        recyclerView.setAdapter(adaptador);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        AddItems();
    }
    @OnClick(R.id.btnSave)
    public void onViewClicked() {
        Toast.makeText(this,"test",Toast.LENGTH_LONG).show();
        Usuario producto = new Usuario(etName.getText().toString().trim(),
                etPrice.getText().toString().trim());
        reference.push().setValue(producto);
        etName.setText("");
        etPrice.setText("");
    }
    void AddItems(){
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Usuario usu = dataSnapshot.getValue(Usuario.class);
                usu.setId(dataSnapshot.getKey());
                if (!midatos.contains(usu)) {
                    midatos.add(usu);
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

}
