package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/image")
public class ImageController {
	
	public final String storageDirectoryPath = "../../../images";
	
	@PostMapping(value = "/upload")
	public ResponseEntity<?> upload(@RequestParam MultipartFile file) throws IOException{
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Path storageDirectory = Paths.get(storageDirectoryPath);
		if(!Files.exists(storageDirectory)){ // if the folder does not exist
			try {
			    Files.createDirectories(storageDirectory); // we create the directory in the given storage directory path
			}catch (Exception e){
			    e.printStackTrace();// print the exception
			    }
		}
		Path destination = Paths.get(storageDirectory.toString() + "/" + fileName);
		try {
			Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);// we are Copying all bytes from an input stream to a file
		
	    } catch (IOException e) {
		        e.printStackTrace();
		}
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/image/getImage/")
                .path(fileName)
                .toUriString();
		
//		Image dbImage = new Image();
//    	dbImage.setName(file.getOriginalFilename());
//    	dbImage.setContent(file.getBytes());
//    	imageRepo.save(dbImage);    	
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(fileDownloadUri);	
	}
		
	@GetMapping(
            value = "/getImage/{fileName:.+}",
            produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_GIF_VALUE,MediaType.IMAGE_PNG_VALUE}
    )
	public  byte[] getImageWithMediaType(@PathVariable final String fileName) throws IOException {
        Path destination =   Paths.get(storageDirectoryPath+"/"+fileName);// retrieve the image by its name
        return IOUtils.toByteArray(destination.toUri());
    }
}
