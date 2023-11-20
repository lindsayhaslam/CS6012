package assignment05;
import java.net.URL;
import java.util.NoSuchElementException;

public class WebBrowser {
    private SinglyLinkedList<URL> history;
    private LinkedListStack<URL> forwardStack;
    URL currentWebpage_;

    //First Constructor
    public WebBrowser() {
        history = new SinglyLinkedList<>();
        forwardStack = new LinkedListStack<>();
    }

    //Second Constructor
    public WebBrowser(SinglyLinkedList<URL> history) {
        this.history = history;
        forwardStack = new LinkedListStack<>();
    }

    public void visit(URL webpage) {
        history.insertFirst(webpage);
        //Clear forward history.
        forwardStack.clear();
        currentWebpage_ = webpage;
    }

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

    public SinglyLinkedList<URL> history() {
        return history;
    }

    public LinkedListStack<URL> getForwardStack() {
        return forwardStack;
    }
}