package resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import entity.QuestionEntity;

public class QuestionResource extends BaseResource{

    private Integer question_id;
    private String  question_text;
    private Integer test_id;
    private Byte image;
    private Integer answer_id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AnswerResource answerResource;

    public QuestionResource(){}

    public QuestionResource(QuestionEntity questionEntity) {
        this.question_id = questionEntity.getId();
        this.question_text = questionEntity.getQuestion_text();
        this.test_id = questionEntity.getTest_id();
        this.image = questionEntity.getImage();
        this.answer_id = questionEntity.getAnswer_id();
    }

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

    public Integer getTest_id() {
        return test_id;
    }

    public void setTest_id(Integer test_id) {
        this.test_id = test_id;
    }

    public Byte getImage() {
        return image;
    }

    public void setImage(Byte image) {
        this.image = image;
    }

    public Integer getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(Integer answer_id) {
        this.answer_id = answer_id;
    }

    public AnswerResource getAnswerResource() {
        return answerResource;
    }

    public void setAnswerResource(AnswerResource answerResource) {
        this.answerResource = answerResource;
    }

    public QuestionEntity toEntity(){
        return new QuestionEntity(
                this.question_id,
                this.question_text,
                this.test_id,
                this.image,
                this.answer_id
        );
    }
}
