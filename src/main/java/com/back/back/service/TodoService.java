package com.back.back.service;

import org.springframework.web.multipart.MultipartFile;

public interface TodoService {
    void saveTodo(String title, MultipartFile file);
}
