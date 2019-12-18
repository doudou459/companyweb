package com.chaoweifish.companyweb.service;

import org.springframework.web.multipart.MultipartFile;

public interface StoreFile {
    String storeFile(MultipartFile file);
}
