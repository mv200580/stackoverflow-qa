import com.sun.rowset.CachedRowSetImpl;
import com.sun.rowset.internal.Row;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.Test;

import javax.sql.rowset.CachedRowSet;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import static org.junit.Assert.*;


/**
 * JDBC RowSet toCollection()
 * http://stackoverflow.com/questions/39523704/java-jdbc-rowset-to-tocollection-method-use
 *
 * Created by smv on 19/09/16.
 */
public class RowSetTest {


    /**
     *
     * @throws Exception
     */
    @Test
    public void run() throws Exception {

//        Class.forName("com.sybase.jdbc3.jdbc.SybDriver");
//        Connection conn= DriverManager.getConnection("jdbc:sybase:Tds:161.8.44.175:4100/kkc","starodubtsev_mv","107538");

        Class.forName("net.sourceforge.jtds.jdbc.Driver");
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        CachedRowSet rowset = new CachedRowSetImpl();
        rowset.setUsername("hist");
        rowset.setPassword("hist");
        rowset.setUrl("jdbc:jtds:sqlserver://161.8.46.156/magnitog");
//        rowset.setUrl("jdbc:sqlserver://161.8.46.156/magnitog");
        rowset.setCommand("select 1 as s");
        rowset.execute();
//        while (rowset.next()) {
//            String res = String.format("id=%s name=%s", rowset.getString("id"), rowset.getString("descr"));
//            System.out.println(res);
//        }

        ResultSetMetaData md = rowset.getMetaData();
        List<Map<String, Object>> res = new ArrayList<>();
        Collection<Row> c = (Collection<Row>) rowset.toCollection();
        for(Row row: c) {
            Map<String, Object> m = new HashMap<String, Object>();
            for (int i = 1; i <= md.getColumnCount(); i++) {
                System.out.println(row.getClass());
                m.put(md.getColumnName(i), row.getColumnObject(i));
            }
            res.add(m);
        }

        res.stream().forEach(s -> System.out.println(s));
   }

    @Test
    public void test() {

        System.out.println("test");

    }




}