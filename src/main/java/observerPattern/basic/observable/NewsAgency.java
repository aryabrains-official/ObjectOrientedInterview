package observerPattern.basic.observable;

import observerPattern.basic.observer.IChannel;

import java.util.ArrayList;
import java.util.List;

public class NewsAgency {
    private String news;
    private List<IChannel> observersList = new ArrayList<IChannel>();

    public void addObservers(IChannel newChannel) {
        this.observersList.add(newChannel);
    }

    public void removeObserver(IChannel existingChannel) {
        this.observersList.remove(existingChannel);
    }

    public void setNews(String news) {
        this.news = news;
        observersList.forEach(observer -> {
            observer.update(news);
        });
    }
}
