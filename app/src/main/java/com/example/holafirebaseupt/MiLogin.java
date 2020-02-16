package com.example.holafirebaseupt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import butterknife.ButterKnife;

public class MiLogin extends AppCompatActivity {
    private EditText usu,pass;
    private Button boton;

    DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_login);

        usu=(EditText) findViewById(R.id.miuser);
        pass=(EditText) findViewById(R.id.mipass);
        boton=(Button) findViewById(R.id.boton);
        FirebaseAuth au;
     //  db=FirebaseDatabase.getInstance().getReference(getString(R.string.nodo_usu));
        db=FirebaseDatabase.getInstance().getReference().child("USUARIOS");
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passcode1=usu.getText().toString().trim();
                String pass1=pass.getText().toString().trim();
                Query q= db.orderByChild("usu").equalTo(passcode1);
                        //.orderByChild("pass").equalTo(pass1);
                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot datasnapshot: dataSnapshot.getChildren()) {
                            //do something
                            startActivity(new Intent(MiLogin.this, ListaProductos.class));
                            finish();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


           // Query q= db.orderByChild(getString(R.string.campo_usu)).equalTo(usu.toString());
                    /*Query s=db.child("USUARIOS").orderByChild("usu").equals(usu);
                if(s.equals(usu)){
                    startActivity(new Intent(MiLogin.this,ListaProductos.class));
                    finish();
                }*/
              /* if(usu.equals(db.child("USUARIOS").orderByChild("usu"))){
                    startActivity(new Intent(MiLogin.this,ListaProductos.class));
                    finish();
               }
            /*
                Query p=db.orderByChild(getString(R.string.campo_pass)).equalTo(pass.toString());
                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int cont=0;
                        for(DataSnapshot datasnapshot: dataSnapshot.getChildren()){
                            cont++;
                            Toast.makeText(MiLogin.this, "He encontrado "+cont, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });*/

            /*  if(usu.equals(db.orderByChild(getString(R.string.campo_usu)))){
                    startActivity(new Intent(MiLogin.this,ListaProductos.class));
                    finish();
                }*/
            }
        });

    }

}
