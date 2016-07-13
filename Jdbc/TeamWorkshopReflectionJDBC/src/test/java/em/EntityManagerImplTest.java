package em;

import org.junit.Test;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Location;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * Created by Alexandru.Dinca on 7/8/2016.
 */
public class EntityManagerImplTest {
    //    <T> T findById(Class<T> entityClass, Long id);
    @Test
    public void testFindById() {
//        String findById = EntityManagerImpl.
//                EntityUtils.getTableName(Department.class);
        assertEquals("Table name should be departments!", "departments", tableName);
    }

    @Test
    public void testGetNextIdVal() {
        EntityManagerImpl emi = new EntityManagerImpl();
        Long result = emi.getNextIdVal("departments", "department_id");
        assertEquals(271, result.longValue());
    }

    //    <T> Object insert(T entity);
    @Test
    public void testInsertMethod() {

        EntityManagerImpl manager = new EntityManagerImpl();
        Location toBeInserted = new Location();
        toBeInserted.setCity("'Bucharest'");
        toBeInserted.setPostalCode("'235468'");
        toBeInserted.setStateProvince("'Bucharest'");
        toBeInserted.setStreetAddress("'Bucharest'");

        assertNotNull(manager.insert(toBeInserted));
    }


    //    <T> List<T> findAll(Class<T> entityClass);
    @Test
    public void testFindAll() {

    }

//     public <T> T update(T entity) {
    @Test
    public void testUpdate(){
        Department department=new Department();
        department.setDepartmentName("Administration");
        department.setId(10);
//        assertTrue();

    }

}
