package com.gizem.db;

import com.gizem.api.TodoItem;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Set;

public class TodoItemDao extends AbstractDAO<TodoItem>{

    public TodoItemDao(SessionFactory factory){
        super(factory);
    }

    public List<TodoItem> findAll() {
        CriteriaBuilder builder = currentSession().getCriteriaBuilder();
        CriteriaQuery<TodoItem> criteria = builder.createQuery(TodoItem.class);
        Root<TodoItem> todoRoot = criteria.from(TodoItem.class);
        criteria.select(todoRoot);
        return list(currentSession().createQuery(criteria));
    }

    @Override
    public TodoItem persist(TodoItem entity) throws HibernateException {
        return super.persist(entity);
    }
    //Delete with Named query
    public Integer deleteById(int id){
        Query query = currentSession().getNamedQuery("com.gizem.api.items.deleteById").setParameter("id",id);
        return query.executeUpdate();
    }
    //Delete process with CriteriaDelete<>
    /*public Integer deleteById(int id){
        CriteriaBuilder builder = currentSession().getCriteriaBuilder();
        CriteriaDelete<TodoItem> delete = builder.createCriteriaDelete(TodoItem.class);
        Root<TodoItem> todoRoot = delete.from(TodoItem.class);
        delete.where(builder.equal(todoRoot.get("id"),id));
        return currentSession().createQuery(delete).executeUpdate();
    }*/

    public Integer update(int id,String newName){
        CriteriaBuilder builder = currentSession().getCriteriaBuilder();
        CriteriaUpdate<TodoItem> update = builder.createCriteriaUpdate(TodoItem.class);
        Root<TodoItem> todoRoot = update.from(TodoItem.class);
        update.where(builder.equal(todoRoot.get("id"),id));
        update.set("name",newName);
        return currentSession().createQuery(update).executeUpdate();
    }

    public Integer updateItem(int id,String newName){
        String hql ="update TodoItem t set t.name= :name WHERE t.id= :id";
        Query query = currentSession().createQuery(hql);
        query.setParameter("id", id);
        query.setParameter("name",newName);
        return query.executeUpdate();
    }
    public Integer updateProcess(int id,String newName){
        String sql = "update TodoItem set name= :name WHERE " +
                "id= :id";
        NativeQuery query = currentSession().createNativeQuery(sql);
        query.setParameter("id",id);
        query.setParameter("name",newName);

        return query.executeUpdate();
    }
}
