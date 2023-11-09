//LINDSAY HASLAM
package assignment02;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Class representation of a library (a collection of library books).
 *
 */
public class Library<Type> {

    private ArrayList<LibraryBook<Type>> library;

    public Library() {
        library = new ArrayList<LibraryBook<Type>>();
    }
    public int size() {
        return library.size();
    }

    /**
     * Add the specified book to the library, assume no duplicates.
     *
     * @param isbn
     *          -- ISBN of the book to be added
     * @param author
     *          -- author of the book to be added
     * @param title
     *          -- title of the book to be added
     */
    public void add(long isbn, String author, String title) {
        library.add(new LibraryBook<>(isbn, author, title));
    }

    /**
     * Add the list of library books to the library, assume no duplicates.
     *
     * @param list
     *          -- list of library books to be added
     */
    public void addAll(ArrayList<LibraryBook<Type>> list) {
        library.addAll(list);
    }

    /**
     * Add books specified by the input file. One book per line with ISBN, author,
     * and title separated by tabs.
     *
     * If file does not exist or format is violated, do nothing.
     *
     * @param filename
     */
    public void addAll(String filename) {
        ArrayList<LibraryBook<Type>> toBeAdded = new ArrayList<LibraryBook<Type>>();

        try (Scanner fileIn = new Scanner(new File(filename))) {

            int lineNum = 1;

            while (fileIn.hasNextLine()) {
                String line = fileIn.nextLine();

                try (Scanner lineIn = new Scanner(line)) {
                    lineIn.useDelimiter("\\t");

                    if (!lineIn.hasNextLong()) {
                        throw new ParseException("ISBN", lineNum);
                    }
                    long isbn = lineIn.nextLong();

                    if (!lineIn.hasNext()) {
                        throw new ParseException("Author", lineNum);
                    }
                    String author = lineIn.next();

                    if (!lineIn.hasNext()) {
                        throw new ParseException("Title", lineNum);
                    }
                    String title = lineIn.next();
                    toBeAdded.add(new LibraryBook<Type>(isbn, author, title));
                }
                lineNum++;
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage() + " Nothing added to the library.");
            return;
        } catch (ParseException e) {
            System.err.println(e.getLocalizedMessage() + " formatted incorrectly at line " + e.getErrorOffset()
                    + ". Nothing added to the library.");
            return;
        }

        library.addAll(toBeAdded);
    }

    /**
     * Returns the holder of the library book with the specified ISBN.
     * <p>
     * If no book with the specified ISBN is in the library, returns null.
     *
     * @param isbn -- ISBN of the book to be looked up
     */
    public Type lookup(long isbn) {
        //Iterate through the library to find book
        for (LibraryBook<Type> book : library) {
            //Checks if the ISBN matches input
            if (book.getIsbn() == isbn) {
                //Check if the book is currently checked out
                if (book.holder != null) {
                    //return the holder of the book
                    return book.holder;
                }
            }
        }
        return null;
    }

    /**
     * Returns the list of library books checked out to the specified holder.
     *
     * If the specified holder has no books checked out, returns an empty list.
     *
     * @param holder
     *          -- holder whose checked out books are returned
     */
    public ArrayList<LibraryBook<Type>> lookup(Type holder) {

        //Create a new ArrayList to store all books checked out by holder
        ArrayList<LibraryBook<Type>> holdersBooks = new ArrayList<>();

        //Iterate through LibraryBook object in library
        for(LibraryBook<Type> book: library){
            //Check if book is currently checked out and the holder matches the input
            if (book.getHolder() != null && book.getHolder().equals(holder)){
                //Add book to holdersBook list
                holdersBooks.add(book);
            }
        }
        return holdersBooks;
    }

    /**
     * Sets the holder and due date of the library book with the specified ISBN.
     *
     * If no book with the specified ISBN is in the library, returns false.
     *
     * If the book with the specified ISBN is already checked out, returns false.
     *
     * Otherwise, returns true.
     *
     * @param isbn
     *          -- ISBN of the library book to be checked out
     * @param holder
     *          -- new holder of the library book
     * @param month
     *          -- month of the new due date of the library book
     * @param day
     *          -- day of the new due date of the library book
     * @param year
     *          -- year of the new due date of the library book
     *
     */
    public boolean checkout(long isbn, Type holder, int month, int day, int year) {
        //Iterate through each book in the library
        for (LibraryBook<Type> book : library) {
            //Check if the ISBN of the current book in the loop matches the provided ISBN
            if (book.getIsbn() == isbn) {
                //Check if it's already checked out
                if (book.holder != null) {
                    //Checks if the book is already checked out by the same holder
                    if (book.holder.equals(holder)) {
                        return false; // Book is already checked out by the same holder
                    } else {
                        return false; // Book is already checked out by a different holder
                    }
                } else {
                    //Set the book's holder to provided holder
                    book.holder = holder;
                    //Sets the book's due date to the provided date
                    book.dueDate = new GregorianCalendar(year, month, day);
                    return true;
                }
            }
        }
        return false; // Book with the given ISBN not found
    }

    /**
     * Unsets the holder and due date of the library book.
     *
     * If no book with the specified ISBN is in the library, returns false.
     *
     * If the book with the specified ISBN is already checked in, returns false.
     *
     * Otherwise, returns true.
     *
     * @param isbn
     *          -- ISBN of the library book to be checked in
     */
    public boolean checkin(long isbn) {
        //search through the library for book
        for (LibraryBook<Type> book : library) {
            //Check if the ISBN matches input
            if (book.getIsbn() == isbn) {
                //Check if the book is currently checked out
                if (book.holder != null) {
                    //Set the holder and due date to null
                    book.holder = null;
                    book.dueDate = null;
                    //Indicate successful check-in
                    return true;
                } else {
                    return false; // Book is not checked out
                }
            }
        }
        return false; // Book with the given ISBN not found
    }

