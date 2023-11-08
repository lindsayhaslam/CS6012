package assignment02;
import java.util.GregorianCalendar;

public class LibraryBookGeneric<Type> extends Book {

    public Type holder;
    public GregorianCalendar dueDate;
    public Boolean isCheckedIn;
    public LibraryBookGeneric(long isbn, String author, String title) {
        super(isbn, author, title);
        holder = null;
        dueDate = null;
        isCheckedIn = true;
    }

    //Getter functions
    public Type getHolder(){

        return holder;
    }

    //setHolder
    public GregorianCalendar getDueDate(){

        return dueDate;
    }
    //If LibraryBook is checked in, holder and due date is set to null
//    public void checkIn() {
//        holder = null;
//        dueDate = null;
//        isCheckedIn = true;
//    }
//
//    public void checkOut(String holder, int year, int month, int day) {
//        this.holder = holder;
//        this.dueDate = new GregorianCalendar(year, month, day);
//        isCheckedIn = false;
//    }

    //Set dueDate
//    //public void setDueDate(dueDate this.dueDate = dueDate)
//    public void setDueDate(int year, int month, int day) {
//        dueDate = new GregorianCalendar(year, month, day);
//    }

}
