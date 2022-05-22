package model;

import  javax.persistence.*;

@Entity
@Table(name = "device", indexes = {@Index( name = "IDX_MYIDX_DEVICE", columnList = "id, name, sale_type")})
public class Device {
    @Id
    @SequenceGenerator(name = "identifier", sequenceName = "seq_device", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "identifier")
    private int id;

    @ManyToOne()
    private Type type; //

    @Column(name = "name")
    private String deviceName;

    @Column(name = "sale_type")
    private String saleType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getSaleType() {
        return saleType;
    }

    public void setSaleType(String saleType) {this.saleType = saleType;}

    public Type getType(){return type;}

    public void setType(Type type){this.type = type;}

    @Override
    public String toString() {
        return "Название: "+deviceName+"; Тип продажи: "+saleType+"; Категория: "+type.getName()+"; || id="+id;
    }
}
