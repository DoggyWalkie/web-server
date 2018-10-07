package com.nothing.project;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.web3j.abi.datatypes.Address;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class DogiTest {
    private String address;
    private Web3j web3j;
    private Credentials credentials;
    private BigInteger GAS_PRICE;
    private BigInteger GAS_LIMIT;

    @Before
    public void setUp() throws Exception {
        address = "0x7d3451ff4C954918697530c58F09BD1FB648BF7A";
        web3j = Web3j.build(new HttpService("https://kovan.infura.io/<your-token>"));
        String privateKey = "21E8B3A3EDA8C296BB6C54246E66258C6F407DA36B15596469ABB28558CA04E4";
        credentials = Credentials.create(privateKey);
        GAS_PRICE = new BigInteger("3");
        GAS_LIMIT = new BigInteger("228080");
    }

    @After
    public void tearDown() throws Exception {
        address = null;
        web3j = null;
        credentials = null;
        GAS_PRICE = null;
        GAS_LIMIT = null;
    }


    @Test
    public void deployAdnLoad() throws Exception {
        Dogi contract = Dogi.load(address, web3j, credentials, GAS_PRICE, GAS_LIMIT);
//        String hash = web3j.web3Sha3("CONTENTS").send().getResult();
//        Address deliverable = new Address(hash);


//        String myPK = "21E8B3A3EDA8C296BB6C54246E66258C6F407DA36B15596469ABB28558CA04E4";
        String to = "0x54A2cCe3a2feDA41c747EE50d08D8836F54878Ef";
        BigInteger tokenId = new BigInteger("2");
        String timestamps = "1540011905:1540021905";
//        RemoteCall<TransactionReceipt> mint = contract.mint(to, tokenId);
        RemoteCall<TransactionReceipt> mint = contract.mintWithTokenURI(to, tokenId, timestamps);
        mint.send();

//        contract.tokenOfOwnerByIndex("45435989324", new BigInteger("1"));
        RemoteCall<BigInteger> tokens = contract.tokenByIndex(new BigInteger("2"));
        BigInteger token = tokens.send();


//        contract.store(deliverable).get();
//        contract.delivered(deliverable).get();
//        BigInteger result = contract.statusFor(deliverable).get()
//                .getValue();

        assertNotNull("NULL!!!", tokens);
//        assertThat(result, is(BigInteger.valueOf(1)));
    }
}