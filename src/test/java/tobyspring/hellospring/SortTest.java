package tobyspring.hellospring;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SortTest {

    Sort sort;

    @BeforeEach
    void beforeEach() {
        sort = new Sort();
        System.out.println(this);
    }

    @Test
    void sort() {
        List<String> unSorted = Arrays.asList("aa", "b");

        List<String> list = sort.sortByLength(unSorted);

        assertThat(list).isEqualTo(List.of("b", "aa"));
    }

    @Test
    void sort3Items() {
        List<String> unSorted = Arrays.asList("aa", "ccc" ,"b");

        List<String> list = sort.sortByLength(unSorted);

        assertThat(list).isEqualTo(List.of("b", "aa", "ccc"));
    }

    @Test
    void sortAlreadySorted() {
        List<String> unSorted = Arrays.asList("b", "aa" ,"ccc");

        List<String> list = sort.sortByLength(unSorted);

        assertThat(list).isEqualTo(List.of("b", "aa", "ccc"));
    }
}
