package logic;

import model.Device;
import model.Device_Receipt;
import model.Receipt;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public class DeviceReceiptLogic {
    private Session session;

    public DeviceReceiptLogic(Session session){
        this.session=session;
    }

    public Device_Receipt getDeviceReceipt(int id){
        return session.get(Device_Receipt.class, id);
    }

    public List<Device_Receipt> readDeviceReceipt(){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Device_Receipt> query = builder.createQuery(Device_Receipt.class);
        Root<Device_Receipt> root = query.from(Device_Receipt.class);

        query.select(root);
        var list = session.createQuery(query).getResultList();
        return list;
    }

    public void createDeviceReceipt(int deviceId, int receiptId, int number){
        Transaction transaction = session.beginTransaction();

        Device_Receipt device_receipt=new Device_Receipt();
        device_receipt.setDevice(session.get(Device.class, deviceId));
        device_receipt.setReceipt(session.get(Receipt.class, receiptId));
        device_receipt.setNumber(number);

        session.save(device_receipt);
        transaction.commit();
    }
    public void deleteDeviceReceipt(int id){
        Transaction transaction = session.beginTransaction();
        session.delete(getDeviceReceipt(id));
        transaction.commit();
    }
    public void updateDeviceReceipt(int id, int deviceId, int receiptId, int number){
        Transaction transaction = session.beginTransaction();

        Device_Receipt device_receipt = getDeviceReceipt(id);
        device_receipt.setReceipt(session.get(Receipt.class, receiptId));
        device_receipt.setDevice(session.get(Device.class, deviceId));
        device_receipt.setNumber(number);

        session.update(device_receipt);
        transaction.commit();
    }

}
