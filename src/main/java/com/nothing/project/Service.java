package com.nothing.project;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;

public class Service {
    protected Dogi contract;

    public Service(String address, Web3j web3j, Credentials credentials,BigInteger gasPrice, BigInteger gasLimit) {
        contract = Dogi.load(address, web3j, credentials, gasPrice, gasLimit);
    }

    public void deployToken(String to, BigInteger tokenId, String timestamps) throws Exception {
        RemoteCall<TransactionReceipt> mint = contract.mintWithTokenURI(to, tokenId, timestamps);
        mint.send();
    }

    public BigInteger loadToken(BigInteger tokenId) throws Exception {
        RemoteCall<BigInteger> tokens = contract.tokenByIndex(tokenId);
        BigInteger token = tokens.send();
        return token;
    }
}
