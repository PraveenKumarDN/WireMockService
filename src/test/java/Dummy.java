import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Dummy {
    public static final Logger log = Logger.getLogger(Dummy.class);
    public static void main(String args[]){

        List<String> list = new ArrayList<>();
        list.add("GKR5833861");
        list.add("ELECTION_CARD");
        boolean x = list.contains("GKR5833861"); // true
        boolean y = list.contains("foo"); // false




        /*String jwt = "eyJvd25lciI6ImltcyIsImFsZyI6IlJTMjU2In0.eyJhdWQiOiIvcmVzdC9mY3dhbGxldC92MS9nZW5lcmF0ZU90cEZvckFkZEJhbmtEZXRhaWxzIiwidXNlcklkIjoiVmpBeEl6RXlNRGswTVRKbUxUQTJNVFV0TkdOaE9TMWhZbU16TFdKaVpHWTJOVFZtT1RSaE9BIiwiZmNVc2VySWQiOjEzMjUyMTY0MCwidmFsaWRDU1JGIjpmYWxzZSwiZmlyc3ROYW1lIjoiTWFyc2hhaW1zIiwibGFzdE5hbWUiOiJNYXJzaGFpbXMiLCJlbWFpbCI6Imltc3Rlc3RydmhmaS42NzcwNjQ5MDU1QG50bHdvcmxkLmluIiwibW9iaWxlIjoiNjQ2NzI0NDU4NiIsImRpc3BsYXluYW1lIjoic2FjcmlmaWNlIiwiZXhwIjoxNTQ5MDAxMTYwfQ.dycHhCnx1PAz_sybeEAyDnjri92dT-F0I4zclnXif5qIoB-y7Od57sY8zuexzt4qdJDdpd3oPvVLxsVZy8QVNgNVjM50kGVTzQ14kTTPaZJo3-w_h-Oi1rkgPHYfGs8sqL9rOPiZV73xLPUiC-tOG2dc5alo-W9465scLKvzdKrFrMdVx3roqgCaT6avf7FFGkKcwiSaSs6rXxL5NNQEAk5NVcx6hw3CdvE4jkRAaGEn_uQtNVCOoaoxv67SoYG1cNQ7lonvOOUuO3xveDAs5hRZhl8XYPlfr7Iim83q2pcbMgN3FLarTb4_0ivnFohG9QW4uscA1Wm66V_n7c3lrg" ;

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
