package observerPattern.basic.observer;

import lombok.Data;

@Data
public class NewsChannel implements IChannel{
    private String news;
    public void update(Object news) {
        this.setNews((String) news);
    }
}
