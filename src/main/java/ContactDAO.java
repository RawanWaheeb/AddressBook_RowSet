import javax.sql.rowset.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ContactDAO {


    public void getAllContacts() {
        try {
            RowSetFactory factory = RowSetProvider.newFactory();
            JdbcRowSet rowSet = factory.createJdbcRowSet();

            var ds = (com.mysql.cj.jdbc.MysqlDataSource) DBConnection.getDataSource();
            rowSet.setUrl(ds.getUrl());
            rowSet.setUsername(ds.getUser());
            rowSet.setPassword(ds.getPassword());

            rowSet.setCommand("SELECT * FROM contacts");
            rowSet.execute();

            System.out.println("--- Reading from Database (JDBCRowSet) ---");
            while (rowSet.next()) {
                System.out.println("Name: " + rowSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void saveToXML() {
        try {
            RowSetFactory factory = RowSetProvider.newFactory();
            WebRowSet webRowSet = factory.createWebRowSet();

            var ds = (com.mysql.cj.jdbc.MysqlDataSource) DBConnection.getDataSource();
            webRowSet.setUrl(ds.getUrl());
            webRowSet.setUsername(ds.getUser());
            webRowSet.setPassword(ds.getPassword());

            webRowSet.setCommand("SELECT * FROM contacts");
            webRowSet.execute();

            try (FileOutputStream fos = new FileOutputStream("contacts_data.xml")) {
                webRowSet.writeXml(fos);
                System.out.println("\n[Success] XML file 'contacts_data.xml' has been created.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void readFromXML() {
        try {
            RowSetFactory factory = RowSetProvider.newFactory();
            WebRowSet webRowSet = factory.createWebRowSet();

            try (FileInputStream fis = new FileInputStream("contacts_data.xml")) {
                webRowSet.readXml(fis);

                System.out.println("--- Reading Back from XML File (WebRowSet) ---");
                while (webRowSet.next()) {
                    System.out.println("Name: " + webRowSet.getString("name") +
                            " | Phone: " + webRowSet.getString("phone"));
                }
            }
        } catch (Exception e) {
            System.out.println("[Error] Could not find or read the XML file.");
            e.printStackTrace();
        }
    }
}