package com.back.back.service;

import com.back.back.config.BucketName;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final FileStore fileStore;

    @Override
    public void saveTodo(String title, MultipartFile file) {
        //check if the file is empty
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file");
        }
        //get file metadata
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        String path = String.format("%s/%s", BucketName.TODO_IMAGE.getBucketName(), "videos");
        String fileName = String.format("%s", title);
        try {
            fileStore.upload(path, fileName, Optional.of(metadata), file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to upload file", e);
        }
    }

    public void deleteTodo(String title) {
        fileStore.delete(title);
    }
}
