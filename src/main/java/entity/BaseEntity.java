package entity;

public class BaseEntity {

    private Integer id;

    BaseEntity(Integer id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
