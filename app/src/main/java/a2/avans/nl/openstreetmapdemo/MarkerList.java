package a2.avans.nl.openstreetmapdemo;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.ItemizedOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

/**
 * Dit even in een class gestopt zodat je deze later kan vervangen door een data class met sql ofzo
 *
 * By Bob van der Valk
 */
public class MarkerList {
    private ArrayList<OverlayItem> markerList = new ArrayList<>();

    void prefillMarkerList() {
        markerList.add(new OverlayItem("Punt 1", "beschrijving", new GeoPoint(51.5855821, 4.789675699999975)));
        markerList.add(new OverlayItem("Punt 2", "beschrijving", new GeoPoint(51.8261248, 4.483456299999943)));
        markerList.add(new OverlayItem("Punt 3", "beschrijving", new GeoPoint(51.8939035,4.523135400000001)));
    }

    public void setMarkerList(ArrayList<OverlayItem> markerList) {
        this.markerList = markerList;
    }

    ArrayList<OverlayItem> getMarkerList() {
        return markerList;
    }
}
