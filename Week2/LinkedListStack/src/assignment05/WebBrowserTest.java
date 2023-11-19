package assignment05;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.net.MalformedURLException;
import java.net.URL;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

public class WebBrowserTest {

    private WebBrowser webBrowser;

    @Before
    public void setUp() {
        webBrowser = new WebBrowser();
    }

    @Test
    public void visit() throws MalformedURLException {
        URL webpage = new URL("https://www.example.com");
        webBrowser.visit(webpage);
        assertEquals("https://www.example.com", webBrowser.history().getFirst().toString());
    }

    @Test(expected = NoSuchElementException.class)
    public void testBackEmptyHistory() throws NoSuchElementException {
        webBrowser.back();
    }

    @Test
    public void back() throws MalformedURLException {
        URL webpage1 = new URL("https://www.example.com");
        URL webpage2 = new URL("https://www.example2.com");
        webBrowser.visit(webpage1);
        webBrowser.visit(webpage2);

        //Go back to webpage1.
        String result = webBrowser.back().toString();
        assertEquals("https://www.example.com", result);

        //Check if the history is empty after going back to the first page.
        assertNull(webBrowser.back());
        assertTrue(webBrowser.history().isEmpty());

        //Check if the forward history is cleared.
        assertTrue(webBrowser.getForwardStack().isEmpty());
    }

    @Test
    public void forward() throws MalformedURLException {
        URL webpage1 = new URL("https://www.example.com");
        URL webpage2 = new URL("https://www.example2.com");
        webBrowser.visit(webpage1);
        webBrowser.visit(webpage2);
        //Move back to webpage1.
        webBrowser.back();

        //Result is webpage2.
        String result = webBrowser.forward().toString();
        assertEquals("https://www.example2.com", result);
        //Assert that webpage1 is the first in history.
        assertEquals("https://www.example.com", webBrowser.history().getFirst().toString());
    }

    @Test(expected = NoSuchElementException.class)
    public void testForwardEmptyHistory() throws NoSuchElementException {
        webBrowser.forward();
    }

    @Test
    public void history() throws MalformedURLException {
        URL webpage1 = new URL("https://www.example.com");
        URL webpage2 = new URL("https://www.example2.com");
        URL webpage3 = new URL("https://www.example3.com");

        webBrowser.visit(webpage1);
        webBrowser.visit(webpage2);
        webBrowser.visit(webpage3);

        SinglyLinkedList<URL> history = webBrowser.history();

        //Check if the history contains the correct URLs in the correct order.
        assertEquals(3, history.size());
        assertEquals(webpage3, history.getFirst());
        assertEquals(webpage2, history.get(1));
        assertEquals(webpage1, history.get(2));
    }
}