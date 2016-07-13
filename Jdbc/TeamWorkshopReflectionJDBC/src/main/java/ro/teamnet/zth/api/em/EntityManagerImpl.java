package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.database.DBManager;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexandru.Dinca on 7/8/2016.
 */
public class EntityManagerImpl implements EntityManager {
    Connection conn = null;


    @Override
    public <T> T findById(Class<T> entityClass, Long id) {
        /*
* -	create a connection to DB;
-	get table name, columns and fields by annotations using the methods from EntityUtils class;
-	create a Condition object in which you have to set column name and the value of the id;
-	create a QueryBuilder object  in which you have to set the name of the table, columns, query type and conditions;
-	call createQuery() method from QueryBuilder.java;
-	create a resultSet object using Statement and execute the query obtained above;
-	if the resultSet has any value (resultSet.next()) then:
o	 you have to create an instance of type T;
o	iterate through ColumnInfo list and obtain the field of the instance using getColumnName().
Ex: instance.getClass().getDeclaredField(column.getColumnName());
o	make the field accessible;
o	set the value of the field with the value obtained from resultSet object;
-	return the instance;
* */
        conn = DBManager.getConnection();
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {

            }
        }
//        -	get table name, columns and fields by annotations using the methods from EntityUtils class;
        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> infoColoane = EntityUtils.getColumns(entityClass);
//        Field fld=EntityUtils.getFieldsByAnnotations(entityClass,);


        return null;
    }

    @Override
    public Long getNextIdVal(String tableName, String columnIdName) {

        Connection connection = DBManager.getConnection();
        String max_id = null;

        Statement statement = null;
        String query = "SELECT MAX(" + columnIdName + ") AS MAX_ID FROM " + tableName;

        try {
            statement = connection.createStatement();
            ResultSet RS = statement.executeQuery(query);

            while (RS.next()) {

                max_id = RS.getString("MAX_ID");
            }

            long result = Long.valueOf(max_id).longValue();
            return result + 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> Object insert(T entity) {

        Connection connection = DBManager.getConnection();
        String tableName = EntityUtils.getTableName(entity.getClass());
        LinkedList<ColumnInfo> columnInfoArrayList = (LinkedList<ColumnInfo>) EntityUtils.getColumns(entity.getClass());
        Long ID = new Long(0);

        for (ColumnInfo columnInfo : columnInfoArrayList) {
            if (columnInfo.isId()) {
                columnInfo.setValue(getNextIdVal(tableName, columnInfo.getDbName()));
                ID = getNextIdVal(tableName, columnInfo.getDbName());
            } else {
                try {
                    Field field = entity.getClass().getDeclaredField(columnInfo.getColumnName());
                    field.setAccessible(true);
                    columnInfo.setValue(field.get(entity));
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder = queryBuilder.setTableName(entity).addQueryColumns(columnInfoArrayList).setQueryType(QueryType.INSERT);
        String query = queryBuilder.createQuery();
        System.out.println(query);

        try (Statement statement = connection.createStatement()) {

            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass) {
        conn = DBManager.getConnection();
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {

            }
        }
        return null;
    }

    /*
    * a)	Method <T> T update(T entity)
-	create a connection to DB;
-	get table name and columns using the methods from EntityUtils class;
-	iterate through ColumnInfo list
o	getDeclaredField by column name;
o	make the field accessible;
o	set the value of the columnInfo with the value obtained from the field;
-	create a Condition object where you need to set id value which will be updated;
-	create a QueryBuilder object  where you set the name of table, query type, columns and conditions;
-	call createQuery() method from QueryBuilder.java;
-	create a Statement object and execute the query;
-	return the updated object;
    * */
    @Override
    public <T> T update(T entity) {
        try {
            conn = DBManager.getConnection();
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
            String tableName = EntityUtils.getTableName(entity.getClass());
            LinkedList<ColumnInfo> columnInfoArrayList = (LinkedList<ColumnInfo>) EntityUtils.getColumns(entity.getClass());
            Long ID = new Long(0);
            for (ColumnInfo columnInfo : columnInfoArrayList) {
                Field valueField = entity.getClass().getDeclaredField(columnInfo.getColumnName());
                valueField.setAccessible(true);
                if (columnInfo.isId())
                    ID=Long.valueOf(columnInfo.getValue().toString());
//            set the value of the columnInfo with the value obtained from the field;
                columnInfo.setValue(valueField.get(entity));
//            -	create a Condition object where you need to set id value which will be updated;
                Condition condition = new Condition();
                condition.setValue(columnInfo.getValue());
                condition.setColumnName(columnInfo.getColumnName());
//            create a QueryBuilder object  where you set the name of table, query type, columns and conditions;
                QueryBuilder queryBuilder = new QueryBuilder();
                queryBuilder.setTableName(tableName);
                queryBuilder.setQueryType(QueryType.UPDATE);
                queryBuilder.addQueryColumns(columnInfoArrayList);
                queryBuilder.addCondition(condition);
                queryBuilder.createQuery();
//            create a Statement object
                Statement stmt = conn.createStatement();
//            execute the query;
                ResultSet rs = stmt.executeQuery(queryBuilder.createQuery());
            }
            return findById(entity,ID);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Object entity) {


    }

    @Override
    public <T> List<T> findByParams(Class<T> entityClass, Map<String, Object> params) {
        return null;
    }
}
