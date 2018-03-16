package org.diiage.gaba.moletap;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.diiage.gaba.moletap.Models.Score;
import org.diiage.gaba.moletap.Models.Session;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jalil on 15/03/2018.
 */

public class GameActivity extends AppCompatActivity
{
    TextView txtChrono = null;
    Boolean isTimerRunning = false;
    int elapsedTime = 0;
    Timer timer = new Timer();
    Timer timerTaupeApparition = new Timer();
    Random random = new Random();
    ImageButton currentTaupe = null;
    Session session = new Session();
    Score score = new Score();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        session = getIntent().getExtras().getParcelable("session");


        txtChrono = (TextView) findViewById(R.id.txtChrono);
        elapsedTime = Integer.parseInt(txtChrono.getText().toString());

        CacherTaupes();
        currentTaupe = GetImageTaupe(random.nextInt(9));
        AfficherTaupe(currentTaupe);



        startTimer();
    }

    protected void startTimer()
    {
        isTimerRunning = true;
        timer.scheduleAtFixedRate(new TimerTask()
        {

                public void run()
                {
                    if (elapsedTime != 0)
                    {
                        elapsedTime -= 1;
                        mHandler.obtainMessage(1).sendToTarget();
                        handlerApparitionTaupe.obtainMessage(1).sendToTarget();
                    }
                    else
                    {
                        mHandler.obtainMessage(1).sendToTarget();
                        return;
                    }
                }

        }, 0, 1000);
    }

    private void ApparitionTaupe()
    {
        CacherTaupe(currentTaupe);
        currentTaupe = GetImageTaupe(random.nextInt(9));
        AfficherTaupe(currentTaupe);
    }


    public Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            if(elapsedTime != 0)
            {
                txtChrono.setText(String.valueOf(elapsedTime));
            }
            else
            {
                session.getScores().add(score);
                txtChrono.setText("Fin de partie " + String.valueOf(score.getNbPoints()) + " points");
            }
        }
    };

    public Handler handlerApparitionTaupe = new Handler() {
        public void handleMessage(Message msg) {
            ApparitionTaupe();
        }
    };

    public void CacherTaupes()
    {
        for (int i = 1; i < 10; i++)
        {
            ImageButton img = GetImageTaupe(i);
            img.setImageDrawable(null);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(elapsedTime != 0)
                    {
                        ApparitionTaupe();
                        score.setNbPoints(score.getNbPoints() + 1);
                    }
                }
            });
        }
    }
    public void CacherTaupe(ImageButton img)
    {
        if(img != null)
            img.setImageDrawable(null);
    }

    public void AfficherTaupe(ImageButton img)
    {
        if (img != null)
            img.setImageResource(R.drawable.lilmole);
    }

    public ImageButton GetImageTaupe(int numeroImage)
    {
        int id = getResources().getIdentifier("mole" + numeroImage,"id", getPackageName());
        ImageButton img = findViewById(id);
        return img;
    }
}
