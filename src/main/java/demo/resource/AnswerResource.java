package demo.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import demo.entity.AnswerEntity;

public class AnswerResource extends BaseResource{

    private Integer answer_id;
    private String answer_text;
    private Boolean bool_val;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private QuestionResource[] questionResource;

    public AnswerResource(){}

    public AnswerResource(AnswerEntity answerEntity) {
        this.answer_id = answerEntity.getId();
        this.answer_text = answerEntity.getAnswer_text();
        this.bool_val = answerEntity.getBool_val();
    }

    public Integer getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(Integer answer_id) {
        this.answer_id = answer_id;
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

    public QuestionResource[] getQuestionResource() {
        return questionResource;
    }

    public void setQuestionResource(QuestionResource[] questionResource) {
        this.questionResource = questionResource;
    }


    public AnswerEntity toEntity(){
        return new AnswerEntity(
                this.answer_id,
                this.answer_text,
                this.bool_val
        );
    }
}
