import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 *
 * Update zip-archive dynamically in memory
 *
 * Created by smv on 19/09/16.
 */
public class ZipappendTest {

    @Test
    public void run() throws Exception{
        System.out.println("start");

        // read files to byte arrays
        InputStream is = getClass().getResourceAsStream("/zip_without_xml.zip");;
        byte[] zipWithoutXml = IOUtils.toByteArray(is);

        is = getClass().getResourceAsStream("/slide1.xml");;
        byte[] xml = IOUtils.toByteArray(is);

        is = getClass().getResourceAsStream("/zip_without_xml.zip");;
        byte[] zipWithXml = IOUtils.toByteArray(is);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // get zip stream from byte array
        ZipInputStream zin = new ZipInputStream(new ByteArrayInputStream(zipWithoutXml));
        ZipOutputStream zout = new ZipOutputStream(out);
        byte[] buf = new byte[1024];

        // put existsing content into new zip byte array
        ZipEntry entry = zin.getNextEntry();
        while (entry != null) {
            System.out.println(entry.toString());

            String name = entry.getName();
            zout.putNextEntry(new ZipEntry(name));
            int len;
            while ((len = zin.read(buf)) > 0) {
                zout.write(buf, 0, len);
            }
            entry = zin.getNextEntry();
        }
        zin.close();

        // put new file content into new zip byte array
        ZipEntry newEntry = new ZipEntry("ppt/slides/slide1.xml");
        zout.putNextEntry(newEntry);
        int len;
        ByteArrayInputStream xmlBais = new ByteArrayInputStream(xml);
        while ((len = xmlBais.read(buf)) > 0) {
            zout.write(buf, 0, len);
        }
        zout.finish();

        // getting the result
        byte[] result = out.toByteArray();
        zout.close();
        out.close();

        FileOutputStream fos = new FileOutputStream("f:\\1.zip");
        fos.write(result);
        fos.close();

//        ZipInputStream zin2 = new ZipInputStream(new ByteArrayInputStream(result));
//        entry = zin2.getNextEntry();
//        while (entry != null) {
//            System.out.println(entry.toString());
//            entry = zin2.getNextEntry();
//        }

        System.out.println("stop");
    }
}
