package com.guitArchiveService.guitArchiveService.service;

import com.guitArchiveService.guitArchiveService.exception.GuitarNotFoundException;
import com.guitArchiveService.guitArchiveService.model.Guitar;
import com.guitArchiveService.guitArchiveService.repo.GuitarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuitarService {
    private final GuitarRepo guitarRepo;

    @Autowired
    public GuitarService(GuitarRepo guitarRepo){
        this.guitarRepo = guitarRepo;
    }

    public Guitar addGuitar(Guitar guitar){
        return guitarRepo.save(guitar);
    }

    public List<Guitar> findAllGuitars(){
        return guitarRepo.findAll();
    }

    public Guitar updateGuitar(Guitar guitar){
        return guitarRepo.save(guitar);
    }

    public void deleteGuitar(Long id){
        guitarRepo.deleteGuitarById(id);
    }

    public Guitar findGuitar(Long id){
        return guitarRepo.findGuitarById(id).orElseThrow(()-> new GuitarNotFoundException("Guitar with id: " + id + " was not found."));
    }

}
