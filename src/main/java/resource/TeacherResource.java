package resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import entity.TeacherEntity;

public class TeacherResource extends BaseResource{

    private Integer teacher_id;
    private String t_surname;
    private String t_name;
    private String t_patronymic;
    private String t_login;
    private String t_password;
    private String t_birth_date;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SubjectResource[] subjectResources;

    public TeacherResource(){};

    public TeacherResource(TeacherEntity teacherEntity) {
        this.teacher_id = teacherEntity.getId();
        this.t_surname = teacherEntity.getT_surname();
        this.t_name = teacherEntity.getT_name();
        this.t_patronymic = teacherEntity.getT_patronymic();
        this.t_login = teacherEntity.getT_login();
        this.t_password = teacherEntity.getT_password();
        this.t_birth_date = teacherEntity.getT_birth_date();
    }

    public Integer getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Integer teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getT_surname() {
        return t_surname;
    }

    public void setT_surname(String t_surname) {
        this.t_surname = t_surname;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public String getT_patronymic() {
        return t_patronymic;
    }

    public void setT_patronymic(String t_patronymic) {
        this.t_patronymic = t_patronymic;
    }

    public String getT_login() {
        return t_login;
    }

    public void setT_login(String t_login) {
        this.t_login = t_login;
    }

    public String getT_password() {
        return t_password;
    }

    public void setT_password(String t_password) {
        this.t_password = t_password;
    }

    public String getT_birth_date() {
        return t_birth_date;
    }

    public void setT_birth_date(String t_birth_date) {
        this.t_birth_date = t_birth_date;
    }

    public SubjectResource[] getSubjectResources() {
        return subjectResources;
    }

    public void setSubjectResources(SubjectResource[] subjectResources) {
        this.subjectResources = subjectResources;
    }

    public TeacherEntity toEntity(){
        return new TeacherEntity(
                this.teacher_id,
                this.t_surname,
                this.t_name,
                this.t_patronymic,
                this.t_login,
                this.t_password,
                this.t_birth_date
        );
    }
}
