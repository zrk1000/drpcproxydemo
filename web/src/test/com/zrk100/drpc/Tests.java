package com.zrk100.drpc;

import org.apache.storm.Config;
import org.apache.storm.thrift.TException;
import org.apache.storm.thrift.transport.TTransportException;
import org.apache.storm.utils.DRPCClient;

/**
 * Created by rongkang on 2017-04-22.
 */
public class Tests {

    public static void main(String[] args) throws TException {
        Config config = new Config();
        DRPCClient client = new DRPCClient(config, "120.26.134.96", 3772, 10);
        String testTopology = client.execute("testTopology", "");
        System.out.println(testTopology);
    }
}
