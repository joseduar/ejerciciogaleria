package net.unadeca.ana.galeriajose.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import net.unadeca.ana.galeriajose.R;
import net.unadeca.ana.galeriajose.subclases.ImageViewHolder;
import net.unadeca.ana.galeriajose.subclases.RunImage;
import net.unadeca.ana.galeriajose.util.Adapter;
import net.unadeca.ana.galeriajose.util.Functions;
import net.unadeca.ana.galeriajose.util.ImageAdapter;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private Adapter mSectionsPagerAdapter;
    private static Context QuickContext;
    private static RecyclerView recyclerView;
    private Session session;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session = new Session(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        QuickContext = this;
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new Adapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        TabLayout tabLayout = findViewById(R.id.tabs);
        mSectionsPagerAdapter.addFragment(new ImageFragment(), "Galeria");
        mSectionsPagerAdapter.addFragment(new ImageFragment(), "Galeria Random");
        mSectionsPagerAdapter.addFragment(new ImageFragment(), "Galeria RX");
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
            //borrar esto asta finish
        }else if(id == R.id.logout){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setPositiveButton("si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    session.logoutUser();
                    finish();

                }
            }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();

                }
            }).setMessage("seguro que quiere cerrar la secion");
            builder.show();


        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */

    public static class ImageFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(QuickContext);
            layoutManager.setFlexWrap(FlexWrap.WRAP);
            layoutManager.setAlignItems(AlignItems.BASELINE);
            layoutManager.setJustifyContent(JustifyContent.CENTER);

            recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            setHasOptionsMenu(true);
            return rootView;
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            setupRecyclerView();
        }

        @Override
        public void onResume() {
            super.onResume();
        }

    }


    public static void setupRecyclerView() {
        List<RunImage> imagenes = new ArrayList<>();
        RunImage imagen = new RunImage();
        imagen.url = "https://cdn.pixabay.com/photo/2018/02/26/16/44/bird-3183441_960_720.jpg";
        imagen.author = "edmondlafoto";
        imagen.name = "Foto 1";

        RunImage imagen1 = new RunImage();
        imagen1.url = "https://cdn.pixabay.com/photo/2018/03/08/15/34/tree-3208922_960_720.jpg";
        imagen1.author = "edmondlafoto";
        imagen1.name = "Foto 1";

        for (int a = 0; a < 10; a++) {
            imagenes.add(imagen);
        }

        for (int a = 0; a < 10; a++) {
            imagenes.add(imagen1);
        }

        ImageAdapter adapter = new ImageAdapter(imagenes, QuickContext);
        recyclerView.setAdapter(adapter);
    }
}

