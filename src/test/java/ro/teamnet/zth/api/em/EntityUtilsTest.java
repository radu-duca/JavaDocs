package ro.teamnet.zth.api.em;

/**
 * Created by user on 7/7/2016.
 */
public class EntityUtilsTest {

    public void testGetTableNameMethod() {
        String tableName = EntityUtils.getTableName(Department.class);
        assertEquals("Table name should be departments!", "departments", tableName);
    }


    }
