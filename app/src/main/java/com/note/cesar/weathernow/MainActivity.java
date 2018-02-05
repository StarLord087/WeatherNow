package com.note.cesar.weathernow;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.note.cesar.weathernow.API.APIClient;
import com.note.cesar.weathernow.API.ConstantsHolder;
import com.note.cesar.weathernow.models.CurrentConditionModel;
import com.note.cesar.weathernow.models.GeoPostionModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity implements LocationListener{

    TextView tvCity,tvUnit,tvTemperature,tvLocalizedName;
    LocationManager locationManager;
    String latLong = "";
    String locationKey = "";
    String APIKey = "P6eflaANHkOikO2vBjlId8H4XcQGc3YP";
    String language = "en-us";
    Boolean details = false;
    Boolean toplevel = false;
    Boolean gotKey = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCity = findViewById(R.id.tv_city);
        tvTemperature = findViewById(R.id.tv_temperature);
        tvLocalizedName = findViewById(R.id.tv_localizedName);
        tvUnit = findViewById(R.id.tv_unit);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    111);
        }

        else {
            updateLocation();
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        latLong = location.getLatitude()+","+location.getLongitude();
        getData(latLong);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 111) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                updateLocation();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @SuppressLint("MissingPermission")
    void updateLocation(){
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000,100f,this);
    }

    void getData(String latLong) {

        Call<GeoPostionModel> call = APIClient.getClient().getKey(APIKey, latLong, language, details, toplevel);
        call.enqueue(new Callback<GeoPostionModel>() {
            @Override
            public void onResponse(Call<GeoPostionModel> call, Response<GeoPostionModel> response) {
                if (response.isSuccessful()) {
                    locationKey = response.body().getKey();
                    ConstantsHolder.WEATHER += locationKey;
                    tvLocalizedName.setText(response.body().getLocalizedName());
                    tvCity.setText(response.body().getAdministrativeArea().getLocalizedName());
                    getCurrentdata();
                    Log.d("WEATHER CONSTANT", "onResponse: " + ConstantsHolder.WEATHER);


                } else {
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GeoPostionModel> call, Throwable t) {

            }
        });
    }

    void getCurrentdata(){
        Call<List<CurrentConditionModel>> call1 = APIClient.getClient().getWether(ConstantsHolder.WEATHER,APIKey,language,details);
        call1.enqueue(new Callback<List<CurrentConditionModel>>() {
            @Override
            public void onResponse(Call<List<CurrentConditionModel>> call, Response<List<CurrentConditionModel>> response) {
                if(response.isSuccessful()){

                    String str = ""+response.body().get(0).getTemperature().getMetric().getValue().toString();

                    tvTemperature.setText(str);
                    tvUnit.setText(response.body().get(0).getTemperature().getMetric().getUnit());
                }
                else {
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CurrentConditionModel>> call, Throwable t) {

            }
        });
    }

}
