package com.guitArchiveService.guitArchiveService;

import com.azure.storage.blob.*;
import com.guitArchiveService.guitArchiveService.model.Guitar;
import com.guitArchiveService.guitArchiveService.service.GuitarService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.azure.storage.blob.models.BlobHttpHeaders;
import com.azure.storage.blob.models.BlockBlobItem;

import java.util.List;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/guitar")
public class GuitarResource {

    private final GuitarService guitarService;
    public GuitarResource(GuitarService guitarService){
        this.guitarService = guitarService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Guitar>> getAllGuitars(){
        List<Guitar> guitar = guitarService.findAllGuitars();
        return new ResponseEntity<>(guitar, HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Guitar> getAllGuitars(@PathVariable("id") Long id){
        Guitar guitar = guitarService.findGuitar(id);
        return new ResponseEntity<>(guitar, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Guitar> addGuitar(@RequestBody Guitar guitar){
        Guitar newGuitar = guitarService.addGuitar(guitar);
        return new ResponseEntity<>(newGuitar, HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Guitar> updateGuitar(@RequestBody Guitar guitar){
        Guitar updateGuitar = guitarService.updateGuitar(guitar);
        return new ResponseEntity<>(updateGuitar, HttpStatus.OK);
    }
    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGuitar(@PathVariable("id") Long id){
        guitarService.deleteGuitar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/upload")
    @ResponseBody
    public int handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            guitarService.uploadGuitarImage(file);
        }
        return 3;
    }
}