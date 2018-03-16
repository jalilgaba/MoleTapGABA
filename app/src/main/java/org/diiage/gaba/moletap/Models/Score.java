package org.diiage.gaba.moletap.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jalil on 15/03/2018.
 */

// on implémente Parcelable
public class Score implements Parcelable {
    private int nbPoints;
    private int nbTaupesManquees;
    private int tpsReactionMax;
    private int tpsReactionMin;
    private int tpsReactionMoyen;

    public int getNbPoints() {
        return nbPoints;
    }

    public void setNbPoints(int nbPoints) {
        this.nbPoints = nbPoints;
    }

    public int getNbTaupesManquees() {
        return nbTaupesManquees;
    }

    public void setNbTaupesManquees(int nbTaupesManquees) {
        this.nbTaupesManquees = nbTaupesManquees;
    }

    public int getTpsReactionMax() {
        return tpsReactionMax;
    }

    public void setTpsReactionMax(int tpsReactionMax) {
        this.tpsReactionMax = tpsReactionMax;
    }

    public int getTpsReactionMin() {
        return tpsReactionMin;
    }

    public void setTpsReactionMin(int tpsReactionMin) {
        this.tpsReactionMin = tpsReactionMin;
    }

    public int getTpsReactionMoyen() {
        return tpsReactionMoyen;
    }

    public void setTpsReactionMoyen(int tpsReactionMoyen) {
        this.tpsReactionMoyen = tpsReactionMoyen;
    }

    public Score(int nbPoints, int nbTaupesManquees, int tpsReactionMax, int tpsReactionMin, int tpsReactionMoyen) {
        this.nbPoints = nbPoints;
        this.nbTaupesManquees = nbTaupesManquees;
        this.tpsReactionMax = tpsReactionMax;
        this.tpsReactionMin = tpsReactionMin;
        this.tpsReactionMoyen = tpsReactionMoyen;
    }

    public Score() {
    }
    // Sert à décrire le contenu de notre Parcel
    @Override
    public int describeContents() {
        return 0;
    }
    //Sert à écrire l’objet dans un Parcel
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(String.valueOf(nbPoints));
        parcel.writeString(String.valueOf(nbTaupesManquees));
        parcel.writeString(String.valueOf(tpsReactionMax));
        parcel.writeString(String.valueOf(tpsReactionMin));
        parcel.writeString(String.valueOf(tpsReactionMoyen));
    }

    //Le CREATOR permet d’indiquer comment l'objet de type Parcelable sera créé.
    public static final Parcelable.Creator<Score> CREATOR = new Parcelable.Creator<Score>()
    {
        @Override
        public Score createFromParcel(Parcel source)
        {
            return new Score(source);
        }

        @Override
        public Score[] newArray(int size)
        {
            return new Score[size];
        }
    };

    public Score(Parcel in) {
        this.nbPoints = in.readInt();
        this.nbTaupesManquees = in.readInt();
        this.tpsReactionMax = in.readInt();
        this.tpsReactionMin = in.readInt();
        this.tpsReactionMoyen = in.readInt();
    }
}
