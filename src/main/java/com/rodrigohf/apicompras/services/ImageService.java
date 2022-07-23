package com.rodrigohf.apicompras.services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ImageService {
	
	public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) throws FileNotFoundException    {
		String ext = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
		if(!ext.equalsIgnoreCase("png") && !ext.equalsIgnoreCase("jpg")) {
			throw new FileNotFoundException("Somente Imagens PNG e JPG são permitidas!");
		}
		try {
			BufferedImage img = ImageIO.read(uploadedFile.getInputStream());
			if(!img.equals("jpg")) {
				img = pngToJpg(img);
			}
			return img;
		} catch (IOException e) {
			throw new FileNotFoundException("Erro ao ler arquivo!");
		}
		
		
	}

	public BufferedImage pngToJpg(BufferedImage img) {
		BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(),BufferedImage.TYPE_INT_RGB);
		jpgImage.createGraphics().drawImage(img,0,0,Color.WHITE,null);
		return jpgImage;
	}
	
	public InputStream getInputStream(BufferedImage img, String extension) throws FileNotFoundException {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(img, extension, os);
			return new ByteArrayInputStream(os.toByteArray());
		}
		catch(IOException e) {
			throw new FileNotFoundException("Erro ao ler arquivo!");
		}
		
		
	}

}
