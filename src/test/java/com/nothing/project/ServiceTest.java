package com.nothing.project;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ServiceTest {
    private String address;
    private Web3j web3j;
    private Credentials credentials;
    private BigInteger GAS_PRICE;
    private BigInteger GAS_LIMIT;
    private String timestamps;
    private BigInteger tokenId;
    private String to;
    private Service service;

    @Before
    public void setUp() {
        address = "0x7d3451ff4C954918697530c58F09BD1FB648BF7A";
        web3j = Web3j.build(new HttpService("https://kovan.infura.io/<your-token>"));
        String privateKey = "21E8B3A3EDA8C296BB6C54246E66258C6F407DA36B15596469ABB28558CA04E4";
        credentials = Credentials.create(privateKey);
        GAS_PRICE = new BigInteger("3");
        GAS_LIMIT = new BigInteger("228080");
        timestamps = "1540011905:1540021905";
        tokenId = new BigInteger("2");//unique
        to = "0x54A2cCe3a2feDA41c747EE50d08D8836F54878Ef";
        service = new Service(address, web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

    @After
    public void tearDown() {
        web3j.shutdown();
        address = null;
        web3j = null;
        credentials = null;
        GAS_PRICE = null;
        GAS_LIMIT = null;
        timestamps = null;
        tokenId = null;
        to = null;
    }

    @Test
    public void deployAndLoad() throws Exception {
        BigInteger token;
        try {
            service.deployToken(to, tokenId, timestamps);// tokenId need to be unique
        } catch (Exception e) {
            System.out.println("Error: Probably not unique tokenId.");
        }
        token = service.loadToken(tokenId);

        assertNotNull("NULL!!!", token);
    }

    @Test
    public void getTokensByGap() {
        BigInteger from = new BigInteger("0");
        BigInteger to = new BigInteger("3");
        List<BigInteger> tokensExpected = new ArrayList<>() {{
            add(new BigInteger("3"));
            add(new BigInteger("2"));
            add(new BigInteger("1"));
            add(new BigInteger("0"));
        }};
        List<BigInteger> tokensActual = service.loadTokensByGap(from, to);
        System.out.println(tokensActual);
        assertEquals(tokensExpected, tokensActual);
    }
}