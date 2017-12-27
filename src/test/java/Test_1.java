import org.apache.http.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


public class Test_1 {
    public Test_1() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {


        CloseableHttpClient client = getClient();

        //https://qe5b.usw1.aws.tidemark.net/reference/api/go/auth
        HttpPost authHttpPost = new HttpPost("https://qe3b.usw1.aws.tidemark.net/reference/api/go/auth");
        System.out.println("authHttpPost1 " + authHttpPost);
        authHttpPost.setHeader("Content-Type", "application/json");

        AuthRequest authRequest = new AuthRequest();
        authRequest.setPassword("Password1");
        authRequest.setScreenHeight("1080");
        authRequest.setScreenWidth("1920");
        authRequest.setTouchStartSupported(false);
        authRequest.setUserName("tidemark.automation+reference.admin@gmail.com");
        System.out.println("authRequest " + authRequest);

        ObjectMapper mapper = new ObjectMapper();
        String authRequestString = mapper.writeValueAsString(authRequest);
        HttpEntity authRequestEntity = new StringEntity(authRequestString);
        authHttpPost.setEntity(authRequestEntity);

        CloseableHttpResponse authResponse = client.execute(authHttpPost);
        HttpEntity authResponseEntity = authResponse.getEntity();
        String authEntityResponseString = EntityUtils.toString(authResponseEntity);
        System.out.println("entityString ");
        System.out.println(authEntityResponseString);
        int responseCode = authResponse.getStatusLine().getStatusCode();
        System.out.println(responseCode);

        //https://qe5b.usw1.aws.tidemark.net/reference/api/getUserLandingPage

        HttpPost userLandingPagePost = new HttpPost("https://qe3b.usw1.aws.tidemark.net/reference/api/getUserLandingPage");
        System.out.println("authHttpPost2 " + userLandingPagePost);
        userLandingPagePost.setHeader("Content-Type", "application/json");

        UserLandingPageRequest userLandingPageRequest = new UserLandingPageRequest();
        userLandingPageRequest.setCurrentState(null);
        userLandingPageRequest.setWidgetState(null);

        ObjectMapper landingPageMapper = new ObjectMapper();
        String landingPageRequestString = landingPageMapper.writeValueAsString(userLandingPageRequest);
        HttpEntity landingPageRequestEntity = new StringEntity(landingPageRequestString);
        userLandingPagePost.setEntity(landingPageRequestEntity);


        CloseableHttpResponse userLandingPageResponse = client.execute(userLandingPagePost);
        HttpEntity userLandingPageResponseEntity = userLandingPageResponse.getEntity();
        String userLandingPageResponseEntityString = EntityUtils.toString(userLandingPageResponseEntity);
        JsonNode landingPageNode = mapper.readTree(userLandingPageResponseEntityString);
        JsonNode currentStateJsonNode = landingPageNode.get("currentState");
        System.out.println(currentStateJsonNode);
        CurrentState currentState = mapper.readValue(currentStateJsonNode, CurrentState.class);
        System.out.println("Current state " + currentState);


        //https://qe5b.usw1.aws.tidemark.net/reference/api/getAppList
        HttpPost appListPost = new HttpPost("https://qe3b.usw1.aws.tidemark.net/reference/api/getAppList");
        System.out.println("authHttpPost3 " + appListPost);
        appListPost.setHeader("Content-Type", "application/json");
        AppListRequest appListRequest = new AppListRequest();
        appListRequest.setCurrentState(currentState);
        appListRequest.setFilter("[{\"property\":null,\"value\":null}]");
        appListRequest.setWidgetState(null);


        String appListRequestString = mapper.writeValueAsString(appListRequest);
        HttpEntity appListPageRequestEntity = new StringEntity(appListRequestString);
        appListPost.setEntity(appListPageRequestEntity);

        CloseableHttpResponse appListPageResponse = client.execute(appListPost);
        HttpEntity appListResponseEntity = appListPageResponse.getEntity();

        String appListPageResponseEntityString = EntityUtils.toString(appListResponseEntity);
        int responseCode3 = appListPageResponse.getStatusLine().getStatusCode();
        System.out.println(responseCode3);

        JsonNode appListNode = mapper.readTree(appListPageResponseEntityString);

        JsonNode instanceType = appListNode.get("instanceType");
        JsonNode id = appListNode.get("id");
        JsonNode nodeType = appListNode.get("nodeType");
        JsonNode features = appListNode.get("features");

        System.out.println("instanceType " + instanceType);
        System.out.println("id " + id);
        System.out.println("nodeType " + nodeType);
        System.out.println("features " + features);

        JsonNode childrenJsonNode = appListNode.get("children");
        System.out.println("children " + childrenJsonNode);
        Applications[] applications = mapper.readValue(childrenJsonNode, Applications[].class);

        for (int i = 0; i < applications.length; i++) {
            String appName = applications[i].getName();
            System.out.println(appName);

        }


//        //https://qe3b.usw1.aws.tidemark.net/reference/api/getUserLandingPage
//        HttpPost userLandingPagePost2 = new HttpPost("https://qe3b.usw1.aws.tidemark.net/reference/api/getUserLandingPage");
//        System.out.println("authHttpPost4 " + userLandingPagePost2);
//        userLandingPagePost.setHeader("Content-Type", "application/json");
//
//        UserLandingPageRequest userLandingPageRequest2 = new UserLandingPageRequest();
//        userLandingPageRequest.setCurrentState(null);
//        userLandingPageRequest.setWidgetState(null);
//
//        String userLandingPage2String = mapper.writeValueAsString(userLandingPageRequest2);
//        HttpEntity userLandingPage2RequestEntity = new StringEntity(userLandingPage2String);
//        userLandingPagePost2.setEntity(userLandingPage2RequestEntity);
//
//        CloseableHttpResponse userLandingPage2Response = client.execute(userLandingPagePost2);
//        HttpEntity userLandingPage2ResponseEntity = userLandingPagePost2.getEntity();
//        String userLandingPage2ResponseString = EntityUtils.toString(userLandingPage2ResponseEntity);
//        int userLandingPage2ResponseCode = userLandingPage2Response.getStatusLine().getStatusCode();
//        System.out.println("userLandingPage2ResponseCode " + userLandingPage2ResponseCode);


    }

    public static CloseableHttpClient getClient() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        PoolingHttpClientConnectionManager mgr;

        SSLContext sslContext = SSLContexts.custom()
                .loadTrustMaterial(null, new TrustStrategy() {
                    public boolean isTrusted(X509Certificate[] chain, String authType)
                            throws CertificateException {
                        return true;
                    }
                }).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslContext, new HostnameVerifier() {
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });

        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
                .<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", sslsf)
                .build();

        mgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        mgr.setDefaultMaxPerRoute(50);
        mgr.setMaxTotal(50);

        BasicCookieStore cookieStore = new BasicCookieStore();

        return HttpClientBuilder.create()
                .setConnectionManager(mgr)
                .setDefaultCookieStore(cookieStore)
                .build();
    }

}


