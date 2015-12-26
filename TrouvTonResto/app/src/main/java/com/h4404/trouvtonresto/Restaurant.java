package com.h4404.trouvtonresto;

import android.content.Context;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by luc on 15/12/15.
 */
public class Restaurant {
    private  static Random r = new Random();
    private static int lastId = 0;
    private int id;
    private String nom;
    private String specialite;
    private int tempsAttente;
    private int distance;
    private static Context context;

    public Restaurant (String a_nom, String a_specialite, int a_tempsAttente, int a_distance) {
        nom = a_nom;
        specialite = a_specialite;
        //En minutes
        tempsAttente = a_tempsAttente;
        //En mètres
        distance = a_distance;
        id = lastId++;
    }

    public String getNom() {
        return nom;
    }

    public String getSpecialite() {
        return specialite;
    }

    public int getId() {
        return id;
    }

    public int getTempsAttente() {
        return tempsAttente;
    }

    public int getDistance() {
        return distance;
    }

    public void shuffleWaitingTime()
    {
        tempsAttente = r.nextInt(17) + 3;
    }
}
