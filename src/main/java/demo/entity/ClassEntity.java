package demo.entity;

public class ClassEntity extends BaseEntity{

    private Integer class_number;

    public ClassEntity(Integer id, Integer class_number) {
        super(id);
        this.class_number = class_number;
    }

    public Integer getClass_number() {
        return class_number;
    }

    public void setClass_number(Integer class_number) {
        this.class_number = class_number;
    }
}
