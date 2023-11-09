//LINDSAY HASLAM
package assignment02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    private Library library;

    @BeforeEach
    void setUp() {
        library = new Library();
    }
    @Test
    public void testEmpty() {
        Library lib = new Library();
        assertNull(lib.lookup(978037429279L));

        ArrayList<LibraryBook> booksCheckedOut = lib.lookup("Jane Doe");
        assertEquals(booksCheckedOut.size(), 0);

        assertFalse(lib.checkout(978037429279L, "Jane Doe", 1, 1, 2008));
        assertFalse(lib.checkin(978037429279L));
        assertFalse(lib.checkin("Jane Doe"));
    }

    @Test
    public void testCheckInBookNotFound() {
        // Try to check in a book that is not in the library
        boolean result = library.checkin(9780374292799L);

        // Expect that the check in method returns false
        assertFalse(result);
    }

    @Test
    public void testNonEmpty() {

        //Create new library
        var lib = new Library();
        // Add a small amount of books to library
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");

        //Assert that this specific ISBN is not checked out
        assertNull(lib.lookup(9780330351690L));
        var res = lib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008);
        assertTrue(res);
        ArrayList<LibraryBook> booksCheckedOut = lib.lookup("Jane Doe");
        assertEquals(booksCheckedOut.size(), 1);
        assertEquals(booksCheckedOut.get(0),new Book(9780330351690L, "Jon Krakauer", "Into the Wild"));
        assertEquals(booksCheckedOut.get(0).getHolder(), "Jane Doe");
        assertEquals(booksCheckedOut.get(0).getDueDate(),new GregorianCalendar(2008, 1, 1));
        res = lib.checkin(9780330351690L);
        assertTrue(res);
        res = lib.checkin("Jane Doe");
        assertFalse(res);
    }

    @Test
    public void testLargeLibrary(){
        // test a medium library
        var lib = new Library();
        lib.addAll("Mushroom_Publishing.txt");
    }

    @Test
    public void stringLibraryTest() {
        // test a library that uses names (String) to id patrons
        Library<String> lib = new Library<>();
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");
        //Assign Jane Done to patron1
        String patron1 = "Jane Doe";

        assertTrue(lib.checkout(9780330351690L, patron1, 1, 1, 2008));
        assertTrue(lib.checkout(9780374292799L, patron1, 1, 1, 2008));

        var booksCheckedOut1 = lib.lookup(patron1);
        //Check the size of booksCheckedOut1
        assertEquals(booksCheckedOut1.size(), 2);
        //Assert what it should contain
        assertTrue(booksCheckedOut1.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild")));
        assertTrue(booksCheckedOut1.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
        //Verify holder and due date for both books
        assertEquals(booksCheckedOut1.get(0).getHolder(), patron1);
        assertEquals(booksCheckedOut1.get(0).getDueDate(), new GregorianCalendar(2008, 1, 1));
        assertEquals(booksCheckedOut1.get(1).getHolder(),patron1);
        assertEquals(booksCheckedOut1.get(1).getDueDate(),new GregorianCalendar(2008, 1, 1));

        assertTrue(lib.checkin(patron1));

    }

    @Test
    public void phoneNumberTest(){
        // Create a new library with type <PhoneNumber>
        var lib = new Library<PhoneNumber>();
        //Add to library
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");

        //Create patron2 with assigned phone number
        PhoneNumber patron2 = new PhoneNumber("801.555.1234");

        //AssertTrue that books are checked out and are assigned a date
        assertTrue(lib.checkout(9780330351690L, patron2, 1, 1, 2008));
        assertTrue(lib.checkout(9780374292799L, patron2, 1, 1, 2008));

        //Use lookup to find patron2 and assign it to ArrayList of PhoneNumber called booksCheckedOut2
        ArrayList<LibraryBook<PhoneNumber>> booksCheckedOut2 = lib.lookup(patron2);

        //Check the size of the ArrayList
        assertEquals(booksCheckedOut2.size(), 2);
        //Assert that it contains the two checked out books
        assertTrue(booksCheckedOut2.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild")));
        assertTrue(booksCheckedOut2.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
        //Assert who the holder is for book 0.
        assertEquals(booksCheckedOut2.get(0).getHolder(),patron2);
        //Assert the due date for book 0.
        assertEquals(booksCheckedOut2.get(0).getDueDate(),new GregorianCalendar(2008, 1, 1));
        //Assert the holder for book 1.
        assertEquals(booksCheckedOut2.get(1).getHolder(), patron2);
        //Assert the due date for book 1.
        assertEquals(booksCheckedOut2.get(1).getDueDate(), new GregorianCalendar(2008, 1, 1));

        assertTrue(lib.checkin(patron2));

    }
    @Test
    public void testOverDueBookListSmall(){
        //Create a new library
        Library<String> lib=new Library<>();

        //Add books to the library
        lib.add(1L, "Author1", "Book1");
        lib.add(2L, "Author2", "Book2");
        lib.add(3L, "Author3", "Book3");
        lib.add(4L, "Author4", "Book4");

        //Checkout the books with a due date
        lib.checkout(1L, "holder1", 10, 1, 2023);
        lib.checkout(3L, "holder3", 2, 1, 2024);
        lib.checkout(2L, "holder2", 9, 10, 2023);
        lib.checkout(4L, "holder4", 9, 1, 2023);

        //Create an instance of OrderByDueDate
        var overdueList=lib.getOverdueList(11, 1, 2023);

        //Verify that the list is sorted in ascending order of due dates
        assertEquals(overdueList.get(0).getIsbn(), 4);
        assertEquals(overdueList.get(1).getIsbn(), 2);
        assertEquals(overdueList.get(2).getIsbn(), 1);
        assertEquals(overdueList.size(), 3);
    }

    @Test
    public void orderByDueDateTest(){
        // Create a library
        Library<String> lib=new Library<>();

        //Add books to library
        lib.add(1L, "Author1", "Book1");
        lib.add(2L, "Author2", "Book2");
        lib.add(3L, "Author3", "Book3");
        lib.add(4L, "Author4", "Book4");

        //Checkout the books with a due date
        lib.checkout(1L, "holder1", 12, 1, 2023);
        lib.checkout(3L, "holder3", 2, 1, 2024);
        lib.checkout(2L, "holder2", 1, 1, 2024);
        lib.checkout(4L, "holder4", 9, 1, 2022);

        // Create an instance of OrderByDueDate
        var overdueList=lib.getOverdueList(11, 1, 2023);

        // Verify that the list is sorted in ascending order of due dates
        assertEquals(overdueList.get(0).getIsbn(), 4);
        assertEquals(overdueList.size(), 1);
    }
    @Test
    public void testGetOverdueList() {
        //Test a medium library
        var lib = new Library();
        //Add Mushroom Publishing text to lib
        lib.addAll("Mushroom_Publishing.txt");

        //Check out multiple books
        var checkout1 = lib.checkout(9781843190042L, "Jane Doe", 1, 1, 2008);
        var checkout2 = lib.checkout(9781843190073L, "Jane Doe", 1, 1, 2008);
        var checkout3 = lib.checkout(9781843190110L, "Jane Doe", 1, 1, 2008);
        var checkout4 = lib.checkout(9781843190349L, "Jane Doe", 1, 1, 2024);
        var checkout5 = lib.checkout(9781843190363L, "Jane Doe", 1, 1, 2024);

        //Create Arraylist of LibraryBook called overdueList
        //Call getOverdueList which takes in a month, day, and year
        ArrayList<LibraryBook> overdueList = lib.getOverdueList(11,1,2022);
        //Check that the overdueList size is 3
        assertEquals(overdueList.size(), 3);
    }
    @Test
    public void testOrderedByAuthor(){
        //Initialize library to test
        Library<String> lib = new Library<>();
        lib.addAll("Mushroom_Publishing.txt");

        //Test 1 - Testing sort by author.
        var sortedByAuthor = lib.getOrderedByAuthor();
        assertEquals(sortedByAuthor.get(0).getAuthor(), "Alan Burt Akers");

        //Test 2 - Check if the size of the sorted list matches the original library size
        assertEquals(sortedByAuthor.size(), lib.size());

        //Test 3 - Check that the sort function is working in ascending order
        for (int i = 1; i < sortedByAuthor.size(); i++) {
            String author1 = sortedByAuthor.get(i - 1).getAuthor();
            String author2 = sortedByAuthor.get(i).getAuthor();
            assertTrue(author1.compareTo(author2) <= 0);
        }

        //Test 4 - An empty library doesn't cause an error if put through the sorted function and stays empty
        Library<String> emptyLib = new Library<>();
        var sortedEmptyLib = emptyLib.getOrderedByAuthor();
        assertTrue(sortedEmptyLib.isEmpty());

        //Test 5 - Check that an empty library throws an out of bounds error if there is an attempt to index in
        assertThrows(IndexOutOfBoundsException.class, () -> {sortedEmptyLib.get(0);});

        //Test 6 - Books with the same author get sorted by title
        String sameAuthor = "Moyra Caldecott";
        int firstIndex = sortedByAuthor.indexOf(new LibraryBook<>(9781843190028L, sameAuthor, "Crystal Legends"));
        int lastIndexWithSameAuthor = sortedByAuthor.lastIndexOf(new LibraryBook<>(9781843190004L, sameAuthor, "Weapons of the Wolfhound"));
        assertTrue(firstIndex < lastIndexWithSameAuthor);

        //Test 7 - Check that adding a book (to the end) and then sorting puts it in the right spot
        int origSize = lib.size();
        lib.add(22L, "AAA New Author", "New Title");
        assertEquals(lib.size(), origSize+1);
        var updatedSortedByAuthor = lib.getOrderedByAuthor();
        assertEquals(updatedSortedByAuthor.get(0).getAuthor(), "AAA New Author");
    }
}