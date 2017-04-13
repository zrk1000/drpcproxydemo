package com.zrk1000.drcp.serviceimpl;


import com.zrk1000.drcp.model.User;

/**
 * Created by rongkang on 2017-03-11.
 */
public interface UserService {

    User getUser(String name) throws Exception;

}
