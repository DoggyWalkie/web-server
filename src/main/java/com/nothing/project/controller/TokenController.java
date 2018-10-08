package com.nothing.project.controller;

import com.nothing.project.Service;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.math.BigInteger;

@RestController
public class TokenController {
    @CrossOrigin
    @RequestMapping(value = "/token", method = RequestMethod.GET)
//    public BigInteger getTokenByIndex(@PathVariable("index") BigInteger index) {
    public BigInteger getTokenByIndex(@PathParam(value = "index") BigInteger index) {
        Service service = new Service();
        BigInteger result = null;
        try {
            result = service.loadToken(index);
        } catch (Exception e) {
            //todo
        }
        return result;
    }





}
