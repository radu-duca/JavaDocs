package ro.teamnet.zth.api.em;

import java.util.List;
import java.util.Map;

/**
 * Created by Alexandru.Dinca on 7/8/2016.
 */
public interface EntityManager {

    <T> T findById(Class<T> entityClass, Long id);

    public Long getNextIdVal(String tableName, String columnIdName);

    <T> Object insert(T entity);

    <T> List<T> findAll(Class<T> entityClass);

    <T> T update(T entity);

    void delete(Object entity);

    <T> List<T> findByParams(Class<T> entityClass, Map<String, Object> params);


}
