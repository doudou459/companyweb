package com.chaoweifish.companyweb.service;

import java.util.HashMap;

public interface AdminLoginService {
    HashMap<String,Object> doLogin(String loginID, String loginKey);
    String changeKey(String loginID,String oldKey,String newKey);
}
