package demo.entity;

public class TeacherEntity extends BaseEntity {
    String t_surname;
    String t_name;
    String t_patronymic;
    String t_login;
    String t_password;
    String t_birth_date;

    public TeacherEntity(Integer id, String t_surname, String t_name, String t_patronymic, String t_login, String t_password, String t_birth_date) {
        super(id);
        this.t_surname = t_surname;
        this.t_name = t_name;
        this.t_patronymic = t_patronymic;
        this.t_login = t_login;
        this.t_password = t_password;
        this.t_birth_date = t_birth_date;
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
}
