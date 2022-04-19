package com.example.android_2022_1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button bt = null;
    private Button btLimpar = null;
    EditText login = null;
    EditText password = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.bt = findViewById(R.id.buttonAutenticar);
        this.login = findViewById(R.id.editTextLogin);
        this.password = findViewById(R.id.editTextPassword);
        this.btLimpar = findViewById(R.id.buttonLimpar);
        bt.setEnabled(false);
        this.btLimpar.setVisibility(View.INVISIBLE);
        EditText[] listFields = {this.login, this.password};
        View.OnKeyListener listener = new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                atualizaEnableBotao();
                atualizaVisibilityBotao();
                return false;
            }
        };
        for(EditText et: listFields) {
            et.setOnKeyListener(listener);
        }
        this.btLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limparCampos();
            }
        });

        this.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autenticar();
            }
        });
    }

    private void autenticar(){
        String loginTxt = this.login.getText().toString();
        String passowordText = this.password.getText().toString();
        String mensagem = "";
        int color = 0;
        if( loginTxt.equals("admin") && passowordText.equals("123") ){
            mensagem = "Autenticação ok.";
            color = Color.rgb(0,255,0);
        }else{
            mensagem = "Autenticação falhou!";
            color = Color.rgb(255,0,0);
        }
        TextView tv = findViewById(R.id.textViewFeedbackMessage);
        tv.setTextSize(20);
        tv.setText(mensagem);
        tv.setTextColor(color);
    }

    private void atualizaVisibilityBotao(){
        String valorLogin = this.login.getText().toString().trim();
        String valorPassword = this.password.getText().toString().trim();
        boolean botaoVisivel = !valorLogin.isEmpty() || !valorPassword.isEmpty();
        this.btLimpar.setVisibility(botaoVisivel ? View.VISIBLE : View.INVISIBLE);
    }

    private void atualizaEnableBotao(){
        String valorLogin = this.login.getText().toString().trim();
        String valorPassword = this.password.getText().toString().trim();
        boolean habilitaBotao = !valorLogin.isEmpty() && !valorPassword.isEmpty();
        this.bt.setEnabled(habilitaBotao);
    }

    private  void limparCampos(){
        this.login.setText("");
        this.password.setText("");
        atualizaVisibilityBotao();
    }



}