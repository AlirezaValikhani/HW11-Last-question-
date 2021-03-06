package model;

public class Course {
    private Integer id;
    private String name;
    private Integer unit;

    public Course(Integer id, String name, Integer unit) {
        this.id = id;
        this.name = name;
        this.unit = unit;
    }

    public Course(Integer id) {
        this.id = id;
    }

    public Course(String name, Integer unit) {
        this.name = name;
        this.unit = unit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "-------------------\nID : " + id + "\nCourse name : " + name + "\nunit : " + unit + "\n-------------------\n";
    }
}
