package pendency.service;

import lombok.NonNull;
import pendency.storage.HashMapStorage;
import pendency.storage.IStorage;

import java.util.ArrayList;
import java.util.List;

public class PendencySystem {
    private IStorage storage;

    // will be injected in real world scenario
    public PendencySystem() {
        storage = new HashMapStorage();
    }

    public void startTracking(@NonNull final String id, @NonNull final List<String> tags) {
        try {
            storage.setTagsForId(id, tags);
        } catch (IllegalArgumentException ex) {
            throw ex;
        }

        List<String> incrementalTagList = new ArrayList<>();
        tags.forEach(tag -> {
            incrementalTagList.add(tag);
            storage.incrementFrequencyForTags(incrementalTagList);
        });
    }

    public void stopTracking(String id) {
        List<String> tags;
        try {
            tags = storage.getTagsForId(id);
        } catch (IllegalArgumentException ex) {
            throw ex;
        }
        List<String> incrementalTagList = new ArrayList<>();
        tags.forEach(tag -> {
            incrementalTagList.add(tag);
            storage.decrementFrequencyForTags(incrementalTagList);
        });
        storage.removeId(id);
    }

    public int getCounts(List<String> tags) {
        try {
            return storage.getCountForTags(tags);
        } catch (IllegalArgumentException ex) {
            throw ex;
        }
    }
}
