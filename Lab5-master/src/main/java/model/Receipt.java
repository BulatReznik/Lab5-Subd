package model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "receipt", indexes = {@Index(name = "IDX_MYIDX_RECEIPT", columnList = "id, date, time, cashier")})
public class Receipt {
    @Id
    @SequenceGenerator(name = "identifier", sequenceName = "seq_receipt", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "identifier")
    private int id;

    @ManyToOne()
    private Customer customer;

    @Column(name = "date")
    private Date receiptDate;

    @Column(name = "time")
    private Time receiptTime;

    @Column(name = "cashier")
    private String cashier;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Time getReceiptTime() {
        return receiptTime;
    }

    public void setReceiptTime(Time receiptTime) {
        this.receiptTime = receiptTime;
    }

    public String getCashier(){return cashier;}

    public void setCashier(String cashier){this.cashier = cashier;}

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    @Override
    public String toString() {
        return "Дата: "+ receiptDate+"; Время: "+ receiptTime + "; Касир: "+cashier+"; Покупатель: "+customer.getName()+" || id="+id;
    }
}
