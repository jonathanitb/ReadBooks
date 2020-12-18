package cat.itb.readbooks;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class BookViewModel extends ViewModel {
    public static List<Book>booklist=new ArrayList<>();
    public static Adapter adapter;

    public BookViewModel(){
        Book book;

        for(int i=1;i<3;i++){
            book=new Book();
            book.setTitle("Title "+i);
            book.setAuthor("Author "+i);
            book.setStatus("Leído");
            book.setRate(""+i);

            booklist.add(book);
        }

        adapter=new Adapter(booklist);
    }
}
