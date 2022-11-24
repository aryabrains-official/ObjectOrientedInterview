package observerPattern.usingPropertyChangeListner.oberver;

import lombok.Data;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

@Data
public class PclNewsChannel implements PropertyChangeListener {
    private String news;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.setNews((String) evt.getNewValue());
    }
}
