package database;

import org.junit.Assert;
import org.junit.Test;
import ro.teamnet.zth.api.database.DBManager;

import java.sql.SQLException;

/**
 * Created by Alexandru.Dinca on 7/8/2016.
 */
public class DBManagerTest {

        @Test
        public void testGetConnectionMethod() throws ClassNotFoundException, SQLException {
            Assert.assertNotNull(DBManager.getConnection());
        }

        @Test
        public void testCheckConnectionMethod() throws ClassNotFoundException, SQLException {
            boolean connected = DBManager.checkConnection(DBManager.getConnection());
            Assert.assertTrue(connected);
        }

}
