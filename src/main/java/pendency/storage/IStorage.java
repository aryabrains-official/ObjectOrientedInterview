package pendency.storage;

import java.util.List;

public interface IStorage {
    int getCountForTags(List<String> tags);
    List<String> getTagsForId(String id);

    void setTagsForId(String id, List<String> tags);
    void incrementFrequencyForTags(List<String> tags);
    void decrementFrequencyForTags(List<String> tags);

    void removeId(String id);
}
