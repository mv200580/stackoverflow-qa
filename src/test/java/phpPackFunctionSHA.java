import org.junit.Test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * Created by smv on 22/09/16.
 */
public class phpPackFunctionSHA {

    private static String hmacSha(String KEY, String VALUE, String SHA_TYPE) {
        try {

            SecretKeySpec signingKey = new SecretKeySpec(KEY.getBytes("UTF-8"), SHA_TYPE);
            Mac mac = Mac.getInstance(SHA_TYPE);
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(VALUE.getBytes("UTF-8"));

            byte[] hexArray = {
                    (byte) '0', (byte) '1', (byte) '2', (byte) '3',
                    (byte) '4', (byte) '5', (byte) '6', (byte) '7',
                    (byte) '8', (byte) '9', (byte) 'a', (byte) 'b',
                    (byte) 'c', (byte) 'd', (byte) 'e', (byte) 'f'
            };
            byte[] hexChars = new byte[rawHmac.length * 2];
            for (int j = 0; j < rawHmac.length; j++) {
                int v = rawHmac[j] & 0xFF;
                hexChars[j * 2] = hexArray[v >>> 4];
                hexChars[j * 2 + 1] = hexArray[v & 0x0F];
            }
            return new String(hexChars);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public String pack(String hex) {
        String input = hex.length() % 2 == 0 ? hex : hex + "0";
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i += 2) {
            String str = input.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }
        return output.toString();
    }

    @Test
    public void run() throws Exception {

        String data = "hello world";
        String key = "7E066";
        System.out.println(hmacSha(key, data, "HmacSHA256"));
        System.out.println(hmacSha(pack(key), data, "HmacSHA256"));

        StringBuilder output = new StringBuilder();
        output.append((char) Integer.parseInt("7E", 16));
        output.append((char) Integer.parseInt("06", 16));
        output.append((char) Integer.parseInt("60", 16));
        System.out.println(output.toString());
    }

}
