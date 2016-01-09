package com.h4404.trouvtonresto;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.MapFragment;
import android.content.Context;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;


public class CarteFragment extends Fragment {

    private GoogleMap mMap;
    private LatLng mCurrentLocation;
    public static double mRestosLat[] = {45.7810263, 45.78424, 45.78398, 45.7810013, 45.78099, 45.78391};
    public static double mRestosLon[] = {4.8730053, 4.87485, 4.87503, 4.873202, 4.87615, 4.87462};
    String[] mRestosName;
    String[] mRestosSpecialite;
    MapView mMapView;

    public CarteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        mRestosName = getResources().getStringArray(R.array.restosName);
        mRestosSpecialite = getResources().getStringArray(R.array.restosSpecialite);

        mMapView = (MapView) view.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        if(mMapView != null)
            initMap(mMapView.getMap());

        return view;
    }

    public void initMap(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMyLocationEnabled(true);

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

                nomResto.setText(mRestosName[Integer.parseInt(marker.getTitle())]);
                specialité.setText(mRestosSpecialite[Integer.parseInt(marker.getTitle())]);

                return v;
            }
        });
        //Ajout d'un listenner sur le label d'un marker
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Bundle bundle = new Bundle();
                bundle.putInt("indexResto", Integer.parseInt(marker.getTitle()));
                ((MainActivity)(getActivity())).displayView(R.id.resto, bundle);
            }
        });

        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {

            @Override
            public void onMyLocationChange(Location arg0) {
                LatLng myLocation = new LatLng(mMap.getMyLocation().getLatitude(), mMap.getMyLocation().getLongitude());
                mMap.addMarker(new MarkerOptions().position(myLocation).title("Moi").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            }
        });

        LatLngBounds.Builder b = new LatLngBounds.Builder();
        for (int i = 0 ; i < mRestosName.length ; ++i)
        {
            LatLng pos = new LatLng(mRestosLat[i], mRestosLon[i]);
            mMap.addMarker(new MarkerOptions().position(pos).title(Integer.toString(i)));
            b.include(pos);
        }

        LatLngBounds bounds = b.build();

        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 300, 300, 5);
        mMap.animateCamera(cu);
    }

    @Override
    public void onResume() {
        mMapView.onResume();
        super.onResume();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
