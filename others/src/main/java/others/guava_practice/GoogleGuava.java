package others.guava_practice;

import com.google.common.base.Preconditions;
import com.google.common.collect.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class GoogleGuava {
    public static void main(String[] args) {
        // check not null
        String param = "test";
        String p1 = Preconditions.checkNotNull(param);
        String p2 = Preconditions.checkNotNull(param, "error message");

        // immutable collections
        final ImmutableSet<String> immutableSet = ImmutableSet.of("a", "b", "c");
        final ImmutableSet<Integer> integerImmutableSet = ImmutableSet.<Integer>builder().add(11).add(22).add(33).build();
        ArrayList<String> arrayList = new ArrayList();
        arrayList.add("aaa");
        arrayList.add("bbb");
        ImmutableSet<String> stringImmutableSet = ImmutableSet.copyOf(arrayList);

        arrayList.add("ccc");
        stringImmutableSet.forEach(System.out::println); // only has aaa, bbb

        // collection factory
        final ArrayList<String> stringArrayList = Lists.newArrayList();
        final ArrayList<Integer> integerArrayList = Lists.newArrayList(1, 2, 3);
        final ArrayList<Integer> initLengthArrayList = Lists.newArrayListWithCapacity(3);
        final CopyOnWriteArrayList<Object> objectCopyOnWriteArrayList = Lists.newCopyOnWriteArrayList();

        final ConcurrentMap<Object, Object> concurrentMap = Maps.newConcurrentMap();

        // check not null
        // immutable collection
        // collection factory
        // union, intersection, difference
        // count collection
        // one key multi value map
        // string concat, split
        // cache

        // FluentIterable
        // partition list
        // table
        // BiMap, key and value are unique
        // range
        // ordering
        // Monitor

        String sample = "Opera refers to a dramatic art form, originating in Europe in which the emotional content is conveyed to the audience as much through music, both vocal and instrumental, as it is through the lyrics. By contrast, in musical theater an actor’s dramatic performance is primary, and the music plays a lesser role. The drama in opera is presented using the primary elements of theater such as scenery, costumes, and acting. However, the words of the opera, or libretto are sung rather than spoken. The singers are accompanied by a musical ensemble ranging from a small instrumental ensemble to a full symphonic orchestra.";
        //Initializing Table with Start letter as row,length as col,word as value
        Table<Character, Integer, String> tableWithStartCharAndLength = Arrays.stream(sample.split(" "))
                .map(s -> ImmutableTable.of(s.charAt(0), s.length(), s))
                .flatMap(table -> table.cellSet().stream())
                .filter(cell -> {
//                    System.out.println(cell.getRowKey());
//                    System.out.println(cell.getColumnKey());
//                    System.out.println(cell.getValue());
                    return true;
                })
                .collect(ImmutableTable.toImmutableTable(
                        Table.Cell::getRowKey,
                        Table.Cell::getColumnKey,
                        Table.Cell::getValue,
                        (b1, b2) -> {
                            System.out.println(b1);
                            System.out.println(b2);
                            return b2;
                        }));

        assertThat(tableWithStartCharAndLength.row('d')).containsKeys(8, 5);
        assertThat(tableWithStartCharAndLength.row('d'))
                .containsValues("dramatic", "drama");
        assertThat(tableWithStartCharAndLength.get('O', 5)).isEqualTo("Opera");
    }
}
