package model;

import javax.persistence.*;

@Entity
@Table(name = "device_receipt",indexes = { @Index(name = "IDX_MYIDX_DEVICE_RECEIPT", columnList = "id, number") })
public class Device_Receipt {
    @Id
    @SequenceGenerator(name="identifier", sequenceName="seq_device_receipt", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="identifier")
    private int id;

    @ManyToOne()
    private Device device;

    @ManyToOne()
    private Receipt receipt;

    @Column(name = "number")
    private int number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


}
