package ar.recicl.reciclar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.appdatasearch.Feature;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import ar.recicl.reciclar.R;
import ar.recicl.reciclar.data.RecyclingCenter;
import butterknife.Bind;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class Map extends Base {

    @Bind(R.id.name_text_view) TextView mNameTextView;
    @Bind(R.id.location_text_view) TextView mLocationTextView;
    @Bind(R.id.circle_image_view) CircleImageView mCircleImageView;
    @Bind(R.id.map_view) MapView mMapView;

    RecyclingCenter mRecyclingCenter;
    GoogleMap mMap;

    public Map() {
        super(R.layout.activity_map, R.menu.map, R.string.title_activity_map, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRecyclingCenter = RecyclingCenter.getRecyclingCenter(getIntent().getIntExtra("id", 0));

        mMapView.onCreate(savedInstanceState);

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                try {
                    mMap.setMyLocationEnabled(true);
                } catch (SecurityException e) {}

                // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
                MapsInitializer.initialize(Map.this);

                // Updates the location and zoom of the MapView
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                        new LatLng(mRecyclingCenter.getLat(), mRecyclingCenter.getLong()), 15);
                mMap.animateCamera(cameraUpdate);

                mMap.addMarker(new MarkerOptions().position(
                        new LatLng(mRecyclingCenter.getLat(), mRecyclingCenter.getLong())).icon(
                        BitmapDescriptorFactory.defaultMarker()));
            }
        });
    }

    @Override
    public void onResume() {
        mMapView.onResume();
        super.onResume();

        Picasso.with(this).load(mRecyclingCenter.getPictureRes()).into(mCircleImageView);
        mNameTextView.setText(mRecyclingCenter.getName());
        mLocationTextView.setText(mRecyclingCenter.getAddress());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_ok) {
            Intent intent = new Intent(this, Feed.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
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
