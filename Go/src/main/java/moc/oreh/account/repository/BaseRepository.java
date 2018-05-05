package moc.oreh.account.repository;

import moc.oreh.account.entity.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;

@Repository
@Transactional
public class BaseRepository<T extends BaseEntity> {
    @Autowired
    private SessionFactory sessionFactory;

    private Class<T> entityType;

    protected BaseRepository() {
        if (BaseRepository.class != this.getClass()) {
            entityType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
    }

    public void save(T t) {
        currentSession().saveOrUpdate(t);
    }

    public T get(long id) {
        return currentSession().get(entityType, id);
    }

    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }
}
