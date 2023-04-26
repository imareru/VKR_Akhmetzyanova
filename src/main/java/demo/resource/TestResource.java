package demo.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import demo.entity.TestEntity;

public class TestResource extends BaseResource{

    private Integer test_id;
    private String test_name;
    private String pass_time;
    private Integer subject_id;
    private Boolean if_access;
    private String close_date;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SubjectResource subjectResource;

    public TestResource(){}

    public TestResource(TestEntity testEntity) {
        this.test_id = testEntity.getId();
        this.test_name = testEntity.getTest_name();
        this.pass_time = testEntity.getPass_time();
        this.subject_id = testEntity.getSubject_id();
        this.if_access = testEntity.getIf_access();
        this.close_date = testEntity.getClose_date();
    }

    public Integer getTest_id() {
        return test_id;
    }

    public void setTest_id(Integer test_id) {
        this.test_id = test_id;
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

    public Integer getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Integer subject_id) {
        this.subject_id = subject_id;
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

    public SubjectResource getSubjectResource() {
        return subjectResource;
    }

    public void setSubjectResource(SubjectResource subjectResource) {
        this.subjectResource = subjectResource;
    }

    public TestEntity toEntity(){
        return new TestEntity(
                this.test_id,
                this.test_name,
                this.pass_time,
                this.subject_id,
                this.if_access,
                this.close_date
        );
    }
}
