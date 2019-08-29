import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Dummy {
    public static final Logger log = Logger.getLogger(Dummy.class);
    public static void main(String args[]){

        List<String> list = new ArrayList<>();
        list.add("GKR5833861");
        list.add("asdfghj");
        boolean x = list.contains("GKR5833861"); // true
        boolean y = list.contains("foo"); // false

        /*String jwt = "rfyf" ;

        System.out.println("------------ Decode JWT ------------");
        String[] split_string = jwt.split("\\.");
        String base64EncodedHeader = split_string[0];
        String base64EncodedBody = split_string[1];
        String base64EncodedSignature = split_string[2];

        System.out.println("~~~~~~~~~ JWT Header ~~~~~~~");
        Base64 base64Url = new Base64(true);
        String header = new String(base64Url.decode(base64EncodedHeader));
        System.out.println("JWT Header : " + header);
        log.info("JWT Header : " + header);

        System.out.println("~~~~~~~~~ JWT Body ~~~~~~~");
        String body = new String(base64Url.decode(base64EncodedBody));
        System.out.println("JWT Body : "+body);
        log.info("JWT Body : "+body);

        System.out.println("~~~~~~~~~ JWT Signature ~~~~~~~");
        String signature = new String(base64Url.decode(base64EncodedSignature)) ;
        System.out.println("JWT Signature : " + signature);
        log.info("JWT Signature : " + signature);*/
            }

}
