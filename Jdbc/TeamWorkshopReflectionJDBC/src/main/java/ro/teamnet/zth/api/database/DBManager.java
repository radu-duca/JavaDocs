package ro.teamnet.zth.api.database;

import java.sql.*;

public class DBManager {

    private static final String CONNECTION_STRING = "jdbc:oracle:thin:@" + DBProperties.IP + ":" + DBProperties.PORT+":xe";

    private DBManager() throws UnsupportedOperationException{
    }

    private static void registerDriver(){
        try {
            Class.forName(DBProperties.DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        registerDriver();
        try {
            return DriverManager.getConnection(CONNECTION_STRING,DBProperties.USER, DBProperties.PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean checkConnection(Connection connection){
        boolean flag = false;
        Statement statement = null;
        String query = "SELECT 1 FROM DUAL";
        try {
            statement = connection.createStatement();
            flag = statement.execute(query);
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return flag;
    }


}
