package logic;

import model.Device;
import model.Type;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class DeviceLogic {
    private Session session;

    public DeviceLogic(Session session){this.session=session;}

    public Device getDevice(int id){return session.get(Device.class, id);}

    public List<Device> readDevices(){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Device> query = builder.createQuery(Device.class);
        Root<Device> root = query.from(Device.class);

        query.select(root);
        var list = session.createQuery(query).getResultList();
        return list;
    }

    public void createDevice(String deviceName, String saleType, int typeid){
        Transaction transaction = session.beginTransaction();

        Device device = new Device();
        device.setDeviceName(deviceName);
        device.setSaleType(saleType);
        Type newType = session.get(Type.class, typeid);
        device.setType(newType);
        session.save(device);

        transaction.commit();
    }
    public void deleteDevice(int id){
        Transaction transaction = session.beginTransaction();
        session.delete(getDevice(id));
        transaction.commit();
    }

    public void updateDevice(int id, String deviceName, String saleType, int typeid){
        Transaction transaction = session.beginTransaction();

        Device device = getDevice(id);
        device.setDeviceName(deviceName);
        device.setSaleType(saleType);
        Type newType = session.get(Type.class, typeid);
        device.setType(newType);
        transaction.commit();
    }

}
