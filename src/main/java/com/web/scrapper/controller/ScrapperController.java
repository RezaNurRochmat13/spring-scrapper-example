package com.web.scrapper.controller;

import com.web.scrapper.utility.ScrapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ScrapperController {
    @Autowired
    ScrapperUtil scrapperUtil;

    @RequestMapping(path = "/scrap/products")
    public void getAllEmployeesInCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"untitled.csv\"");
        scrapperUtil.scrapperProductFromWeb(servletResponse.getWriter());
    }
}
