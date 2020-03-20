package com.example.shopping.controller;

import com.example.shopping.pojo.Book;
import com.example.shopping.pojo.Search;
import com.example.shopping.service.BookService;
import com.example.shopping.service.impl.PicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author Evan
 * @date 2019/4
 */

@RestController
public class LibraryController {
    @Autowired
    BookService bookService;
    @Autowired
    PicServiceImpl picService;

    @GetMapping("/api/books")
    public List<Book> listBooks() {
        return bookService.list();
    }

    @PostMapping("/api/admin/content/books")
    public Book addOrUpdateBooks(@RequestBody Book book) {
        bookService.addOrUpdate(book);
        return book;
    }

    @PostMapping("/api/admin/content/books/delete")
    public void deleteBook(@RequestBody Book book) {
        bookService.deleteById(book.getId());
    }

    @PostMapping("/api/search")
    public List<Book> searchResult(@RequestBody Search s) {
        if ("".equals(s.getKeywords())) {
            return bookService.list();
        } else {
            return bookService.Search(s.getKeywords());
        }
    }

    @GetMapping("/api/categories/{cid}/books")
    public List<Book> listByCategory(@PathVariable("cid") int cid) {
        if (0 != cid) {
            return bookService.listByCategory(cid);
        } else {
            return listBooks();
        }
    }

    @PostMapping("/api/admin/content/books/covers")
    public String coversUpload(MultipartFile file) {
        try {
//            type = 1,图片上传
            Map<String, String> upload = picService.upload(file,1);
            if ("0".equals(upload.get("error"))){
                return upload.get("url");
            }else {
                return "";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

}
