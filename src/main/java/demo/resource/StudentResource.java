package demo.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import demo.entity.StudentEntity;

public class StudentResource extends BaseResource{

    private Integer student_id;
    private String s_surname;
    private String s_name;
    private String s_patronymic;
    private String s_login;
    private String s_password;
    private String s_birth_date;
    private Integer class_id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ClassResource classResource;

    public StudentResource(){}

    public StudentResource(StudentEntity studentEntity) {
        this.student_id = studentEntity.getId();
        this.s_surname = studentEntity.getS_surname();
        this.s_name = studentEntity.getS_name();
        this.s_patronymic = studentEntity.getS_patronymic();
        this.s_login = studentEntity.getS_login();
        this.s_password = studentEntity.getS_password();
        this.s_birth_date = studentEntity.getS_birth_date();
        this.class_id = studentEntity.getClass_id();
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public String getS_surname() {
        return s_surname;
    }

    public void setS_surname(String s_surname) {
        this.s_surname = s_surname;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_patronymic() {
        return s_patronymic;
    }

    public void setS_patronymic(String s_patronymic) {
        this.s_patronymic = s_patronymic;
    }

    public String getS_login() {
        return s_login;
    }

    public void setS_login(String s_login) {
        this.s_login = s_login;
    }

    public String getS_password() {
        return s_password;
    }

    public void setS_password(String s_password) {
        this.s_password = s_password;
    }

    public String getS_birth_date() {
        return s_birth_date;
    }

    public void setS_birth_date(String s_birth_date) {
        this.s_birth_date = s_birth_date;
    }

    public Integer getClass_id() {
        return class_id;
    }

    public void setClass_id(Integer class_id) {
        this.class_id = class_id;
    }

    public ClassResource getClassResource() {
        return classResource;
    }

    public void setClassResource(ClassResource classResource) {
        this.classResource = classResource;
    }

    public StudentEntity toEntity(){
        return new StudentEntity(
                this.student_id,
                this.s_surname,
                this.s_name,
                this.s_patronymic,
                this.s_login,
                this.s_password,
                this.s_birth_date,
                this.class_id
        );
    }
}
