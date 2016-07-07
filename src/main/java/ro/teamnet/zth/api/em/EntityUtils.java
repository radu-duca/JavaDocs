package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 7/7/2016.
 */
public class EntityUtils {

    private EntityUtils() {
        throw new UnsupportedOperationException();
    }

    public static String getTableName(Class entity) {

        Table table = (Table) entity.getAnnotation(Table.class);
        if (table != null) {
            return table.name();
        } else {
            return entity.getSimpleName();
        }

    }

    public static List<ColumnInfo> getColumns(Class entity) {
        List<ColumnInfo> list = new ArrayList<>();

        for (Field f : entity.getDeclaredFields()) {
            ColumnInfo columnInfo = new ColumnInfo();
            columnInfo.setColumnName(f.getName());
            columnInfo.setColumnType(f.getType());

            Column column = f.getAnnotation(Column.class);
            Id id = f.getAnnotation(Id.class);

            if (column != null) {
                columnInfo.setDbName(column.name());
                columnInfo.setId(false);
            } else {
                columnInfo.setDbName(id.name());
                columnInfo.setId(true);
            }
            list.add(columnInfo);
        }
        return list;


    }

    public static Object castFromSqlType(Object value, Class wantedType) {

        if (value.getClass().equals(BigDecimal.class))
            if (wantedType.getClass().equals(Integer.class))
                return ((BigDecimal) value).intValue();

        if (value.getClass().equals(BigDecimal.class))
            if (wantedType.getClass().equals(Long.class))
                return ((BigDecimal) value).longValue();

        if (value.getClass().equals(BigDecimal.class))
            if (wantedType.getClass().equals(Float.class))
                return ((BigDecimal) value).floatValue();

        if (value.getClass().equals(BigDecimal.class))
            if (wantedType.getClass().equals(Double.class))
                return ((BigDecimal) value).doubleValue();

            else
                return value;

        return null;
    }

    public static List<Field> getFieldsByAnnotations(Class clazz, Class annotation) {
        List<Field> list = new ArrayList<Field>();

        for (Field f : clazz.getDeclaredFields()) {
            if (f.getAnnotation(annotation) != null)
                list.add(f);
        }
        return list;

    }

    public static Object getSqlValue(Object object) throws IllegalAccessException {
        Table table = (Table) object.getClass().getAnnotation(Table.class);
        if (table != null) {
            Field idField = getFieldsByAnnotations(object.getClass(), Id.class).get(0);
            idField.setAccessible(true);
            return idField.get(object);
        }

        return object;
    }

}




