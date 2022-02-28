package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Singleton {
    private Connection connection;
    private static Singleton singletonConnection;

    public Singleton(){
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/UniversitySystem",
                            "postgres", "Alireza1376");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static Singleton getInstance(){
        try {
            if (singletonConnection == null) {
                singletonConnection = new Singleton();
            } else if (singletonConnection.getConnection().isClosed()) {
                singletonConnection = new Singleton();
            }
            return singletonConnection;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return singletonConnection;
    }


}
