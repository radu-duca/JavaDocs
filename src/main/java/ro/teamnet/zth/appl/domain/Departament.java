package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.annotations.Id;

/**
 * Created by user on 7/7/2016.
 */
public class Departament {

    @Id(name = "department_id")
    private Long Id;
    private String departmentName;
    private Location location;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Departament{" +
                "Id=" + Id +
                ", departmentName='" + departmentName + '\'' +
                ", location=" + location +
                '}';
    }
}
