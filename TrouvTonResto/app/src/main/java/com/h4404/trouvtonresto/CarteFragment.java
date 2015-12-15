package com.h4404.trouvtonresto;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;


public class CarteFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    EditText edLocation;
    String[] restosName;
    public CarteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        edLocation=(EditText)view.findViewById(R.id.rechercheAdresse);

        MapFragment mapFragment = (MapFragment)getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        restosName = getResources().getStringArray(R.array.restosName);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //Ajout d'un custom window info qui se base sur le fichier XML maker_info_window
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View v = getActivity().getLayoutInflater().inflate(R.layout.maker_info_window, null);
                TextView nomResto= (TextView) v.findViewById(R.id.nomResto);
                TextView specialité=(TextView) v.findViewById(R.id.specialiteResto);

                nomResto.setText(marker.getTitle());
                specialité.setText(marker.getSnippet());

                return v;
            }
        });
        //Ajout d'un listenner sur click sur marker
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                return false;
            }
        });
        // Ajjour d'un marker dans la location de l'utilisateur
        LatLng myLocation = new LatLng(mMap.getMyLocation().getLatitude(), mMap.getMyLocation().getLongitude());
        //Grillon
        LatLng grillonLocalisation = new LatLng(45.78398,4.87503);
        //Olivier
        LatLng olivierLocalisation = new LatLng(45.78424,4.87485);
        //Beurk
        LatLng beurkLocalisation = new LatLng(45.78181,4.87593);
        //Prevert
        LatLng prevertLocalisation = new LatLng(45.78181,4.87573);
        //RU
        LatLng ruLocalisation = new LatLng(45.78099,4.87615);
        //Castor
        LatLng castorLocalisation = new LatLng(45.78391,4.87462);
        mMap.addMarker(new MarkerOptions().position(myLocation).title("Moi").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mMap.addMarker(new MarkerOptions().position(grillonLocalisation).title("Grillon").snippet(restosName[2]));
        mMap.addMarker(new MarkerOptions().position(olivierLocalisation).title("Olivier").snippet(restosName[3]));
        mMap.addMarker(new MarkerOptions().position(beurkLocalisation).title("Beurk").snippet(restosName[0]));
        mMap.addMarker(new MarkerOptions().position(prevertLocalisation).title("Prevert").snippet(restosName[4]));
        mMap.addMarker(new MarkerOptions().position(ruLocalisation).title("RU").snippet(restosName[1]));
        mMap.addMarker(new MarkerOptions().position(castorLocalisation).title("C.G.").snippet(restosName[5]));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
        mMap.setMyLocationEnabled(true);

    }

    public void onSearch(View view){
        String location=edLocation.getText().toString();
        List<Address> lesAdresses=null;
        if(location!=null && !location.equals(""))
        {
            Geocoder geocoder= null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                geocoder = new Geocoder(this.getContext());
            }
            try {
                lesAdresses=geocoder.getFromLocationName(location,2);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for(int i=0;i<lesAdresses.size();i++)
            {
                Address adresse=lesAdresses.get(i);
                LatLng coords=new LatLng(adresse.getLatitude(),adresse.getLongitude());
                mMap.addMarker(new MarkerOptions().position(coords).title(adresse.getLocality()));
                mMap.animateCamera(CameraUpdateFactory.newLatLng(coords));

            }

        }
    }
}
