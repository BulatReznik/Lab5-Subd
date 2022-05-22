package logic;

import model.Receipt;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class ReceiptLogic {
    private Session session;

    public ReceiptLogic(Session session) {this.session=session;}

    public Receipt getReceipt(int id){return session.get(Receipt.class, id);}

    public List<Receipt> readReceipt(){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Receipt> query =builder.createQuery(Receipt.class);
        Root<Receipt> root = query.from(Receipt.class);

        query.select(root);

        var list = session.createQuery(query).getResultList();
        return list;
    }

    public void createReceipt(Date receiptDate, Time receiptTime, String cashier){
        Transaction transaction = session.beginTransaction();

        Receipt receipt = new Receipt();
        receipt.setReceiptDate(receiptDate);
        receipt.setReceiptTime(receiptTime);
        receipt.setCashier(cashier);
        session.save(receipt);

        transaction.commit();
    }

    public void deleteReceipt(int id){
        Transaction transaction = session.beginTransaction();
        session.delete(getReceipt(id));
        transaction.commit();
    }

    public void updateReceipt(int id, Date receiptDate, Time receiptTime, String cashier) {
        Transaction transaction = session.beginTransaction();

        Receipt receipt =getReceipt(id);
        receipt.setReceiptDate(receiptDate);
        receipt.setReceiptTime(receiptTime);
        receipt.setCashier(cashier);
        session.update(receipt);

        transaction.commit();
    }
}
