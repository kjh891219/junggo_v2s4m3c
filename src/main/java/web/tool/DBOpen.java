package web.tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBOpen {
  public Connection getConnection(){
    Connection con = null;         // DBMS ����
    String jdbc = "org.gjt.mm.mysql.Driver"; // MySQL ���� Drvier 
    String url = "jdbc:mysql://localhost:3306/web?useUnicode=true&characterEncoding=euckr"; 
    String user = "root"; 
    String pass = "1234";
    
    try {
      Class.forName(jdbc); // memory�� ����̹� Ŭ������ �ε���.
      con = DriverManager.getConnection(url, user, pass); // MySQL ����
      
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    } 
    
    return con;
  }
}




