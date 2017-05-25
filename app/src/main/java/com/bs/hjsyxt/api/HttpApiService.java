package com.bs.hjsyxt.api;


import com.bs.hjsyxt.bean.User;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wf on 2017/5/5.
 */

public interface HttpApiService {

    /**
     * 消费者登陆
     * @param username
     * @param password
     * @return
     */
    @GET("/Home/User/login_c")
    Observable<User> loginCUser(@Query("name") String username, @Query("psw") String password);

    /**
     * j经销商登陆
     * @param username
     * @param password
     * @return
     */
    @GET("/Home/User/login_i")
    Observable<User> loginIUser(@Query("name") String username,@Query("psw") String password);

}
