package com.rodrigohf.apicompras.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class S3Service {

	@Autowired
	private AmazonS3 s3Client;

	@Value("${s3.bucket}")
	private String bucketName;

	private static Logger LOG = LoggerFactory.getLogger(S3Service.class);

	// método que faz um upload que passa o caminho como argumento la no S3Bucket
	public URI uploadFile(MultipartFile multipartFile) {
		try {
		String fileName = multipartFile.getOriginalFilename();
		InputStream is = multipartFile.getInputStream();
		String contentTypeFile = multipartFile.getContentType();

		return uploadFile(is, fileName, contentTypeFile);
		
		} catch (IOException e) {
			
			throw new RuntimeException("Erro de IO "+e.getMessage());
		}
		

	}

	public URI uploadFile(InputStream is, String fileName, String contentTypeFile) {
		try {
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(contentTypeFile);
			LOG.info("Iniciando upload...");
			s3Client.putObject(bucketName, fileName, is, meta);
			LOG.info("Upload finalizado!");

			return s3Client.getUrl(bucketName, fileName).toURI();
		} catch (URISyntaxException e) {
			
			throw new RuntimeException("Erro ao converter URL para URI");
	}

	}
}
