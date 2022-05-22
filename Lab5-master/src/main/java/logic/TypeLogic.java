package logic;

import org.hibernate.Session;
import org.hibernate.Transaction;
import model.Type;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public class TypeLogic {
    private  Session session;

    public  TypeLogic(Session session){this.session=session;}

    public Type getType(int id){return session.get(Type.class, id);}

    public List<Type> readType(){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Type> query = builder.createQuery(Type.class);
        Root<Type> root = query.from(Type.class);
        query.select(root);
        var list = session.createQuery(query).getResultList();
        return list;
    }

    public void createType(String name){
        Transaction transaction = session.beginTransaction();
        Type type = new Type();
        type.setName(name);
        session.save(type);
        transaction.commit();
    }

    public void deleteType(int id){
        Transaction transaction = session.beginTransaction();
        session.delete(getType(id));
        transaction.commit();
    }

    public  void updateType(int id, String name){
        Transaction transaction = session.beginTransaction();
        Type type = getType(id);
        type.setName(name);
        session.update(type);
        transaction.commit();
    }
}
