import com.azure.core.http.*;
import com.azure.core.http.netty.NettyAsyncHttpClientBuilder;
import com.azure.core.implementation.DateTimeRfc1123;
import reactor.core.publisher.Mono;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.OffsetDateTime;

public class App {
    public static void main(String [] args) throws NoSuchAlgorithmException, InvalidKeyException {
        // To get rid of the logger warning: https://github.com/Azure/azure-sdk-for-java/wiki/Logging-with-Azure-SDK
        HttpClient client = new NettyAsyncHttpClientBuilder().build();

        String workspaceId = "9aa9f2d3-dd4f-42b3-a60d-a49aa6c19ca1";
        String json = "[{\"DemoField1\":\"DemoValue1\",\"DemoField2\":\"DemoValue2\"},{\"DemoField3\":\"DemoValue3\",\"DemoField4\":\"DemoValue4\"}]";
        String logName = "TestLogType";

        /**
         * Get Shared Key
         * @see https://docs.microsoft.com/en-us/rest/api/loganalytics/workspaces/getsharedkeys
         */
        String sharedKey = "hqd9BmXhVGNSDFgYHUhovjjj/koIDwXE7u7dVsl0Iy2h66R0QOO2taJ4nqGh1ZRFoB8fkRHw2iOwxWY1ls4UPQ==";

        /**
         * Add JSON into Log Analytics Workspace
         * @see https://docs.microsoft.com/en-us/rest/api/loganalytics/create-request
         */
        String postDataUrl = String.format("https://%s.ods.opinsights.azure.com/api/logs?api-version=2016-04-01", workspaceId);
        DateTimeRfc1123 rfc1123 = new DateTimeRfc1123(OffsetDateTime.now());
        String dateTime = rfc1123.toString();

        byte [] jsonBytes = json.getBytes(StandardCharsets.UTF_8);
        String stringToHash = "POST\n" + jsonBytes.length + "\napplication/json\n" + "x-ms-date:" + dateTime + "\n/api/logs";
        String authorization = BuildAuth(workspaceId, sharedKey, stringToHash);

        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", authorization);
        headers.put("Content-Type", "application/json");
        // headers.put("Accept", "application/json");
        headers.put("Log-Type", logName);
        // The date that the request was processed in RFC 1123 format
        headers.put("x-ms-date", dateTime);
        // You can use an optional field to specify the timestamp from the data.
        // If the time field is not specified, Azure Monitor assumes the time is the message ingestion time
        headers.put("time-generated-field", "");

        HttpRequest dataRequest = new HttpRequest(HttpMethod.POST, postDataUrl);
        dataRequest.setHeaders(headers);
        // alternatively use dataRequest.setHeader()
        dataRequest.setBody(json);

        // It may takes a couple of minutes until it is visible
        Mono<HttpResponse> response = client.send(dataRequest);
        HttpResponse finalResponse = response.block();
        System.out.println(finalResponse.getStatusCode() + ": " + finalResponse.getBodyAsString().block());
    }

    private static String BuildAuth(String workspaceId, String sharedKey, String stringToHash) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance("HmacSHA256");

        SecretKeySpec secret_key = new SecretKeySpec(DatatypeConverter.parseBase64Binary(sharedKey), "HmacSHA256");
        mac.init(secret_key);
        String hashedString = DatatypeConverter.printBase64Binary(mac.doFinal(stringToHash.getBytes(StandardCharsets.UTF_8)));

        // return String.format("SharedKey %s:%s", workspaceId, hashedString);
        return "SharedKey " + workspaceId + ":" + hashedString;
    }
}
