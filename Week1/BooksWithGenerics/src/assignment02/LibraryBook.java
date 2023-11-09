//LINDSAY HASLAM
package assignment02;
import java.util.GregorianCalendar;


public class LibraryBook<Type> extends Book {

    public Type holder;
    public GregorianCalendar dueDate;
    public Boolean isCheckedIn;
    public LibraryBook(long isbn, String author, String title) {
        super(isbn, author, title);
        holder = null;
        dueDate = null;
        isCheckedIn = true;
    }

    //Getter functions
    public Type getHolder(){

        return holder;
    }
    public GregorianCalendar getDueDate(){
        return dueDate;
    }
}
