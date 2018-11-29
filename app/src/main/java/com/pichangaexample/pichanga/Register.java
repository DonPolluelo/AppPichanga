package com.pichangaexample.pichanga;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Register extends AppCompatActivity implements View.OnClickListener {

    //defining view objects
    private EditText TextEmail;
    private EditText TextPassword;
    private Button btnRegistrar, btnLogin;
    private ProgressDialog progressDialog;


    //Declaramos un objeto firebaseAuth
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //inicializamos el objeto firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        //Referenciamos los views
        TextEmail = (EditText) findViewById(R.id.register_email);
        TextPassword = (EditText) findViewById(R.id.register_password);

        btnRegistrar = (Button) findViewById(R.id.register_button);
        btnLogin = (Button) findViewById(R.id.login_button);

        progressDialog = new ProgressDialog(this);

        //attaching listener to button
        btnRegistrar.setOnClickListener(this);
        btnLogin.setOnClickListener(this);


        SharedPreferences prefs = getSharedPreferences("login", MODE_PRIVATE);
        final String correo = prefs.getString("correo", "");




    }

    private void registrarUsuario(){

        //Obtenemos el email y la contraseña desde las cajas de texto
        final String email = TextEmail.getText().toString().trim();
        final String password  = TextPassword.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacías
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Se debe ingresar un email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Falta ingresar la contraseña",Toast.LENGTH_LONG).show();
            return;
        }


        progressDialog.setMessage("Realizando registro en linea...");
        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){

                            Toast.makeText(Register.this,"Se ha registrado el usuario con éxito",Toast.LENGTH_LONG).show();
                        }else{
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {//si se presenta una colisión
                                Toast.makeText(Register.this, "Ese usuario ya existe ", Toast.LENGTH_SHORT).show();
                            }

                            else if(email.indexOf('@') == -1){
                                Toast.makeText(Register.this, "Debes ingresar un correo válido", Toast.LENGTH_SHORT).show();
                            }

                            else if(password.length() < 6){
                                Toast.makeText(Register.this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                            }

                            else {
                                Toast.makeText(Register.this, "No se pudo registrar el usuario ", Toast.LENGTH_LONG).show();
                            }
                        }
                        progressDialog.dismiss();
                    }
                });

    }



    private void loguearUsuario() {
        //Obtenemos el email y la contraseña desde las cajas de texto
        final String email = TextEmail.getText().toString().trim();
        String password = TextPassword.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacías
        if (TextUtils.isEmpty(email)) {//(precio.equals(""))
            Toast.makeText(this, "Se debe ingresar un email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Falta ingresar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }


        progressDialog.setMessage("Realizando consulta en linea...");
        progressDialog.show();

        //loguear usuario
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {
                            int pos = email.indexOf("@");
                            String user = email.substring(0, pos);


                            SharedPreferences prefs = getSharedPreferences("login", MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("correo", email);
                            editor.apply();


                            // Toast.makeText(Register.this, "Bienvenido: " + TextEmail.getText(), Toast.LENGTH_LONG).show();
                            Intent intencion = new Intent(getApplication(), MainActivity.class);

                            intencion.putExtra(MainActivity.user, user);
                            startActivity(intencion);
                            finish();


                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {//si se presenta una colisión
                                Toast.makeText(Register.this, "Ese usuario ya existe ", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Register.this, "Usuario o contraseña inválidos ", Toast.LENGTH_LONG).show();
                            }
                        }
                        progressDialog.dismiss();
                    }
                });


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.register_button:
                //Invocamos al método:
                registrarUsuario();
                break;
            case R.id.login_button:
                loguearUsuario();
                break;
        }


    }

}

