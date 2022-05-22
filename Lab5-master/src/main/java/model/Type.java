package model;

import javax.persistence.*;

@Entity
@Table(name = "type",indexes = { @Index(name = "IDX_MYIDX_TYPE", columnList = "id, name") })
public class Type {
    @Id
    @SequenceGenerator(name="identifier", sequenceName="seq_type", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="identifier")
    private int id;

    @Column(name = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Категория: "+name+"; || id="+id;
    }
}
