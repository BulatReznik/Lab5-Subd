package logic;

import model.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CustomerLogic {
    private Session session;

    public CustomerLogic(Session session){this.session=session;}
    public Customer getCustomer(int id){return  session.get(Customer.class, id);}

    public List<Customer> readCustomers(){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
        Root<Customer> root = query.from(Customer.class);

        query.select(root);

        var list = session.createQuery(query).getResultList();
        return list;
    }
    public void createCustomer(String name, String contactNumber, String cardNumber){
        Transaction transaction = session.beginTransaction();

        Customer customer = new Customer();
        customer.setName(name);
        customer.setContactNumber(contactNumber);
        customer.setCardNumber(cardNumber);
        session.save(customer);

        transaction.commit();
    }

    public void deleteCustomer(int id){
        Transaction transaction = session.beginTransaction();
        ReceiptLogic receiptLogic = new ReceiptLogic(session);
        //var receipts = receiptLogic.readReceipts();
        //receipts.stream().filter(e->e.getPosition().equals(getPosition(id))).forEach(Receipt::nullificationPosition);
        //session.delete(getPosition(id));
        transaction.commit();
    }

    public void updateCustomer(int id, String name, String contactNumber, String cardNumber)
    {
        Transaction transaction = session.beginTransaction();

        Customer customer = getCustomer(id);
        customer.setName(name);
        customer.setContactNumber(contactNumber);
        customer.setCardNumber(cardNumber);

        transaction.commit();
    }

}
