package entity;

public class AnswerEntity extends BaseEntity{

    String answer_text;
    Boolean bool_val;
    Integer question_id;

    public AnswerEntity(Integer id, String answer_text, Boolean bool_val, Integer question_id) {
        super(id);
        this.answer_text = answer_text;
        this.bool_val = bool_val;
        this.question_id = question_id;
    }

    public String getAnswer_text() {
        return answer_text;
    }

    public void setAnswer_text(String answer_text) {
        this.answer_text = answer_text;
    }

    public Boolean getBool_val() {
        return bool_val;
    }

    public void setBool_val(Boolean bool_val) {
        this.bool_val = bool_val;
    }

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    }
}
