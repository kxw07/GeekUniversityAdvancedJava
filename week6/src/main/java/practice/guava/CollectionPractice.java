package practice.guava;

import com.google.common.collect.*;

import java.util.List;

public class CollectionPractice {
    public static void main(String[] args) {
        ImmutableList<Integer> immutableList = ImmutableList.of(1,2,3,4,5);
        try {
            immutableList.add(6);
        } catch (Throwable t) {
            System.out.println(t);
        }

        ImmutableMap<String, String> immutableMap = ImmutableMap.of("key1", "value1", "key2", "value2");
        try {
            immutableMap.put("key3", "value3");
        } catch (Throwable t) {
            System.out.println(t);
        }

        Multiset<String> multiset = HashMultiset.create();
        multiset.add("a");
        multiset.add("a");
        multiset.add("b", 3);
        System.out.println(multiset.count("b"));

        Multimap<String, String> multimap = HashMultimap.create();
        multimap.put("a", "a1");
        multimap.put("a", "a2");
        System.out.println(multimap.get("a"));

//        Table<String, String, Integer> table = HashBasedTable.create();
        List<String> tableRow = Lists.newArrayList("Math", "English");
        List<String> tableColumn = Lists.newArrayList("Jack", "Tom", "John");
        Table<String, String, Integer> table = ArrayTable.create(tableRow, tableColumn);
        table.put("Math", "Jack", 90);
        table.put("English", "Tom", 87);
        System.out.println(table);

        BiMap<String, String> biMap = HashBiMap.create();
        biMap.put("key1", "value1");
        biMap.put("key2", "value2");
        biMap.forcePut("key3", "value2");
        System.out.println(biMap.inverse().get("value1"));
        System.out.println(biMap.inverse().get("value2"));
    }
}
