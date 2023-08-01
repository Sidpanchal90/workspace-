
package com.image.service.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl {

	public String uploadImage(String path, MultipartFile file) throws IOException {
		// file name
		String name = file.getOriginalFilename();
		
		//random id generated for duplicate image eg. randomId+png/jpg
		String randomID = UUID.randomUUID().toString();
		String fileName1 = randomID.concat(name.substring(name.lastIndexOf(".")));

		// full path
		String filePath = path + File.separator + fileName1;

		// creating folder if not creted
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}

		// copy file
		Files.copy(file.getInputStream(), Paths.get(filePath));

		return name;
	}

	/*
	 * @Override public InputStream getResource(String path, String fileName) throws
	 * FileNotFoundException {
	 * 
	 * String fullPath = path + File.separator + fileName; InputStream is = new
	 * FileInputStream(fullPath); return is; }
	 */
}
