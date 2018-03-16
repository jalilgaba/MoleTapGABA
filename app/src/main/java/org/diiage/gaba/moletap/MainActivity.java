package org.diiage.gaba.moletap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.diiage.gaba.moletap.Models.Score;
import org.diiage.gaba.moletap.Models.Session;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by jalil on 15/03/2018.
 */

public class MainActivity extends AppCompatActivity {
    EditText txtNom = null;
    Button btnNewGame = null;
    Button btnScores = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Récupération des contôles dans le layout principal
        txtNom = (EditText) findViewById(R.id.txtName);
        btnNewGame = (Button) findViewById(R.id.btnNewGame);
        btnScores = (Button) findViewById(R.id.btnScores);

        //Affectation de l'ouverture du jeu au clic sur le bouton
        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = Calendar.getInstance().getTime();
                String nomJoueur = txtNom.getText().toString();
                ArrayList<Score> scores = new ArrayList<Score>();
                Score score = new Score(0,0,0,0,0);

                Session s = new Session(date,nomJoueur,scores);

                Intent intent = new Intent(MainActivity.this,GameActivity.class);
                intent.putExtra("session", s);
                startActivity(intent);
            }
        });

        //Affectation de l'ouverture du score au clic sur le bouton
        btnScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ScoreActivity.class);
                startActivity(intent);
            }
        });
    }
}
