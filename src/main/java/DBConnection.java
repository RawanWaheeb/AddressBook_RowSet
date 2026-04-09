import com.mysql.cj.jdbc.MysqlDataSource;
import java.io.FileInputStream;
import java.util.Properties;
import javax.sql.DataSource;

public class DBConnection {
    public static DataSource getDataSource() {
        MysqlDataSource ds = new MysqlDataSource();
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/db.properties")) {
            props.load(fis);
            ds.setUrl(props.getProperty("db.url"));
            ds.setUser(props.getProperty("db.user"));
            ds.setPassword(props.getProperty("db.password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
}