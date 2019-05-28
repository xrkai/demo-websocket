package com.example.websocket.controller;

import com.example.websocket.socket.EchoHander;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/websocket")
public class WebScoketController {

    @Autowired
    private EchoHander echoHander;

    @RequestMapping("/send/all")
    public String sendMsgToAll(@RequestParam("msg") String msg) throws IOException {
        echoHander.sendMsgToAll(msg);
        return "发送成功";
    }

    @RequestMapping("/send/one")
    public String sendMsgToAll(@RequestParam("msg") String msg, @RequestParam("id") String id) throws IOException {
        echoHander.sendMsgToOne(msg, id);
        return "发送成功";
    }
}
