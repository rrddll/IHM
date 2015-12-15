package com.h4404.trouvtonresto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luc on 15/12/15.
 */
public class Restaurant {
    private static int lastId = 0;
    private int id;
    private String nom;
    private String specialite;
    private int tempsAttente;
    private int distance;

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

    public static List<Restaurant> allRestaurants() {
        ArrayList<Restaurant> liste = new ArrayList<>();
        liste.add(new Restaurant("Castor et Polux", "Cuisine gastronomique française", 30, 1024));
        liste.add(new Restaurant("L'olivier", "Cucina gourmet italiana", 12, 820));
        liste.add(new Restaurant("Le grillon", "Grillades grasses accompagnées de patates", 15, 820));
        liste.add(new Restaurant("Le prévert", "芋のバーガー", 7, 1023));

        return liste;
    }
}
