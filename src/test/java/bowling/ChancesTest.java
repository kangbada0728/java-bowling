package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ChancesTest {
    @Test
    @DisplayName("10개 이상의 핀을 넘어뜨림")
    void test1() {
        assertThatThrownBy(() -> {
            new Chances().add(11);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("모든 핀이 넘어졌음")
    void test2() {
        Chances chances = new Chances(10, 0);
        assertThat(chances.areAllPinsDown()).isTrue();
    }

    @Test
    @DisplayName("남은 찬스가 없음")
    void test3() {
        Chances chances = new Chances(10, 0);
        assertThat(chances.noLeftChances()).isTrue();
    }

    @Test
    @DisplayName("모든 핀이 넘어졌음")
    void test4() {
        Chances chances = new Chances(5, 0);
        assertThat(chances.areAllPinsDown()).isFalse();
    }

    @Test
    @DisplayName("남은 찬스가 있음")
    void test5() {
        Chances chances = new Chances(10);
        assertThat(chances.noLeftChances()).isFalse();
    }

}
