package resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import entity.ResultEntity;

public class ResultResource extends BaseResource{

    private Integer result_id;
    private Integer student_id;
    private Integer test_id;
    private String pass_date;
    private String passed_time;
    private Integer score;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StudentResource studentResource;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TestResource testResource;

    public ResultResource(){}

    public ResultResource(ResultEntity resultEntity) {
        this.result_id = resultEntity.getId();
        this.student_id = resultEntity.getStudent_id();
        this.test_id = resultEntity.getTest_id();
        this.pass_date = resultEntity.getPass_date();
        this.passed_time = resultEntity.getPassed_time();
        this.score = resultEntity.getScore();
    }

    public Integer getResult_id() {
        return result_id;
    }

    public void setResult_id(Integer result_id) {
        this.result_id = result_id;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public Integer getTest_id() {
        return test_id;
    }

    public void setTest_id(Integer test_id) {
        this.test_id = test_id;
    }

    public String getPass_date() {
        return pass_date;
    }

    public void setPass_date(String pass_date) {
        this.pass_date = pass_date;
    }

    public String getPassed_time() {
        return passed_time;
    }

    public void setPassed_time(String passed_time) {
        this.passed_time = passed_time;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public StudentResource getStudentResource() {
        return studentResource;
    }

    public void setStudentResource(StudentResource studentResource) {
        this.studentResource = studentResource;
    }

    public TestResource getTestResource() {
        return testResource;
    }

    public void setTestResource(TestResource testResource) {
        this.testResource = testResource;
    }

    public ResultEntity toEntity(){
        return new ResultEntity(
                this.result_id,
                this.student_id,
                this.test_id,
                this.pass_date,
                this.passed_time,
                this.score
        );
    }
}
