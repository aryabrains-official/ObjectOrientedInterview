package observerPattern.usingPropertyChangeListner.driver;

import observerPattern.usingPropertyChangeListner.obervable.AnotherNewsAgency;
import observerPattern.usingPropertyChangeListner.oberver.PclNewsChannel;
import org.junit.jupiter.api.Assertions;

public class MainProgram {

    public static void main(String[] args) {
        AnotherNewsAgency newsAgency = new AnotherNewsAgency();
        PclNewsChannel channel1 = new PclNewsChannel();
        PclNewsChannel channel2 = new PclNewsChannel();

        newsAgency.addPropertyChangeListener(channel1);
        newsAgency.addPropertyChangeListener(channel2);

        newsAgency.setNews("PCL Implemented");

        Assertions.assertEquals(channel1.getNews(), "PCL Implemented");
        Assertions.assertEquals(channel2.getNews(), "PCL Implemented");
    }
}
