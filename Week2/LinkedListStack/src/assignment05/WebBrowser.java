package assignment05;
import java.net.URL;
import java.util.NoSuchElementException;

public class WebBrowser {
    private SinglyLinkedList<URL> history;
    private LinkedListStack<URL> forwardStack;
    URL currentWebpage_;

    /**
     * First constructor.
     * Represents a simple web browser with basic navigation functionality.
     */
    public WebBrowser() {
        history = new SinglyLinkedList<>();
        forwardStack = new LinkedListStack<>();
    }
    /**
     * Second constructor.
     * Constructs a new web browser with an empty history and forward stack.
     */

    public WebBrowser(SinglyLinkedList<URL> history) {
        this.history = history;
        forwardStack = new LinkedListStack<>();
    }

    /**
     * Visits a webpage, adding it to the browsing history and clearing the forward history.
     *
     * @param webpage the URL of the webpage to visit
     */
    public void visit(URL webpage) {
        history.insertFirst(webpage);
        //Clear forward history.
        forwardStack.clear();
        currentWebpage_ = webpage;
    }

    /**
     * Navigates back to the previous webpage in the history.
     *
     * @return the URL of the previous webpage, or null if the history is empty
     * @throws NoSuchElementException if there is no webpage history
     */
    public URL back() throws NoSuchElementException {
        if (history.isEmpty()) {
            throw new NoSuchElementException("No webpage history.");
        }

        //Remove current URL from history.
        currentWebpage_ = history.deleteFirst();
        forwardStack.push(currentWebpage_);

        //Clear forward history if going back to the first page.
        if (history.isEmpty()) {
            forwardStack.clear();
        }

        return history.isEmpty() ? null : history.getFirst();
    }
    /**
     * Navigates forward to the next webpage in the forward history.
     *
     * @return the URL of the next webpage
     * @throws NoSuchElementException if there is no forward history
     */
    public URL forward() throws NoSuchElementException {
        if (forwardStack.isEmpty()) {
            throw new NoSuchElementException("No forward history.");
        }

        //Pop the top URL from forwardStack.
        URL forwardURL = forwardStack.pop();

        //Push the current webpage to the forwardStack.
        forwardStack.push(currentWebpage_);

        //Update the current webpage to the one from forward history.
        currentWebpage_ = forwardURL;

        return currentWebpage_;
    }

    /**
     * Gets the browsing history.
     *
     * @return the browsing history
     */
    public SinglyLinkedList<URL> history() {
        return history;
    }

    /**
     * Gets the forward history stack.
     *
     * @return the forward history stack
     */
    public LinkedListStack<URL> getForwardStack() {
        return forwardStack;
    }
}