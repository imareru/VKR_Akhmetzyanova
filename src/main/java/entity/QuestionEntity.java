package entity;

public class QuestionEntity extends BaseEntity{

    String question_text;
    Integer test_id;
    Byte image;
    Integer answer_id;

    public QuestionEntity(Integer id, String question_text, Integer test_id, Byte image, Integer answer_id) {
        super(id);
        this.question_text = question_text;
        this.test_id = test_id;
        this.image = image;
        this.answer_id = answer_id;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

    public Byte getImage() {
        return image;
    }

    public void setImage(Byte image) {
        this.image = image;
    }

    public Integer getTest_id() {
        return test_id;
    }

    public void setTest_id(Integer test_id) {
        this.test_id = test_id;
    }

    public Integer getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(Integer answer_id) {
        this.answer_id = answer_id;
    }
}
