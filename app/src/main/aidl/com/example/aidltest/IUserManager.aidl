// IUserManager.aidl
package com.example.aidltest;

import com.example.aidltest.User;

interface IUserManager {

    User getUser(String name);

}