    /**
     * Unsets the holder and due date for all library books checked out be the
     * specified holder.
     *
     * If no books with the specified holder are in the library, returns false;
     *
     * Otherwise, returns true.
     *
     * @param holder
     *          -- holder of the library books to be checked in
     */
    public boolean checkin(Type holder) {
        //Used to indicate whether at least one book checked out by holder was found
        boolean found = false;
        //Iterate over library to find book
        for (LibraryBook<Type> book : library) {
            //Checks if the holder matches provided holder
            if (book.holder == holder) {
                //Set holder and due date to null to indicate book is not checked out
                book.holder = null;
                book.dueDate = null;
                //Indicate at least one book was found and checked in
                found = true;
            }
        }
        return found;
    }

    /**
     * Returns the list of library books, sorted by ISBN (smallest ISBN
     first).
     */
    public ArrayList<LibraryBook<Type>> getInventoryList() {
        ArrayList<LibraryBook<Type>> libraryCopy = new ArrayList<LibraryBook<Type>>();
        libraryCopy.addAll(library);
        OrderByIsbn comparator = new OrderByIsbn();
        sort(libraryCopy, comparator);
        return libraryCopy;
    }

    /**
     * Returns the list of library books, sorted by author
     */
    public ArrayList<LibraryBook<Type>> getOrderedByAuthor() {
        //Create a new arrayList to sore the copy of the library.
        ArrayList<LibraryBook<Type>> libraryCopy = new ArrayList<LibraryBook<Type>>();
        //Copies all elements from the original library to libraryCopy.
        libraryCopy.addAll(library);
        //Declare a new comparator of type OrderByAuthor
        OrderByAuthor comparator = new OrderByAuthor();
        //sorting according to the OrderByAuthor comparator
        sort(libraryCopy, comparator);
        return libraryCopy;
    }

    /**
     * Returns the list of library books whose due date is older than the
     input
     * date. The list is sorted by date (oldest first).
     *
     * If no library books are overdue, returns an empty list.
     */
    //Only store the ones that are checked out, not the entire library.
    public ArrayList<LibraryBook<Type>> getOverdueList(int month, int
            day, int year) {
        //Create a new GregorianCalendar object
        GregorianCalendar dueDate = new GregorianCalendar(year, month, day);
        //Create a new overDueList to store overdue books
        ArrayList<LibraryBook<Type>> overDueList = new ArrayList<>();
        //Iterate through each book in the library
        for(LibraryBook<Type> book: library) {
            //If there is a due date
            if (book.getDueDate()!=null) {
                //Compare input due date with the book's due date
                int res = book.getDueDate().compareTo(dueDate);
                //If book is overdue, add it to overDueList
                if (res < 0) {
                    overDueList.add(book);
                }
            }
        }
        //Create new comparator from OrderByDueDate
        OrderByDueDate comparator=new OrderByDueDate();
        //Sort overDueList by tehe comparator
        sort (overDueList, comparator);
        return overDueList;
    }

    /**
     * Performs a SELECTION SORT on the input ArrayList.
     * 1. Find the smallest item in the list.
     * 2. Swap the smallest item with the first item in the list.
     * 3. Now let the list be the remaining unsorted portion
     * (second item to Nth item) and repeat steps 1, 2, and 3.
     */
    private static <ListType> void sort(ArrayList<ListType> list, Comparator<ListType> c) {
        for (int i = 0; i < list.size() - 1; i++) {
            int j, minIndex;
            for (j = i + 1, minIndex = i; j < list.size(); j++)
                if (c.compare(list.get(j), list.get(minIndex)) < 0)
                    minIndex = j;
            ListType temp = list.get(i);
            list.set(i, list.get(minIndex));
            list.set(minIndex, temp);
        }
    }

    /**
     * Comparator that defines an ordering among library books using the
     ISBN.
     */
    protected class OrderByIsbn implements Comparator<LibraryBook<Type>> {
        /**
         * Returns a negative value if lhs is smaller than rhs. Returns a positive
         * value if lhs is larger than rhs. Returns 0 if lhs 	and rhs are equal.
         */
        public int compare(LibraryBook<Type> lhs, LibraryBook<Type> rhs) {
            return (int) (lhs.getIsbn() - rhs.getIsbn());
        }
    }

    /**
     * Comparator that defines an ordering among library books using the
     author, and book title as a tie-breaker.
     */
    protected class OrderByAuthor implements Comparator<LibraryBook<Type>> {
        @Override
        public int compare(LibraryBook<Type> lhs, LibraryBook<Type> rhs) {
            int authorComparison = lhs.getAuthor().compareTo(rhs.getAuthor());

            // If authors are the same, compare titles
            if (authorComparison == 0) {
                return lhs.getTitle().compareTo(rhs.getTitle());
            }

            return authorComparison;
        }
    }

    /**
     * Comparator that defines an ordering among library books using the
     due date.
     */
    protected class OrderByDueDate implements Comparator<LibraryBook<Type>> {
        public int compare(LibraryBook<Type> lhs, LibraryBook<Type> rhs) {
            return lhs.getDueDate().compareTo(rhs.getDueDate());
        }
    }
}