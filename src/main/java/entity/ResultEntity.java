package entity;

public class ResultEntity extends BaseEntity {

    Integer student_id;
    Integer test_id;
    String passed_time;
    String pass_date;
    Integer score;

    public ResultEntity(Integer id, Integer student_id, Integer test_id, String passed_time, String pass_date, Integer score) {
        super(id);
        this.student_id = student_id;
        this.test_id = test_id;
        this.passed_time = passed_time;
        this.pass_date = pass_date;
        this.score = score;
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

    public String getPassed_time() {
        return passed_time;
    }

    public void setPassed_time(String passed_time) {
        this.passed_time = passed_time;
    }

    public String getPass_date() {
        return pass_date;
    }

    public void setPass_date(String pass_date) {
        this.pass_date = pass_date;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
