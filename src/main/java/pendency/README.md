## Pendency System
Pendency is a system that is able to track counts of in-flight/in-progress entities.
The system is told when to start tracking a particular entity, and when to stop. And at any point the system can be asked how many entities are in-progress (or in-flight). The system is expected to give this count, as fast as possible

## Problem Statement
You need to design a system that is able to track pending entities.
An entity is identified using a unique ID and a list of hierarchical tags.
There are 3 parts to this problem statement. startTracking, endTracking and getCounts.

When an entity is being tracked, you will be provided with an identifier for the entity, and a hierarchical tags associated with the entity to be tracked.

When counts are being retrieved, you will be provided with a partial list of tags, for which the accumulated counts need to be returned. This partial list of tags will follow the same hierarchy order as given in the input. (Should become clear with the example shown below).

The following is a guide for the interfaces you may have in your system.

An interface to start tracking the counts for the entity. (increment count by 1)
void startTracking (Integer id, List<String> hierarchicalTags);
where,
id = Identifier of the entity
hierarchicalTags = Tags that are properties of the entity. These tags are hierarchical in nature.

An interface to stop tracking the entity that is already being tracked (reduce count by 1)
void stopTracking (Integer id);
where,
id = Identifier of the transaction

An interface to get counts of entity being tracked, that match the tags supplied
Integer getCounts (List<String> tags);
where,
tags = a hierarchy of tags, for which counts are to be retrieved

Sample
The following is just a sample for your understanding.
Please remember: You are expected to write the system which mirrors production quality code, rather than just implementing these functions

The sample below shows the ability to track in-flight transactions happening across instruments, states and cities:

## Examples for tracking and getting counts

startTracking(1112, ["UPI", "Karnataka", "Bangalore"]);
startTracking(2451, ["UPI", "Karnataka", "Mysore"]);
startTracking(3421, ["UPI", "Rajasthan", "Jaipur"]);
startTracking(1221, ["Wallet", "Karnataka", "Bangalore"]);

getCounts(["UPI"])   # represents the counts of all pending "UPI" transactions
Output: 3
getCounts(["UPI", "Karnataka"])  # represents the counts of all pending "UPI" transactions in "Karnataka"
Output: 2
getCounts(["UPI", "Karnataka", "Bangalore"]) # represents the counts of all pending "UPI" transactions in "Karnataka" and "Bangalore"
Output: 1

getCounts(["Bangalore"]) # Does not represent a valid hierarchy in the sample
Output: 0

startTracking(4221, ["Wallet", "Karnataka", "Bangalore"]);
stopTracking(1112);
stopTracking(2451);

getCounts(["UPI"])
Output: 1
getCounts(["Wallet"])
Output: 2
getCounts(["UPI", "Karnataka", "Bangalore"])
Output: 0


## Solution
In order to get this information, we need to design our storage layer so that we can provide the results with minimal cost/latency.
During real time design it's possible you do not achieve the best solution at first go so we will code to an interface. The storage interface will remain same, and you can swap the implementation to bring out the best latency.
### Storage layer
1. IStorage
```
public interface IStorage {
    int getCountForTags(List<String> tags);
    List<String> getTagsForId(String id);
    void incrementFrequencyForTags(List<String> tags);
    void decrementFrequencyForTags(List<String tags);
}
```
2. HashMapStorage implements IStorage


### PendencySystem
```
public class PendencySystem {
    void startTracking(String id, List<String> tags);
    void stopTracking(String id);
    int getCounts(List<String> tags);
}
```

### Driver
```
public class MainClass {
    public static void main(String[] args) {
        
    }
}
```