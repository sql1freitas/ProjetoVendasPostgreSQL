package Jbdc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Matheus
 */

public class ConnectionFactory {

    private static Connection connection;

    private ConnectionFactory(Connection connection){

    }


    private static Connection initConnection() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/vendas_online", "postgres","senhaBoladona");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }





    public static Connection getConnection(){
        if (connection == null){
            connection = initConnection();
        }
        return connection;
    }


}
