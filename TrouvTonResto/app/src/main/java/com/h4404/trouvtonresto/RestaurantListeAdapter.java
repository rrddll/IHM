package com.h4404.trouvtonresto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by luc on 15/12/15.
 */
public class RestaurantListeAdapter extends ArrayAdapter<Restaurant>{
    private final Context context;
    private final List<Restaurant> restos;
    public RestaurantListeAdapter (Context context, List<Restaurant> values) {
        super(context, -1, values);
        this.context = context;
        restos = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.resto_adapter, parent, false);

        TextView titre = (TextView) rowView.findViewById(R.id.titreResto);
        TextView specialite = (TextView) rowView.findViewById(R.id.specialiteResto);
        TextView info = (TextView) rowView.findViewById(R.id.infoChiffreResto);

        titre.setText(restos.get(position).getNom());
        specialite.setText(restos.get(position).getSpecialite());
        int dist = restos.get(position).getDistance();
        String distance;
        if (dist >= 1000) {
            distance = (dist/1000) + "," + ((dist % 1000) / 100) + " km";
        } else {
            distance = dist + " m";
        }

        info.setText(distance);

        return rowView;
    }
}
