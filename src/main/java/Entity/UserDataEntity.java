package Entity;

import Annotation.Table;

import java.util.Date;

@Table(name="userdata")
public class UserDataEntity {
    private int id;
    private Date time;
    private int count;
    private Date lastTime;
    private String name;
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    @Override
    public String toString(){
        return "id="+this.getId()+" "+"count="+this.getCount()+" "+"name="+this.getName()+" "+"lastTime="+this.getLastTime();
    }
}
