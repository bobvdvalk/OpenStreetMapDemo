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
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.MinimapOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay;

import java.util.ArrayList;

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
        mapController.setZoom(19); // zoom level
        // locatie instellen
        GeoPoint geoPoint = new GeoPoint(51.827373, 4.483371); // emulator testing
        //deze gebruiken als je een echt android apparaat hebt.
        //Location location = new Location("");
        //GeoPoint geoPoint = new GeoPoint(location.getLatitude(), location.getLongitude());
        mapController.setCenter(geoPoint);

        // vinger rotatie accepteren
        RotationGestureOverlay mRotationGestureOverlay = new RotationGestureOverlay(context, mapView);
        mRotationGestureOverlay.setEnabled(true);
        mapView.setMultiTouchControls(true);
        mapView.getOverlays().add(mRotationGestureOverlay);
        this.setMarkers();
    }

    private void setMarkers() {
        MarkerList markerList = new MarkerList();
        markerList.prefillMarkerList();
        ArrayList<OverlayItem> items = markerList.getMarkerList();
        ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<OverlayItem>(
                this, items,
                new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                    @Override
                    public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
                        //do something
                        return true;
                    }
                    @Override
                    public boolean onItemLongPress(final int index, final OverlayItem item) {
                        return false;
                    }
                });  // <----- removed the mResourceProxy parameter
        mOverlay.setFocusItemsOnTap(true);

        mapView.getOverlays().add(mOverlay);
    }
}
