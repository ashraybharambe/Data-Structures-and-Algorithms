import java.util.*;

public class MyHashMap {
    private int capacity = 16;
    private ArrayList<Entry> buckets;
    private int size;

    public MyHashMap(int capacity) {
        this.capacity = capacity;
        buckets = new ArrayList<Entry>(capacity);
        for(int i = 0; i < capacity; i++) {
            buckets.add(null);
        }
        this.size = 0;
    }

    public boolean search(int key) {
        int bucketIndex = getBucketIndex(key);
        Entry entry = buckets.get(bucketIndex);
        while(entry != null && entry.key != key) {
            entry = entry.next;
        }
        return (entry != null) ? true : false;
    }

    public int getSize () {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int get (int key) {
        int bucketIndex = getBucketIndex(key);
        Entry entry = buckets.get(bucketIndex);
        while(entry != null && entry.key != key) {
            entry = entry.next;
        }
        return (entry != null) ? entry.value : -1;
    }

    public void rehash() {
        ArrayList<Entry> oldBuckets = this.buckets;
        this.buckets = new ArrayList<>(2*capacity);
        for(int i = 0; i < 2*capacity; i++) {
            buckets.add(null);
        }
        this.size = 0;
        for(Entry oldEntry : oldBuckets) {
            if(oldEntry != null) {
                put(oldEntry.key, oldEntry.value);
                Entry itr = oldEntry.next;
                while(itr != null) {
                    put(itr.key, itr.value);
                    itr = itr.next;
                }
            }
        }
    }

    public void remove (int key) {
        int index = getBucketIndex(key);
        Entry itr = buckets.get(index);
        Entry prev = itr;
        while(itr != null && itr.key != key) {
            prev = itr;
            itr = itr.next;
        }
        if(prev != null) {
            if(itr != null) {
                prev.next = itr.next;
                this.size--;
            }
        }
    }

    public void put (int key, int value) {
        Entry newEntry = new Entry(key, value);
        int bucketIndex = getBucketIndex(key);
        Entry existingEntry = this.buckets.get(bucketIndex);
        if(existingEntry == null) {
            this.buckets.set(bucketIndex, newEntry);
            this.size++;
            return;
        }
        while(existingEntry.next != null && existingEntry.key != key) {
            existingEntry = existingEntry.next;
        }
        if(existingEntry.key == key) {
            existingEntry.value = value;
        } else {
            existingEntry.next = newEntry;
            this.size++;
        }
    }

    private int getBucketIndex(int key) {
        int hash = Objects.hash(key);
        // hash can be negative, hence modulo can result in negative integer
        return ((hash % this.capacity) + this.capacity) % this.capacity;
    }

    public class Entry {
        int key;
        int value;
        Entry next;

        public Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        MyHashMap myHashMap = new MyHashMap(5);
        System.out.println(myHashMap.getSize());
        myHashMap.put(1, 10);
        myHashMap.put(2, 20);
        myHashMap.put(1, 30);
        System.out.println(myHashMap.getSize());
        System.out.println(myHashMap.get(1));
        myHashMap.remove(1);
        System.out.println(myHashMap.search(1));
        System.out.println(myHashMap.getSize());
    }

}
