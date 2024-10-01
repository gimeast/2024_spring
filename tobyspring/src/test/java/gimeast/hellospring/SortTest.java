package gimeast.hellospring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class SortTest {

    Sort sort;

    @BeforeEach
    void beforeEach() {
        this.sort = new Sort();
        System.out.println("테스트 케이스 마다 SortTest클래스의 인스턴스가 새로 생성된다. this = " + this);
    }

    @Test
    void sort() {
        List<String> list = sort.sortByLength(Arrays.asList("aa", "b"));

        assertThat(list).isEqualTo(List.of("b", "aa"));
    }

    @Test
    void sort3Items() {
        List<String> list = sort.sortByLength(Arrays.asList("aa", "ccc", "b"));

        assertThat(list).isEqualTo(List.of("b", "aa", "ccc"));
    }

    @Test
    void sortAlreadySorted() {
        List<String> list = sort.sortByLength(Arrays.asList("b", "aa", "ccc"));

        assertThat(list).isEqualTo(List.of("b", "aa", "ccc"));
    }

}