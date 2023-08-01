package com.image.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.image.imageEntity.ImageDataEntity;
import com.image.service.serviceImpl.FileServiceImpl;

@RestController
@RequestMapping("/file")
public class FileController {

	/*
	 * @Autowired private ImageDataRepository imageDataRepository;
	 */
	@Autowired
	private FileServiceImpl fileService;

	@Value("${project.image}")
	private String path;

	@PostMapping("/upload")
	public ResponseEntity<ImageDataEntity> fileUpload(@RequestParam("image") MultipartFile image) {
		String fileName = null;
		try {
			fileName = this.fileService.uploadImage(path, image);
			System.out.println(fileName);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ImageDataEntity(null, "image is not uploadeed due to error on server !!"),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return new ResponseEntity<>(new ImageDataEntity(fileName, "image is successfully uploded"), HttpStatus.OK);

	}

}


// @GetMapping("/images/{id}")

/*
 * public ResponseEntity<byte[]> getImageById(@PathVariable Long id) {
 * Optional<ImageDataEntity> imageOptional = imageDataRepository.findById(id);
 * 
 * if (imageOptional.isPresent()) { ImageDataEntity imageEntity =
 * imageOptional.get(); return
 * ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)// Adjust content type
 * // based on image // type .body(imageEntity.getimageData()); } else { return
 * ResponseEntity.notFound().build(); }
 */

/*
 * public List<?> getAllImages() { List<ImageDataEntity> findAll =
 * fileDataRepository.findAll(); return findAll;
 */
