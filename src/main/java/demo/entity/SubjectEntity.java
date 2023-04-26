package demo.entity;

public class SubjectEntity extends BaseEntity{

    String sub_name;
    Integer teacher_id;

    public SubjectEntity(Integer id, String sub_name, Integer teacher_id) {
        super(id);
        this.sub_name = sub_name;
        this.teacher_id = teacher_id;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public Integer getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Integer teacher_id) {
        this.teacher_id = teacher_id;
    }
}
