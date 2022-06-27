package com.kkp.pelatihanwebservice.internal.services.userApi;

import com.kkp.pelatihanwebservice.internal.models.UserApi;

public interface UserApiService {
    //user admin
    UserApi createEmployee(UserApi userApi);

    UserApi updateEmployee(Long id, UserApi userApi);

    UserApi deleteEmployee(Long id);


    // user mobile
    UserApi createUserApi(UserApi userApi);

    UserApi updateUserApi(Long id, UserApi userApi);

    UserApi deleteUserApi(Long id);
}
