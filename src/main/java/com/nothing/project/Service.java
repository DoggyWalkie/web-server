package com.nothing.project;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Service {
    protected DoggyWalkie contract;

    public Service() {
        String address = "0xf4A1f285B2b539b629f8d3C8c66A63977Cd0a030";
        Web3j web3j = Web3j.build(new HttpService("https://kovan.infura.io/<your-token>"));
        Credentials credentials;
        BigInteger gasPrice = new BigInteger("3");
        BigInteger gasLimit = new BigInteger("228080");
        String privateKey = "21E8B3A3EDA8C296BB6C54246E66258C6F407DA36B15596469ABB28558CA04E4";
        credentials = Credentials.create(privateKey);
        contract = DoggyWalkie.load(address, web3j, credentials, gasPrice, gasLimit);
    }

    public Service(String address, Web3j web3j, Credentials credentials,BigInteger gasPrice, BigInteger gasLimit) {
        contract = DoggyWalkie.load(address, web3j, credentials, gasPrice, gasLimit);
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

    public List<BigInteger> loadTokensByList(List<BigInteger> tokenIds) {
        List<BigInteger> tokens = new ArrayList<>();
        for (BigInteger tokenId : tokenIds) {
            try {
                tokenIds.add(loadToken(tokenId));
            } catch (Exception e) {
                //todo
            }
        }
        return tokens;
    }

    public List<BigInteger> loadTokensByGap(BigInteger from, BigInteger to) {
        List<BigInteger> tokens = new ArrayList<>();
        for (BigInteger i = to; i.compareTo(from) >= 0; i = i.subtract(BigInteger.ONE)) {
            try {
                tokens.add(loadToken(i));
            } catch (Exception e) {
                //todo
            }
        }
        return tokens;
    }

    public Object getTokenUri(BigInteger tokenId) throws Exception {
        RemoteCall tokenURI = contract.tokenURI(tokenId);
        return tokenURI.send();
    }
}
