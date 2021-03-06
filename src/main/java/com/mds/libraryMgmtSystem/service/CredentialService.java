package com.mds.libraryMgmtSystem.service;

import com.mds.libraryMgmtSystem.entity.Credential;
import com.mds.libraryMgmtSystem.repository.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CredentialService {
    @Autowired
    private CredentialRepository credentialRepository;

    public Page<Credential> getCredential(Pageable pageable) {
        return credentialRepository.findAll(pageable);
    }

    public void deleteCredential(Long id) {
        credentialRepository.deleteById(id);
    }

    public Credential findById(Long id) {
        return credentialRepository.findById(id).orElse(null);
    }

    public Credential save(Credential credential) {
        return credentialRepository.save(credential);
    }

    public Credential findByUserId(Long id) {
        return credentialRepository.findByUserId(id);
    }

    public List<Credential> findByCredentialUserId(Long id) {
        return credentialRepository.findByCredentialUserId(id);
    }


    public Optional<Credential> findOldPassword(String password) {
        return credentialRepository.findOldPassword(password);
    }
}
