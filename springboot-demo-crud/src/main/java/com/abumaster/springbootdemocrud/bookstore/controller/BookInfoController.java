package com.abumaster.springbootdemocrud.bookstore.controller;

import com.abumaster.springbootdemocrud.bookstore.domain.BookInfo;
import com.abumaster.springbootdemocrud.bookstore.service.BookInfoService;
import com.abumaster.springbootdemocrud.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@Api(tags = "测试")
public class BookInfoController {
    @Autowired
    BookInfoService bookInfoService;

    @GetMapping("/books")
    @ApiOperation("查询列表")
    public Response queryAllBookInfo(){
        List<BookInfo> bookInfos = bookInfoService.queryBookInfoList();
        return Response.ok(bookInfos);
    }


}
