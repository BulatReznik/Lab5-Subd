package logic;


import model.Device;
import model.Price;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.util.List;

public class PriceLogic {
    private Session session;

    public PriceLogic(Session session){this.session=session;}

    public Price getPrice(int id){return  session.get(Price.class, id);}

    public List<Price> readPrice(){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Price> query = builder.createQuery(Price.class);
        Root<Price> root = query.from(Price.class);

        query.select(root);

        var list = session.createQuery(query).getResultList();
        return list;
    }

    public void createPrice(int unitPrice, Date date){
        Transaction transaction =session.beginTransaction();

        Price price = new Price();
        price.setUnitPrice(unitPrice);
        price.setDateChange(date);
        session.save(price);

        transaction.commit();
    }

    public void deletePrice(int id){
        Transaction transaction = session.beginTransaction();
        session.delete(getPrice(id));
        transaction.commit();
    }

    public void updatePrice(int id, int unitPrice, Date date){
        Transaction transaction = session.beginTransaction();

        Price price = getPrice(id);
        price.setUnitPrice(unitPrice);
        price.setDateChange(date);

        session.update(price);

        transaction.commit();
    }

    public void addDevice(int priceId, int deviceId){
        Transaction transaction = session.beginTransaction();

        Price price = getPrice(priceId);
        if(price.getDevice()!=null){
            Device deviceDel = session.get(Device.class, price.getDevice().getId());
            session.update(deviceDel);
        }
        Device device = session.get(Device.class, deviceId);
        price.setDevice(device);
        session.update(price);
        session.update(device);

        transaction.commit();
    }
}
