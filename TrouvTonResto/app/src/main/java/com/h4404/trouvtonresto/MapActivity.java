package com.h4404.trouvtonresto;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
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
        mMap.addMarker(new MarkerOptions().position(myLocation).title(""));
        mMap.addMarker(new MarkerOptions().position(grillonLocalisation).title("Grillon"));
        mMap.addMarker(new MarkerOptions().position(olivierLocalisation).title("Olivier"));
        mMap.addMarker(new MarkerOptions().position(beurkLocalisation).title("Beurk"));
        mMap.addMarker(new MarkerOptions().position(prevertLocalisation).title("Prevert"));
        mMap.addMarker(new MarkerOptions().position(ruLocalisation).title("RU"));
        mMap.addMarker(new MarkerOptions().position(castorLocalisation).title("C.G."));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
        mMap.setMyLocationEnabled(true);

    }

    public void onSearch(View view){
        EditText edLocation=(EditText)findViewById(R.id.rechercheAdresse);
        String location=edLocation.getText().toString();
        List<Address> lesAdresses=null;
        if(location!=null && !location.equals(""))
        {
            Geocoder geocoder=new Geocoder(this);
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
