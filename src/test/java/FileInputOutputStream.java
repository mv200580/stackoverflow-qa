import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by smv on 19/09/16.
 */
public class FileInputOutputStream {

    @Test
    public void run() {
        uploadToFTP(new File("f:\\work\\1.txt"));
    }

    public void uploadToFTP(File file) {

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd hh24:mm:ss");
            final ByteArrayInputStream stream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
            String date = dateFormat.format(new Date());
            String filename = date.replaceAll(":", "-");

            FileOutputStream out = new FileOutputStream(new File(String.format("f:\\work\\%s.txt", filename)));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = stream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
