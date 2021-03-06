package com.mds.libraryMgmtSystem.service;

import com.mds.libraryMgmtSystem.entity.LibraryCard;
import com.mds.libraryMgmtSystem.repository.LibraryCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryCardService {
    @Autowired
    private LibraryCardRepository libraryCardRepository;
//
//    public List<LibraryCard> getLibraryCard(Pageable pageable) {
//        return libraryCardRepository.findAll(pageable);
//    }

    public LibraryCard findById(Long id) {
        return libraryCardRepository.findById(id).orElse(null);
    }

    public LibraryCard addLibraryCard(LibraryCard libraryCard) {
        return libraryCardRepository.save(libraryCard);
    }

    public void deleteLibraryCard(Long id) {
        libraryCardRepository.deleteById(id);
    }

    public LibraryCard updateLibraryCard(LibraryCard libraryCard) {
        return libraryCardRepository.save(libraryCard);
    }

    public Optional<LibraryCard> findByRollNo(String rollNo) {
        return libraryCardRepository.findByRollNo(rollNo);
    }

    public List<LibraryCard> searchLibraryCard(String rollNo) {
        return libraryCardRepository.findByLibraryCardRollNo(rollNo);
    }

    public List<LibraryCard> getLibraryCard(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<LibraryCard> pagedResult = libraryCardRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<LibraryCard>();
        }
    }
}
