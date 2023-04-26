package demo.entity;

public class AnswerEntity extends BaseEntity{

    String answer_text;
    Boolean bool_val;

    public AnswerEntity(Integer id, String answer_text, Boolean bool_val) {
        super(id);
        this.answer_text = answer_text;
        this.bool_val = bool_val;
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

}
