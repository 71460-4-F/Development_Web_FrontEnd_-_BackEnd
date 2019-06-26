package com.trabalho.br.util;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class ImageUtil {

    public static void saveImage(String caminho, @RequestParam(value = "imagem") MultipartFile image) {

        File file = new File(caminho);
        try {
            FileUtils.writeByteArrayToFile(file, image.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void deleteImage(String caminho) {

        File file = new File(caminho);

        file.delete();

    }

}
