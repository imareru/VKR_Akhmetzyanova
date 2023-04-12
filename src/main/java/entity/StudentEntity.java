package entity;

public class StudentEntity extends BaseEntity {

    String s_surname;
    String s_name;
    String s_patronymic;
    String s_login;
    String s_password;
    String s_birth_date;
    Integer class_id;

    public StudentEntity(Integer id, String s_surname, String s_name, String s_patronymic, String s_login, String s_password, String s_birth_date, Integer class_id) {
        super(id);
        this.s_surname = s_surname;
        this.s_name = s_name;
        this.s_patronymic = s_patronymic;
        this.s_login = s_login;
        this.s_password = s_password;
        this.s_birth_date = s_birth_date;
        this.class_id = class_id;
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

}
