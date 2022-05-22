package model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "price", indexes = {@Index(name = "IDX_MYIDX_PRICE", columnList = "id, date_change, unit_price") })

public class Price {
    @Id
    @SequenceGenerator(name="identifier", sequenceName="seq_price", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="identifier")
    private int id;

    @ManyToOne()
    private Device device;

    @Column(name = "date_change")
    private Date dateChange;

    @Column(name = "unit_price")
    private int unitPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUnitPrice() {return unitPrice; }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Date getDateChange(){return  dateChange;}

    public void setDateChange(Date dateChange){this.dateChange = dateChange;}

    @Override
    public String toString() {
        return "Девайс: "+ device.getDeviceName()+"; Дата изм.: "+dateChange+"; Цена за ед.: "+unitPrice+" || id="+id;
    }
}
