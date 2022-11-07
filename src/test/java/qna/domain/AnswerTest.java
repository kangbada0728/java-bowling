package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final Answer A3 = new Answer(1L, UserTest.BADA, QuestionTest.Q1, "Answers Content");

    @Test
    @DisplayName("답변 삭제 성공")
    void test1() {
        DeleteHistory deleteHistory = A3.delete(UserTest.BADA);
        assertThat(deleteHistory).isEqualTo(new DeleteHistory(ContentType.ANSWER, 1L, UserTest.BADA));
    }

    @Test
    @DisplayName("답변 삭제 실패 : 답변자가 삭제자와 다를 경우")
    void test2() {
        assertThatThrownBy(() -> {
            A3.delete(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }


}
