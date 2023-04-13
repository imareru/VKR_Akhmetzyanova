package resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import entity.ClassEntity;

public class ClassResource extends BaseResource{
    private Integer id;
    private Integer class_number;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StudentResource[] order;

    public ClassResource(){}

    public ClassResource(ClassEntity classEntity){
        this.id = classEntity.getId();
        this.class_number = classEntity.getClass_number();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClass_number() {
        return class_number;
    }

    public void setClass_number(Integer class_number) {
        this.class_number = class_number;
    }

    public StudentResource[] getOrder() {
        return order;
    }

    public void setOrder(StudentResource[] order) {
        this.order = order;
    }

    public ClassEntity toEntity(){
        return new ClassEntity(
                this.id,
                this.class_number
        );
    }
}
