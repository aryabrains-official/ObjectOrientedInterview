package observerPattern.basic.driver;

import observerPattern.basic.observable.NewsAgency;
import observerPattern.basic.observer.NewsChannel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainProgram {
    public static void main(String[] args) {
        NewsAgency newsAgency = new NewsAgency();
        NewsChannel newsChannel1 = new NewsChannel();
        NewsChannel newsChannel2 = new NewsChannel();
        newsAgency.addObservers(newsChannel1);
        newsAgency.addObservers(newsChannel2);

        newsAgency.setNews("Hello everyone");
        assertEquals(newsChannel1.getNews(), "Hello everyone");
        assertEquals(newsChannel2.getNews(), "Hello everyone");
    }
}
