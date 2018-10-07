package com.nothing.project;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
//        https://kovan.infura.io/<your-token>
//        Web3j web3j = Web3j.build(new HttpService("https://morden.infura.io/your-token"));
        Web3j web3j = Web3j.build(new HttpService("https://kovan.infura.io/<your-token>"));

        String privateKey = "573268737293678";
        Credentials credentials = Credentials.create(privateKey);
        BigInteger GAS_PRICE = new BigInteger("3452345");
        BigInteger GAS_LIMIT = new BigInteger("3412342134");
//        use an existing contract:
//        Dogi contract = Dogi.load(
//                "0x<address>|<ensName>", web3j, <credentials>, GAS_PRICE, GAS_LIMIT);
        Dogi contract = Dogi.load(
                "0x7d3451ff4C954918697530c58F09BD1FB648BF7A",
                web3j,
                credentials,
                GAS_PRICE,
                GAS_LIMIT);

//        contract.



////        To transact with a smart contract:
//        TransactionReceipt transactionReceipt = contract.someMethod(
//                <param1>,
//             ...).send();
//
//
//        contract.

        web3j.shutdown();
    }
}
