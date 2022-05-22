package model;

import javax.persistence.*;

@Entity
@Table(name = "customer", indexes = {@Index(name = "IDX_MYIDX_CUSTOMER", columnList = "id, name, card_number, contact_number")})
public class Customer {
    @Id
    @SequenceGenerator(name = "identifier", sequenceName = "seq_customer", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "identifier")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "card_number")
    private String card_number;

    @Column(name = "contact_number")
    private String contact_Number;

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


    public String getCardNumber() {
        return card_number;
    }

    public void setCardNumber(String cardNumber) {
        this.card_number = cardNumber;
    }


    public String getContactNumber() {
        return contact_Number;
    }

    public void setContactNumber(String contact_Number) {
        this.contact_Number = contact_Number;
    }

    @Override
    public String toString() {
        return "ФИО: "+name+"; Номер карточки: "+card_number+"; Телефонный номер: "+contact_Number+"; || id="+id;
    }
}
