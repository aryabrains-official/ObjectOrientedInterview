package pendency.storage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapStorage implements IStorage {
    private final String TAGS_ID_DELIMITER = "-";
    private Map<String, String> idToTagsStringMap = new HashMap<>();
    private Map<String, Integer> tagsStringToFrequencyMap = new HashMap<>();

    @Override
    public int getCountForTags(List<String> tags) {
        String normalizedTagString = getNormalizedStringForTags(tags);
        if(!tagsStringToFrequencyMap.containsKey(normalizedTagString)) {
            return 0;
        }
        return tagsStringToFrequencyMap.get(normalizedTagString);
    }

    @Override
    public List<String> getTagsForId(String id) throws IllegalArgumentException {
        if(!idToTagsStringMap.containsKey(id)) {
            throw new IllegalArgumentException("NotFound");
        }
        String tagsString = idToTagsStringMap.get(id);
        return getTagsFromNormalizedString(tagsString);
    }

    @Override
    public void setTagsForId(String id, List<String> tags) throws IllegalArgumentException {
        if(idToTagsStringMap.containsKey(id)) {
            throw new IllegalArgumentException("DuplicateId");
        }
        String normalizedTagString = getNormalizedStringForTags(tags);
        idToTagsStringMap.put(id, normalizedTagString);
    }

    @Override
    public void incrementFrequencyForTags(List<String> tags) {
        String normalizedTagString = getNormalizedStringForTags(tags);
        if(tagsStringToFrequencyMap.containsKey(normalizedTagString)) {
            tagsStringToFrequencyMap.put(normalizedTagString, tagsStringToFrequencyMap.get(normalizedTagString) + 1);
        } else {
            tagsStringToFrequencyMap.put(normalizedTagString, 1);
        }
    }

    @Override
    public void decrementFrequencyForTags(List<String> tags) {
        String normalizedTagString = getNormalizedStringForTags(tags);
        if(!tagsStringToFrequencyMap.containsKey(normalizedTagString)) {
            throw new IllegalArgumentException("NoTagFound");
        }
        int freq = tagsStringToFrequencyMap.get(normalizedTagString);
        if(freq == 1) {
            tagsStringToFrequencyMap.remove(normalizedTagString);
        } else {
            tagsStringToFrequencyMap.put(normalizedTagString, tagsStringToFrequencyMap.get(normalizedTagString) - 1);
        }
    }

    @Override
    public void removeId(String id) {
        if(!idToTagsStringMap.containsKey(id)) {
            throw new IllegalArgumentException("NotFound");
        }
        idToTagsStringMap.remove(id);
    }

    private String getNormalizedStringForTags(List<String> tags) {
        return String.join(TAGS_ID_DELIMITER, tags);
    }

    private List<String> getTagsFromNormalizedString(String normalizedTag) {
        String[] tagsArray = normalizedTag.split(TAGS_ID_DELIMITER);
        return Arrays.asList(tagsArray);
    }
}
