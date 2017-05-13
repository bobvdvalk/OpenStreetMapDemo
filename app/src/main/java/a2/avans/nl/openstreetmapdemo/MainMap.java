package a2.avans.nl.openstreetmapdemo;

import android.content.Context;
import android.location.Location;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MinimapOverlay;

public class MainMap extends AppCompatActivity {
    private MapView mapView;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        Configuration.getInstance().load(context, PreferenceManager.getDefaultSharedPreferences(context));
        setContentView(R.layout.activity_main_map);
        mapView = (MapView) findViewById(R.id.map);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);
        this.customMapSettings();
    }

    public void onResume() {
        super.onResume();
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
    }

    private void customMapSettings() {
        IMapController mapController = mapView.getController();
        mapController.setZoom(19);
        GeoPoint geoPoint = new GeoPoint(51.827373, 4.483371); // emulator testing
        //deze gebruiken als je een echt android apparaat hebt.
        //Location location = new Location("");
        //GeoPoint geoPoint = new GeoPoint(location.getLatitude(), location.getLongitude());
        mapController.setCenter(geoPoint);
    }
}
