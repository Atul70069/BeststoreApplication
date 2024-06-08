package com.lcwd.todo.controllers;


import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;

@RestController
@RequestMapping("/file")
public class FileController {



    Logger logger= LoggerFactory.getLogger(FileController.class);

    @RequestMapping("/single")
    public  String uploadSingle(@RequestParam("image")MultipartFile file)
    {
        logger.info("Name; {}",file.getName());
        logger.info("Name: {}",file.getContentType());
        logger.info("OriginalFile Name; {}",file.getOriginalFilename());
        logger.info("File Size: {}",file.getSize());

//        InputStream inputStream = file.getInputStream();
//        FileOutputStream fileOutputStream= new FileOutputStream("data.png");
//        byte data[]= new byte[inputStream.available()];
//        fileOutputStream.write(data);



        return "FILE TESTING ";
    }



    @PostMapping("/multiple")
    public String uploadMultiple(@RequestParam("files") MultipartFile[] files)
    {
        // HANDLING MULTIPLE FILE
        Arrays.stream(files).forEach(file -> {

            logger.info("File name {}",file.getOriginalFilename());
            logger.info("File type {}",file.getContentType());
            System.out.println("+++++++++++++++++++++++++++++");
        });


        return "Handling multiple files ";
    }

    @GetMapping("/serve-image")
    public  void serveImagehandler(HttpServletResponse response)
    {
        //image

        logger.info("File name: {}",response.getHeaderNames());

        try {
            InputStream inputStream=  new FileInputStream("images/atul.jpeg");
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(inputStream,response.getOutputStream());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }



}
