package com.guitArchiveService.guitArchiveService.service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.guitArchiveService.guitArchiveService.exception.GuitarNotFoundException;
import com.guitArchiveService.guitArchiveService.model.Guitar;
import com.guitArchiveService.guitArchiveService.repo.GuitarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class GuitarService {
    private final GuitarRepo guitarRepo;

    @Value("${azure.storage.account-name}")
    private String azureAccountName;
    @Value("${azure.storage.account-key}")
    private String azureAccountKey;

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

    public int uploadGuitarImage(MultipartFile file){
        try{
            String constr = "AccountName=" + azureAccountName +"; AccountKey=" + azureAccountKey + "; EndpointSuffix=core.windows.net; DefaultEndpointsProtocol=https;";

            BlobContainerClient container = new BlobContainerClientBuilder()
                    .connectionString(constr)
                    .containerName("guitarchivecontainer")
                    .buildClient();

            BlobClient blob = container.getBlobClient(file.getOriginalFilename());

            BlobHttpHeaders headers = new BlobHttpHeaders().setContentType("image");
            blob.upload(file.getInputStream(), file.getSize(), true);
            blob.setHttpHeaders(headers);

            return 1;
        } catch (IOException e) {
            return 2;
        }
    }
}
