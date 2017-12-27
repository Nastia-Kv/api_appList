//import java.io.*;
//import java.net.*;
//import java.util.ArrayList;
//import java.util.List;
//import javax.net.ssl.HttpsURLConnection;
//
//
//import static org.apache.http.HttpHeaders.USER_AGENT;
//
//public class TestCookies {
//    private List<String> cookies;
//    private HttpsURLConnection connection;
//
//    public static void main(String[] args) {
//
//        String url = "https://qe5b.usw1.aws.tidemark.net/reference/api/go/auth";
//        String homePage = "https://qe5b.usw1.aws.tidemark.net/reference/";
//
//        TestCookies http = new TestCookies();
//
//        // 1. Send a POST request to extract the form's data
//        String page = http.GetPageContent(url);
//        String postParams = http.getFormParams(page, "username@gmail.com", "password");
//
//        // 2. Construct above post's content and then send a POST request for
//        // authentication
//        http.sendPost(url, postParams);
//
//        // 3. success then go to home page.
//        String result = http.GetPageContent(homePage);
//        System.out.println(result);
//
//
//
//
//
//
//
//    }
//    private String GetPageContent(String url) throws Exception {
//        URL object = new URL(url);
//        connection = (HttpsURLConnection) object.openConnection();
//        connection.setRequestMethod("POST");
//
//        //act like a browser
//        connection.setRequestProperty("User-Agent", USER_AGENT);
//        connection.setRequestProperty("Accept", "");
//        connection.setRequestProperty("Accept-Language", "");
//
//        if (cookies != null){
//            for (String cookie : this.cookies){
//                connection.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
//            }
//        }
//        int responseCode = connection.getResponseCode();
//        System.out.println("\nSending 'GET' request to URL : " + url);
//        System.out.println("Response Code : " + responseCode);
//
//    }
//
//
//
//    private void sendPost(String url, String postParams) {
//    }
//
//    private String getFormParams(String page, String s, String password) {
//    }
//
//
//}
