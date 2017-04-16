package com.zrk1000.drpc.serviceimpl;


import com.zrk1000.drpc.model.User;

/**
 * Created by rongkang on 2017-03-11.
 */
public interface UserService {

    User getUser(String name) throws Exception;

}
