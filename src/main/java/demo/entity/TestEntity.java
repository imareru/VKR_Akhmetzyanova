package demo.entity;

public class TestEntity extends BaseEntity{

    String test_name;
    String pass_time;
    Integer subject_id;
    Boolean if_access;
    String close_date;

    public TestEntity(Integer id, String test_name, String pass_time, Integer subject_id, Boolean if_access, String close_date) {
        super(id);
        this.test_name = test_name;
        this.pass_time = pass_time;
        this.subject_id = subject_id;
        this.if_access = if_access;
        this.close_date = close_date;
    }

    public String getTest_name() {
        return test_name;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name;
    }

    public String getPass_time() {
        return pass_time;
    }

    public void setPass_time(String pass_time) {
        this.pass_time = pass_time;
    }

    public Boolean getIf_access() {
        return if_access;
    }

    public void setIf_access(Boolean if_access) {
        this.if_access = if_access;
    }

    public String getClose_date() {
        return close_date;
    }

    public void setClose_date(String close_date) {
        this.close_date = close_date;
    }

    public Integer getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Integer subject_id) {
        this.subject_id = subject_id;
    }
}
