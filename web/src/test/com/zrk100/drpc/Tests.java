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
        DRPCClient client = new DRPCClient(config, "localhost", 49772, 10);
        String testTopology = client.execute("testTopology", "{\"interfaceClazz\":\"com.zrk1000.drpc.serviceimpl.UserService\",\"method\":\"getUser\",\"methodHashCode\":-1910929195,\"params\":[\"tom\"]}");
        System.out.println(testTopology);
    }
}
