package com.example.nckh.Activity;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.nckh.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Address_Us extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    private Button btn;
    private Location location;
    private GoogleApiClient gac;
    private String dcx, dcy;
    private Intent intent;
    private double x = 10.985024982399338, y = 106.68258440446365;//10.985024982399338, 106.68258440446365
    private String Address_u = "";
    private ArrayList<Point> x_y = new ArrayList<>();
    private ArrayList<Point> x_y2 = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_us);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map2);
        mapFragment.getMapAsync(this);
        if (checkPlayServices()) {
            buildGoogleApiClient();
        }
        addcontrol();
        addEvent();
    }

    public void dispLocation(View view) {
        getLocation();
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 2);
        } else {
            location = LocationServices.FusedLocationApi.getLastLocation(gac);
            if (location != null) {
                x = location.getLatitude();
                y = location.getLongitude();

                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                ax(x, y);
                // Hiển thị
                Toast.makeText(this, latitude + ", " + longitude, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "(Location cannot be displayed. Have you enabled location on the device?)", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this, 1000).show();
            } else {
                Toast.makeText(this, "This device is not supported.", Toast.LENGTH_LONG).show();
                finish();
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.i("key pressed", String.valueOf(event.getKeyCode()));
        return super.dispatchKeyEvent(event);
    }

    protected synchronized void buildGoogleApiClient() {
        if (gac == null) {
            gac = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API).build();
        }
    }

    @Override
    protected void onStart() {
        gac.connect();
        super.onStart();
    }

    protected void onStop() {
        //gac.disconnect();
        super.onStop();
    }

    private void ax(double x2, double y2) {
        DecimalFormat df = new DecimalFormat("#.###");
        Double x3 = ((double) Math.floor(x2 * 1000000) / 1000000) - 0.005367;
        Double y3 = ((double) Math.floor(y2 * 1000000) / 1000000) - 0.006348;
        //btn.setText("x3" + String.valueOf(x3) + ":y3" + String.valueOf(y3));
        LatLng latLng = new LatLng(x3, y3);
        int w = 60;
        int h = 60;
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.userfuny);
        Bitmap bitmap = bitmapDrawable.getBitmap();
        Bitmap sm = Bitmap.createScaledBitmap(bitmap, w, h, false);
        Marker melbourne = mMap.addMarker(
                new MarkerOptions()
                        .position(latLng)
                        .title("Melbourne")
                        .snippet("Population: 4")
                        .icon(BitmapDescriptorFactory.fromBitmap(sm)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latLng_DiAn = new LatLng(10.911256759571685, 106.77658145481655);
        mMap.addMarker(new MarkerOptions().position(latLng_DiAn).title("Dĩ An"));
        LatLng latLngThuanAn = new LatLng(10.942137931367128, 106.71299126830925);
        mMap.addMarker(new MarkerOptions().position(latLngThuanAn).title("Thuận An"));
        LatLng latLngBauBang = new LatLng(11.252292695419952, 106.58817099714804);
        mMap.addMarker(new MarkerOptions().position(latLngBauBang).title("Bàu Bàng"));
        LatLng latLngPhuGiao = new LatLng(11.32073007232624, 106.78093599714869);
        mMap.addMarker(new MarkerOptions().position(latLngPhuGiao).title("Phú Giáo"));
        LatLng latLngTanUyen = new LatLng(11.111552490958436, 106.79259526831085);
        mMap.addMarker(new MarkerOptions().position(latLngTanUyen).title("Tân Uyên"));
        LatLng latLngTDM = new LatLng(11.006518118104516, 106.66655499714565);
        mMap.addMarker(new MarkerOptions().position(latLngTDM).title("Thủ Dầu Một"));
        LatLng latLngDauTieng = new LatLng(11.315262668060118, 106.43518697467735);
        mMap.addMarker(new MarkerOptions().position(latLngDauTieng).title("Dầu Tiếng"));
        LatLng latLngBenCat = new LatLng(11.21729427857911, 106.61748281064003);
        mMap.addMarker(new MarkerOptions().position(latLngBenCat).title("Bến Cát"));

        Polygon polygon = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(10.890789, 106.771614),
                        new LatLng(10.885394, 106.777107),
                        new LatLng(10.875954, 106.781914),
                        new LatLng(10.867188, 106.779854),
                        new LatLng(10.863816, 106.781227),
                        new LatLng(10.869885, 106.788094),
                        new LatLng(10.871908, 106.793587),
                        new LatLng(10.873931, 106.801827),
                        new LatLng(10.875280, 106.807320),
                        new LatLng(10.882697, 106.816933),
                        new LatLng(10.885394, 106.822426),
                        new LatLng(10.894160, 106.823799),
                        new LatLng(10.893486, 106.827233),
                        new LatLng(10.896183, 106.832726),
                        new LatLng(10.898206, 106.834099),
                        new LatLng(10.901577, 106.836846),
                        new LatLng(10.904948, 106.834786),
                        new LatLng(10.905622, 106.829979),
                        new LatLng(10.902925, 106.823799),
                        new LatLng(10.904274, 106.816933),
                        new LatLng(10.907645, 106.814186),
                        new LatLng(10.912365, 106.814186),
                        new LatLng(10.918433, 106.809380),
                        new LatLng(10.919781, 106.802513),
                        new LatLng(10.923152, 106.796334),
                        new LatLng(10.923152, 106.790840),
                        new LatLng(10.931243, 106.790840),
                        new LatLng(10.933265, 106.785347),
                        new LatLng(10.940681, 106.781227),
                        new LatLng(10.939333, 106.777107),
                        new LatLng(10.942029, 106.772301),
                        new LatLng(10.948097, 106.769554),
                        new LatLng(10.952142, 106.768181),
                        new LatLng(10.953490, 106.764061),
                        new LatLng(10.958209, 106.764061),
                        new LatLng(10.964276, 106.770928),
                        new LatLng(10.959557, 106.774361),
                        new LatLng(10.962928, 106.781227),
                        new LatLng(10.972365, 106.794274),
                        new LatLng(10.979780, 106.790840),
                        new LatLng(10.987869, 106.783287),
                        new LatLng(11.004720, 106.772301),
                        new LatLng(11.012134, 106.775734),
                        new LatLng(11.024576, 106.797336),
                        new LatLng(11.031315, 106.812442),
                        new LatLng(11.047490, 106.809009),
                        new LatLng(11.055577, 106.800082),
                        new LatLng(11.066359, 106.819746),
                        new LatLng(11.050859, 106.834165),
                        new LatLng(11.027945, 106.865064),
                        new LatLng(11.023228, 106.873991),
                        new LatLng(11.025249, 106.888410),
                        new LatLng(11.028619, 106.902143),
                        new LatLng(11.023228, 106.904203),
                        new LatLng(11.034011, 106.910383),
                        new LatLng(11.043446, 106.919996),
                        new LatLng(11.053555, 106.919309),
                        new LatLng(11.065011, 106.930296),
                        new LatLng(11.055577, 106.945402),
                        new LatLng(11.066359, 106.947462),
                        new LatLng(11.073771, 106.950895),
                        new LatLng(11.075793, 106.944715),
                        new LatLng(11.085227, 106.944028),
                        new LatLng(11.100051, 106.955701),
                        new LatLng(11.100051, 106.957761),
                        new LatLng(11.111505, 106.962427),
                        new LatLng(11.122285, 106.963113),
                        new LatLng(11.123633, 106.958307),
                        new LatLng(11.129023, 106.953500),
                        new LatLng(11.135086, 106.956247),
                        new LatLng(11.141150, 106.952814),
                        new LatLng(11.142497, 106.963113),
                        new LatLng(11.145192, 106.964487),
                        new LatLng(11.148560, 106.962427),
                        new LatLng(11.161360, 106.954874),
                        new LatLng(11.176854, 106.943887),
                        new LatLng(11.181569, 106.937021),
                        new LatLng(11.186958, 106.932901),
                        new LatLng(11.191000, 106.919855),
                        new LatLng(11.193737, 106.906241),
                        new LatLng(11.203167, 106.906241),
                        new LatLng(11.212596, 106.911047),
                        new LatLng(11.220005, 106.919287),
                        new LatLng(11.227750, 106.913106),
                        new LatLng(11.230444, 106.915166),
                        new LatLng(11.237852, 106.917912),
                        new LatLng(11.240546, 106.911046),
                        new LatLng(11.243914, 106.922032),
                        new LatLng(11.245934, 106.930272),
                        new LatLng(11.265464, 106.927525),
                        new LatLng(11.278258, 106.929585),
                        new LatLng(11.293746, 106.936452),
                        new LatLng(11.314619, 106.942632),
                        new LatLng(11.325391, 106.951558),
                        new LatLng(11.345589, 106.944692),
                        new LatLng(11.355014, 106.940572),
                        new LatLng(11.356360, 106.913106),
                        new LatLng(11.342896, 106.902120),
                        new LatLng(11.340203, 106.882893),
                        new LatLng(11.326738, 106.871220),
                        new LatLng(11.315965, 106.872594),
                        new LatLng(11.309906, 106.873280),
                        new LatLng(11.316639, 106.855428),
                        new LatLng(11.332797, 106.858174),
                        new LatLng(11.344242, 106.863667),
                        new LatLng(11.354341, 106.858861),
                        new LatLng(11.375209, 106.856801),
                        new LatLng(11.382614, 106.824529),
                        new LatLng(11.377229, 106.815602),
                        new LatLng(11.381268, 106.801183),
                        new LatLng(11.380595, 106.792256),
                        new LatLng(11.398699, 106.785869),
                        new LatLng(11.406103, 106.772137),
                        new LatLng(11.428987, 106.769390),
                        new LatLng(11.435044, 106.785869),
                        new LatLng(11.446485, 106.802349),
                        new LatLng(11.459944, 106.806469),
                        new LatLng(11.472730, 106.803722),
                        new LatLng(11.489553, 106.798916),
                        new LatLng(11.484843, 106.769390),
                        new LatLng(11.480132, 106.761150),
                        new LatLng(11.474749, 106.752224),
                        new LatLng(11.466001, 106.750164),
                        new LatLng(11.444466, 106.748791),
                        new LatLng(11.429660, 106.725445),
                        new LatLng(11.424275, 106.724071),
                        new LatLng(11.416199, 106.715145),
                        new LatLng(11.405430, 106.709652),
                        new LatLng(11.398026, 106.708279),
                        new LatLng(11.399372, 106.699352),
                        new LatLng(11.402064, 106.691112),
                        new LatLng(11.399372, 106.689052),
                        new LatLng(11.401391, 106.677379),
                        new LatLng(11.400045, 106.673946),
                        new LatLng(11.389948, 106.672573),
                        new LatLng(11.375812, 106.678066),
                        new LatLng(11.363022, 106.676006),
                        new LatLng(11.358310, 106.675320),
                        new LatLng(11.361676, 106.648540),
                        new LatLng(11.357637, 106.635494),
                        new LatLng(11.358983, 106.625881),
                        new LatLng(11.363695, 106.614895),
                        new LatLng(11.377159, 106.612148),
                        new LatLng(11.387929, 106.614895),
                        new LatLng(11.387929, 106.593609),
                        new LatLng(11.383890, 106.592922),
                        new LatLng(11.381871, 106.584682),
                        new LatLng(11.373120, 106.564770),
                        new LatLng(11.373120, 106.557217),
                        new LatLng(11.385236, 106.546230),
                        new LatLng(11.390621, 106.539364),
                        new LatLng(11.399372, 106.544857),
                        new LatLng(11.418891, 106.544857),
                        new LatLng(11.428987, 106.543484),
                        new LatLng(11.441101, 106.558590),
                        new LatLng(11.453888, 106.573009),
                        new LatLng(11.474749, 106.562023),
                        new LatLng(11.484170, 106.554470),
                        new LatLng(11.476095, 106.549663),
                        new LatLng(11.468020, 106.537990),
                        new LatLng(11.476095, 106.529751),
                        new LatLng(11.489588, 106.503828),
                        new LatLng(11.501700, 106.439283),
                        new LatLng(11.501027, 106.424177),
                        new LatLng(11.479494, 106.415937),
                        new LatLng(11.469401, 106.407697),
                        new LatLng(11.460652, 106.398084),
                        new LatLng(11.453923, 106.378858),
                        new LatLng(11.433060, 106.364438),
                        new LatLng(11.424310, 106.362379),
                        new LatLng(11.410176, 106.356199),
                        new LatLng(11.395368, 106.356199),
                        new LatLng(11.385945, 106.349332),
                        new LatLng(11.373155, 106.345899),
                        new LatLng(11.363730, 106.339719),
                        new LatLng(11.341514, 106.336286),
                        new LatLng(11.325356, 106.343839),
                        new LatLng(11.318623, 106.346586),
                        new LatLng(11.303137, 106.336286),
                        new LatLng(11.278896, 106.354825),
                        new LatLng(11.267449, 106.353452),
                        new LatLng(11.257347, 106.360319),
                        new LatLng(11.242531, 106.361005),
                        new LatLng(11.217612, 106.386411),
                        new LatLng(11.208856, 106.388471),
                        new LatLng(11.186348, 106.385724),
                        new LatLng(11.186348, 106.397397),
                        new LatLng(11.170854, 106.427610),
                        new LatLng(11.148624, 106.433790),
                        new LatLng(11.140539, 106.439283),
                        new LatLng(11.136497, 106.452329),
                        new LatLng(11.158729, 106.455762),
                        new LatLng(11.160750, 106.467435),
                        new LatLng(11.141887, 106.483228),
                        new LatLng(11.141887, 106.503828),
                        new LatLng(11.127065, 106.517560),
                        new LatLng(11.112376, 106.530207),
                        new LatLng(11.100248, 106.518534),
                        new LatLng(11.088793, 106.531580),
                        new LatLng(11.058470, 106.537074),
                        new LatLng(11.049709, 106.546687),
                        new LatLng(11.054426, 106.567973),
                        new LatLng(11.050383, 106.580332),
                        new LatLng(11.040495, 106.587018),
                        new LatLng(11.033756, 106.602124),
                        new LatLng(11.005448, 106.616544),
                        new LatLng(10.981183, 106.626844),
                        new LatLng(10.973094, 106.644010),
                        new LatLng(10.956915, 106.652936),
                        new LatLng(10.948825, 106.652250),
                        new LatLng(10.929949, 106.652250),
                        new LatLng(10.921858, 106.664609),
                        new LatLng(10.921184, 106.681089),
                        new LatLng(10.915790, 106.686582),
                        new LatLng(10.898934, 106.692075),
                        new LatLng(10.881403, 106.689328),
                        new LatLng(10.867243, 106.705121),
                        new LatLng(10.872637, 106.720914),
                        new LatLng(10.874660, 106.720914),
                        new LatLng(10.879380, 106.714048),
                        new LatLng(10.882077, 106.715421),
                        new LatLng(10.888820, 106.715421),
                        new LatLng(10.896237, 106.715421),
                        new LatLng(10.892866, 106.722974),
                        new LatLng(10.888820, 106.730527),
                        new LatLng(10.880729, 106.731900),
                        new LatLng(10.878706, 106.740827),
                        new LatLng(10.873986, 106.746320),
                        new LatLng(10.863871, 106.751813),
                        new LatLng(10.865894, 106.757993),
                        new LatLng(10.867243, 106.762113),
                        new LatLng(10.878032, 106.758680),
                        new LatLng(10.882077, 106.759366),
                        new LatLng(10.892866, 106.765546)));
        this.mMap.setOnMapLongClickListener(this);
    }


    public void addcontrol() {
        btn = (Button) findViewById(R.id.qlmh2);
    }

    public void addEvent() {
        btn.setOnClickListener(new MyEvent());
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        getLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {
        gac.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "ERROR : " + connectionResult.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        try {
            Geocoder geo = new Geocoder(Address_Us.this.getApplicationContext(), Locale.getDefault());
            List<Address> addresses = geo.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (addresses.isEmpty()) {
                Address_u = "Waiting for Location";
            } else {
                if (addresses.size() > 0) {
                    dcx = String.valueOf(latLng.latitude);
                    dcy = String.valueOf(latLng.longitude);
                    Address_u = addresses.get(0).getFeatureName() + ", " + addresses.get(0).getLocality() + ", " + addresses.get(0).getAdminArea() + ", " + addresses.get(0).getCountryName();
                    btn.setText(Address_u);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class MyEvent implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.equals(btn)) {
                Askuser();
            }
        }
    }

    public void Askuser() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Address_Us.this, R.style.AlertDialogStyle);
        builder.setIcon(R.drawable.panda);
        builder.setTitle("Notification");
        builder.setMessage("Do you want to go back to home ?");
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Bundle bundle = new Bundle();
                bundle.putString("dcn", Address_u);
                bundle.putString("dcx", dcx);
                bundle.putString("dcy", dcy);
                intent = new Intent();
                intent.putExtra("dcc", bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }
}

