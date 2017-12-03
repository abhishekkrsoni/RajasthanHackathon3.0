package com.example.abhishek.rajasthane_paryatanam;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private GoogleMap mMap;
    private GoogleApiClient client;
    private LocationRequest locationRequest;//add

    //*
    private Location lastLocation;
    public static final int REQUEST_LOCATION_CODE=99;


    private Marker currentLocationMarker;
//*

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //*
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            checkLocationPermission();
        }

        //*
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){

            case REQUEST_LOCATION_CODE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //permission is granted
                    if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ){

                        if( client == null){
                            buildGoogleApiClient();
                        }

                        mMap.setMyLocationEnabled(true);
                    }
                    else{//when Permission is Denied!
                        Toast.makeText(this, "Permission Denied!", Toast.LENGTH_LONG).show();
                    }

                    return;

                }
        }    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED) {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);    //Give us MyLocation button at Right Top to go to Current Location

        }


        //   # 1. Udaipur
        LatLng udaipur = new LatLng(24.58, 73.68);
        mMap.addMarker(new MarkerOptions().position(udaipur).title("Marker in Udaipur"));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(udaipur));

        //    # 2. City Palace, Udaipur
        LatLng cityPalaceUDZ = new LatLng(24.576, 73.683);
        mMap.addMarker(new MarkerOptions().position(cityPalaceUDZ).title("Marker in City Palace UDZ"));
        //  mMap.moveCamera(CameraUpdateFactory.newLatLng(cityPalaceUDZ));

        //    # 3. Monsoon Palace, Udaipur
        LatLng monsoonPalaceUDZ = new LatLng(24.594, 73.639);
        mMap.addMarker(new MarkerOptions().position(monsoonPalaceUDZ).title("Marker in Monsoon Palace UDZ"));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(monsoonPalaceUDZ));

        //    # 4. Jag Mandir, Udaipur
        LatLng jagMandirUDZ = new LatLng(24.5672, 73.6781);
        mMap.addMarker(new MarkerOptions().position(jagMandirUDZ).title("Marker in Jag Mandir UDZ"));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(jagMandirUDZ));


        //    # 5. Ahar Musuem, Udaipur
        LatLng aharMuseumUDZ = new LatLng(24.5864, 73.7212);
        mMap.addMarker(new MarkerOptions().position(aharMuseumUDZ).title("Marker in Ahar Museum UDZ"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(aharMuseumUDZ));

        //##############   Jaipur   ############
    }

    protected synchronized void buildGoogleApiClient(){

        client = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        client.connect();
    }

    @Override
    public void onLocationChanged(Location location) {

        lastLocation  = location;

        //Checking if currenLocationMarker is there or not if there remove it & set to new location
        if(currentLocationMarker != null ){
            currentLocationMarker.remove();  //removing the previous marker to set marker to current location
        }

        LatLng latLng = new LatLng(location.getAltitude(), location.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));  //setting marker color

        currentLocationMarker = mMap.addMarker(markerOptions);

        //moving camera to that location
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));//latLng stores the lat and longitude of new location where camera will moved
        //animating the camera and zooming into the map
        mMap.animateCamera(CameraUpdateFactory.zoomBy(30));


        if(client!= null){
            LocationServices.FusedLocationApi.removeLocationUpdates(client, this);
        }

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();

        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);
        }
    }


    public  boolean checkLocationPermission(){

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION )!= PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);
            }
            else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);

            }
            return false;
        }
        return  true;
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
