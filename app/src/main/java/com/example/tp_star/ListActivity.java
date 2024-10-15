package com.example.tp_star;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_star.adapter.StarAdapter;
import com.example.tp_star.beans.Star;
import com.example.tp_star.service.StarService;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private  List<Star> stars;
    private RecyclerView recyclerview;
    private StarAdapter starAdapter = null;
    private StarService service;


    @Override
    protected void onPause() {
        super.onPause();
        //this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("AZI-ZOO");

        stars = new ArrayList<>();
        service = StarService.getInstance();

        init();
        starAdapter = new StarAdapter(this,service.findAll());

        recyclerview = findViewById(R.id.recycle_view);
        recyclerview.setAdapter(starAdapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));


    }

    public void init(){
        service.create(new Star("Lion","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9gg6fq8rAKIeKQKcR-h-Y-XAfesKlN_iQ4Q&s",5f));
        service.create(new Star("Giraffe","https://cdn.artphotolimited.com/images/58cacb4500dcd4000b3b67c3/300x300/tour-de-controle-de-la-savane.jpg",2f));
        service.create(new Star("Paon","https://st.depositphotos.com/1962533/1872/i/450/depositphotos_18724447-stock-photo-peacock-with-fanned-tail.jpg",4.5f));
        service.create(new Star("Cheval","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQVyLRJD_xW-wgkGoK9F0MYTXfvd_oYybudUQ&s",3f));
        service.create(new Star("Pingouin","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQv4XewugdH7C9uzF26odtzTTrkByI47nwSRQ&s",1.5f));
        service.create(new Star("Gorille","https://upload.wikimedia.org/wikipedia/commons/thumb/6/6e/Gorilla_gorilla_gorilla8.jpg/1200px-Gorilla_gorilla_gorilla8.jpg",3.5f));
        service.create(new Star("Écureuil","https://www.larousse.fr/encyclopedie/data/images/1309522-Ecureuil.jpg",4.5f));
        service.create(new Star("Perroquet","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQRCvCZ7f0l134Lng0nDEyWeZ2goZPIlCRAtw&s",4f));
        service.create(new Star("Zèbre","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQv1L-lA85EOYp1V3zxAIkTxXEwqETTfCp3Q&s",3f));
        service.create(new Star("Tigre","https://upload.wikimedia.org/wikipedia/commons/thumb/4/41/Siberischer_tiger_de_edit02.jpg/640px-Siberischer_tiger_de_edit02.jpg",2.5f));
        service.create(new Star("Kangourou","https://media.gqmagazine.fr/photos/5e9eebe75d2f1100086a5af3/16:9/w_2560%2Cc_limit/n-kangourou.jpg",3f));
        service.create(new Star("Dauphin","https://thumbs.dreamstime.com/b/dauphin-7672006.jpg",5f));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView)
                MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                                      @Override
                                                      public boolean onQueryTextSubmit(String query) {
                                                          return true;
                                                      }
            @Override
            public boolean onQueryTextChange(String newText) {
                if (starAdapter != null){
                    starAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.share) {
            showPopupMenu(findViewById(R.id.share)); // Appelle la méthode pour afficher le PopupMenu
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showPopupMenu(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.action_share) {
                    // Code de partage ici
                    String txt = "Stars";
                    String mimeType = "text/plain";
                    ShareCompat.IntentBuilder
                            .from(ListActivity.this)
                            .setType(mimeType)
                            .setChooserTitle("Stars")
                            .setText(txt)
                            .startChooser();
                    return true;
                }
                return false;
            }
        });

        popup.show(); // Affiche le PopupMenu
    }


}
