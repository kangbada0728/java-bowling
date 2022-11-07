package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
    public static final Question Q3 = new Question(1L, "title3", "contents2").writeBy(UserTest.BADA);

    @Test
    @DisplayName("질문 삭제 성공")
    void test() {
        List<DeleteHistory> deleteHistory = Q3.delete(UserTest.BADA);
        assertThat(deleteHistory).containsExactly(new DeleteHistory(ContentType.QUESTION, 1L, UserTest.BADA));
    }

    @Test
    @DisplayName("질문 삭제 실패 : 질문자와 삭제자가 다를 경우")
    void test2() {
        assertThatThrownBy(() -> {
            Q1.delete(UserTest.BADA);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문과 답변 삭제 성공")
    void test3() {
        Q3.addAnswer(new Answer(UserTest.BADA, Q3, "content"));
        List<DeleteHistory> deleteHistory = Q3.delete(UserTest.BADA);
        assertThat(deleteHistory).containsExactly(new DeleteHistory(ContentType.QUESTION, 1L, UserTest.BADA), new DeleteHistory(ContentType.ANSWER, null, UserTest.BADA));
    }

    @Test
    @DisplayName("질문과 답변 삭제 실패 : 답변자와 삭제자가 다를 경우")
    void test4() {
        assertThatThrownBy(() -> {
            Q3.addAnswer(new Answer(UserTest.JAVAJIGI, Q3, "content"));
            Q3.delete(UserTest.BADA);
        }).isInstanceOf(CannotDeleteException.class);
    }

}
