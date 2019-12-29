package jp.co.nttdata.myapp.controller.socket;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping(value = "/socket")
public class WebSocketController {
    private WebSocket websocket = new WebSocket();

   @PostMapping(value = "/message")
    public void getSocketMessage(HttpServletRequest request) throws IOException {
       JSONObject json = new JSONObject();
       json.put("to", request.getSession().getId());
       json.put("msg", "欢迎连接WebSocket！！！！");
       websocket.onMessage(json.toJSONString());
    }

}

