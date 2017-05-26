package mymapstut.com.example.admin.mymapstut;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap mMap;

    private static final int ERROR_DIALOG_REQUEST =9001;
    private  static final double
            TorontoLat=43.732755,TorontoLng=-79.263927,
            SydneyLat=-33.856294 ,SydneyLng=151.215297,
            NycLat=40.760260 ,NycLng=-73.980020;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

      if (servicesOK()){
          initMap();
          Toast.makeText(this, "map ready", Toast.LENGTH_SHORT).show();
        //  onMapReady(mMap);

      }
       else{
          Toast.makeText(this, "map not ready", Toast.LENGTH_SHORT).show();
      }

       // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
       // fab.setOnClickListener(new View.OnClickListener() {
          //  @Override
         //   public void onClick(View view) {
         //       Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            //            .setAction("Action", null).show();
           // }
       // });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    //this method is to ensure that apk can map
    private boolean servicesOK() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);

        if(result != ConnectionResult.SUCCESS) {

            if(googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(this, result,
                        ERROR_DIALOG_REQUEST).show();
            }
            Toast.makeText(this, "Cant make connection to map", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private  boolean initMap(){
        if (mMap == null){
            setContentView(R.layout.activity_map);
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
                     mapFragment.getMapAsync(this);
        }
                return (mMap !=null);
    }

    //gotolocation method
    private  void goToMap(double lat,double lng,float zoom){
        LatLng latLng = new LatLng(lat,lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng,zoom);
        mMap.moveCamera(update);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
       mMap = googleMap;
        //double lat = TorontoLat;
      //  double lng = TorontoLng;
       // LatLng latLng = new LatLng(lat,lng);
       // CameraUpdate update = CameraUpdateFactory.newLatLng(latLng);
      //  mMap.moveCamera(update);
        goToMap(TorontoLat,TorontoLng,15);

        // Add a marker in Sydney and move the camera
       // LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(latLng).title("Marker in Toronto"));
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }
}
