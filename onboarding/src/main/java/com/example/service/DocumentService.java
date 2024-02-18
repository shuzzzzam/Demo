package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.repository.UserDocumentRepository;

public class DocumentService {

    @Autowired
    UserDocumentRepository userDocumentRepository;
    
}
