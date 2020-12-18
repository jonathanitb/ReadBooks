package cat.itb.readbooks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<Book>booksList;

    public Adapter(List<Book> booksList) {
        this.booksList = booksList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_fragment_item,parent,false);
      return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        holder.bindData(booksList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.booksList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView author;
        TextView status;
        TextView score;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.tv_item_title);
            author=itemView.findViewById(R.id.tv_item_author);
            status=itemView.findViewById(R.id.tv_item_status);
            score=itemView.findViewById(R.id.tv_item_score);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavDirections listToDetailsBooks=BookListFragmentDirections.actionBookListFragmentToRegisterBookFragment(booksList.get(getAdapterPosition()));
                    Navigation.findNavController(v).navigate(listToDetailsBooks);
                }
            });

        }
        public void bindData(Book book){
            if(book!=null){
                title.setText(book.getTitle());
                author.setText(book.getAuthor());
                status.setText(book.getStatus());
                score.setText(book.getRate());

            }

        }
    }
}
