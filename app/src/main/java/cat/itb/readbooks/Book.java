package cat.itb.readbooks;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    String title,author,status,rate;
    Boolean isempty;

    public Book(){
        this.title="";
        this.author="";
        this.status="";
        this.rate="";
    }

    protected Book(Parcel in) {
        title = in.readString();
        author = in.readString();
        status = in.readString();
        rate = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(status);
        dest.writeString(rate);
    }
}
