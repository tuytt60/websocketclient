import com.casic.websocket.MyWebSocketClient;
import com.casic.websocket.WebsocketClient;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringEscapeUtils;
import org.java_websocket.WebSocket;

import javax.websocket.MessageHandler;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

public class MyTest {
//    public static String sendExecuteRequest(){
//        msg_type = 'execute_request';
//        content = { 'code' : code, 'silent':False }
//        hdr = { 'msg_id' : uuid.uuid1().hex,
//                'username': 'test',
//                'session': uuid.uuid1().hex,
//                'data': datetime.datetime.now().isoformat(),
//                'msg_type': msg_type,
//                'version' : '5.0' }
//        msg = { 'header': hdr, 'parent_header': hdr,
//                'metadata': {},
//        'content': content }
//    }

    public static void main(String[] arg0) throws URISyntaxException, InterruptedException, IOException {

        MessageHandler.Whole<String> messageHandler = new MessageHandler.Whole<String>() {

            @Override
            public void onMessage(String message) {
                System.out.println("****************" + message);
                JSONObject rspJO = JSONObject.fromObject(message);
                JSONObject contentJO = rspJO.getJSONObject("content");
                String content = contentJO.getString("text");
                String unescapeJava = StringEscapeUtils.unescapeJava(content);
                System.out.println(unescapeJava);
            }
        };
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization","Token 1e1a5265bb73fb04b19c212ec0cda4e7a73f39c40ce1a07a");
        WebsocketClient websocketClient = new WebsocketClient("ws://127.0.0.1:8888/api/kernels/dbfeca51-0736-4860-97bb-96fc6f882bac/channels?session_ideb8bb0d6-29e3-45d8-9d2f-26629dff0a98", headers, messageHandler);

        websocketClient.sendMessage("{\"header\": {\"msg_id\": \"17e5db26652111ea8ae640f02f28909a\", \"username\": \"test\", \"session\": \"17e5db27652111eab5da40f02f28909a\", \"data\": \"2020-03-13T19:52:19.748739\", \"msg_type\": \"execute_request\", \"version\": \"5.0\"}, \"parent_header\": {\"msg_id\": \"17e5db26652111ea8ae640f02f28909a\", \"username\": \"test\", \"session\": \"17e5db27652111eab5da40f02f28909a\", \"data\": \"2020-03-13T19:52:19.748739\", \"msg_type\": \"execute_request\", \"version\": \"5.0\"}, \"metadata\": {}, \"content\": {\"code\": \"ls\", \"silent\": \"FALSE\"}}");

        Thread.sleep(6000);
    }

}