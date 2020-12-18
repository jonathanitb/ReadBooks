package cat.itb.readbooks;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class RegisterBookFragment extends Fragment {
    EditText editText_title,editText_author;
    Spinner spinner_status,spinner_score;
    Button button_add;
    boolean add;
    Book book;
    TextView puntuacion;
    String selected_spinnerStatus, selected_spinnerScore, editText_titleContent,editText_AuthorContent;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.register_book_fragment, container, false);

        editText_title=v.findViewById(R.id.editText_mangaName);
        editText_author=v.findViewById(R.id.editText_mangaAuthor);
        spinner_status=v.findViewById(R.id.spinner_mangaStatus);
        spinner_score=v.findViewById(R.id.spinner_mangaScore);
        button_add=v.findViewById(R.id.button_registerManga);
        puntuacion=v.findViewById(R.id.tv_puntuacion);
        book = getArguments().getParcelable("book");

        editText_title.addTextChangedListener(textWatcher);
        editText_author.addTextChangedListener(textWatcher);


        if(book!=null){
            editText_title.setText(book.getTitle());
            editText_author.setText(book.getAuthor());
            spinner_score.setSelection(Integer.parseInt(book.getRate()));
            int position;
            switch (book.getStatus()){
                case "Leído":
                    position=1;
                    break;
                case "Planeado leer":
                    position=2;
                    break;
                default:
                    position=0;

            }
            spinner_status.setSelection(position);
            add=false;
      }else {
            add=true;
        }
        checkIsEmpty();

       spinner_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               String selectedClass=parent.getItemAtPosition(position).toString();
               if ("Leído".equals(selectedClass)) {
                   spinner_score.setVisibility(View.VISIBLE);
                   puntuacion.setVisibility(View.VISIBLE);
               } else {
                   spinner_score.setVisibility(View.INVISIBLE);
                   puntuacion.setVisibility(View.INVISIBLE);
               }
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerData();
                if(add){
                    createData();
                }else {
                    updateData();
                }
                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.action_registerBookFragment_to_bookListFragment);
            }
        });

        return v;
    }


    private TextWatcher textWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            checkIsEmpty();

        }

        @Override
        public void afterTextChanged(Editable s) {
            checkIsEmpty();

        }
    };

    public void registerData(){
        editText_titleContent=editText_title.getText().toString();
        editText_AuthorContent=editText_author.getText().toString();
        selected_spinnerScore=spinner_score.getSelectedItem().toString();
        selected_spinnerStatus=spinner_status.getSelectedItem().toString();
    }

    public void updateData(){
        book.setTitle(editText_titleContent);
        book.setAuthor(editText_AuthorContent);
        book.setRate(selected_spinnerScore);
        book.setStatus(selected_spinnerStatus);
    }
    public void createData(){
        Book newbook=new Book();
        newbook.setTitle(editText_titleContent);
        newbook.setAuthor(editText_AuthorContent);
        newbook.setRate(selected_spinnerScore);
        BookViewModel.booklist.add(newbook);

    }

    private void checkIsEmpty(){
        String et1=editText_title.getText().toString(),et2=editText_author.getText().toString();
        if(et1.length()>0&&et2.length()>0){
            button_add.setVisibility(View.VISIBLE);
        }else {
            button_add.setVisibility(View.INVISIBLE);
        }
    }



}
