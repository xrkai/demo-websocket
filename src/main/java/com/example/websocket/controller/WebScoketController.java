package com.example.websocket.controller;

import com.example.websocket.socket.EchoHander;
import com.example.websocket.socket.EchoSocketHander;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequestMapping("/websocket")
public class WebScoketController {

    @Autowired
    private EchoHander echoHander;

    @Autowired
    private EchoSocketHander echoSocketHander;

    @RequestMapping("/index")
    public String websocket() {
        return "websocket.html";
    }

    @RequestMapping("/websocket/send/all")
    public String sendMsgToAll(@RequestParam("msg") String msg) throws IOException {
        echoHander.sendMsgToAll(msg);
        return "发送成功";
    }

    @RequestMapping("/websocket/send/one")
    public String sendMsgToAll(@RequestParam("msg") String msg, @RequestParam("id") String id) throws IOException {
        echoHander.sendMsgToOne(msg, id);
        return "发送成功";
    }

    @RequestMapping("/scok/send/all")
    public String sendSockMsgToAll(@RequestParam("msg") String msg) throws IOException {
        echoSocketHander.sendMsgToAll(msg);
        return "发送成功";
    }

    @RequestMapping("/sock/send/one")
    public void sendSockMsgToAll(@RequestParam("msg") String msg, @RequestParam("id") String id) throws IOException {
        echoSocketHander.sendMsgToOne(msg, id);
    }
}
