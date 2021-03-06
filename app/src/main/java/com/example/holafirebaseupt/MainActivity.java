package com.example.holafirebaseupt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String PATH_START = "start";
    private static final String PATH_MESSAGE = "message";
    @BindView(R.id.tvMessage)
    TextView tvMessage;
    @BindView(R.id.edtmessage)
    EditText edtmessage;
    @BindView(R.id.btnmessage)
    Button btnmessage;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        final TextView tvMessage = findViewById(R.id.tvMessage);
         database = FirebaseDatabase.getInstance();
        reference = database.getReference(PATH_START).child(PATH_MESSAGE);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tvMessage.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Error al consultar en Firebase", Toast.LENGTH_LONG).show();
            }
        });
    }

    @OnClick(R.id.btnmessage)
    public void onViewClicked() {
        reference.setValue(edtmessage.getText().toString().trim());
    }

    public void boton(View view) {
        startActivity(new Intent(this,MiRecyclerView.class));
    }

    public void registro(View view) {
        startActivity(new Intent(this,RegistroUsuarios.class));
    }

    public void login(View view) {
        startActivity(new Intent(this,MiLogin.class));
    }

}

