package cat.itb.readbooks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    public static BookViewModel bookViewModel;
    public NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (bookViewModel == null){
            bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);
        }



        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();

//        Fragment books=getSupportFragmentManager().findFragmentById(R.id.fragment_container);
//        if(books==null){
//            books=new BookListFragment();
//            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,books).commit();
        //      }
        }
   }

