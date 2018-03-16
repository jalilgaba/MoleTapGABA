package org.diiage.gaba.moletap.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jalil on 15/03/2018.
 */

public class Session implements Parcelable {
    private Date debutSession;
    private String nomJoueur;
    private ArrayList<Score> scores;

    public Date getDebutSession() {
        return debutSession;
    }

    public void setDebutSession(Date debutSession) {
        this.debutSession = debutSession;
    }

    public String getNomJoueur() {
        return nomJoueur;
    }

    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(ArrayList<Score> scores) {
        this.scores = scores;
    }

    public Session() {

    }

    public Session(Date debutSession, String nomJoueur, ArrayList<Score> scores) {
        this.debutSession = debutSession;
        this.nomJoueur = nomJoueur;
        this.scores = scores;
    }



    // Sert à décrire le contenu de notre Parcel
    @Override
    public int describeContents() {
        return 0;
    }
    //Sert à écrire l’objet dans un Parcel
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(debutSession); // la date est serialisable donc on utilise writeSerializable()
        parcel.writeString(nomJoueur);
        parcel.writeSerializable(scores);// ArrayList est serialisable donc on utilise writeSerializable()
    }

    //Le CREATOR permet d’indiquer comment l'objet de type Parcelable sera créé.
    public static final Parcelable.Creator<Session> CREATOR = new Parcelable.Creator<Session>()
    {
        @Override
        public Session createFromParcel(Parcel source)
        {
            return new Session(source);
        }

        @Override
        public Session[] newArray(int size)
        {
            return new Session[size];
        }
    };

    public Session(Parcel in) {
        this.debutSession = (Date) in.readSerializable(); // on cast serializable en date
        this.nomJoueur = in.readString();
        this.scores = (ArrayList<Score>) in.readSerializable();
    }
}
