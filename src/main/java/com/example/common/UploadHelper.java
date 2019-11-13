package com.example.common;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class UploadHelper {
	
	private File uploadTemporaryDirectory;
	
	public String saveTemporaryFile(MultipartFile multipartFile) throws IOException {
		String uploadTemporaryFileId = UUID.randomUUID().toString();
		File uploadTemporaryFile = new File(uploadTemporaryDirectory, uploadTemporaryFileId);
		FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), uploadTemporaryFile);
		return uploadTemporaryFileId;
	}

}
