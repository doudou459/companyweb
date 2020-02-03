package com.chaoweifish.companyweb.service;

import com.chaoweifish.companyweb.mapper.ContactMapper;
import com.chaoweifish.companyweb.pojo.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactMapper contactMapper;

    @Override
    public Contact getContact() {
        Contact contact =contactMapper.getContact();
        return contact;
    }

    @Override
    public String setContatct(String detail) {
        String result = "fail";
        if(contactMapper.setContact(detail)==1){
            result = "success";
        }
        return result;
    }
}
