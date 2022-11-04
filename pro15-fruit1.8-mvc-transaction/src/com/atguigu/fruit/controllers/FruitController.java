package com.atguigu.fruit.controllers;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.dao.impl.FruitDAOImpl;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.fruit.service.FruitService;
import com.atguigu.fruit.service.impl.FruitServiceImpl;
import com.atguigu.myssm.myspringmvc.ViewBaseServlet;
import com.atguigu.myssm.util.StringUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class FruitController {

    private FruitService fruitService = new FruitServiceImpl();


    private String update(Integer fid, String fname, Integer price, Integer fcount, String remark) {
        fruitService.updateFruit(new Fruit(fid, fname, price, fcount, remark));
        return "redirect:fruit.do";
    }

    private String edit(Integer fid, HttpServletRequest req) {
        if (fid != null) {
            Fruit fruit = fruitService.getFruitByFid(fid);
            req.setAttribute("fruit", fruit);
            return "edit";
        }
        return "error";
    }

    private String del(Integer fid) {
        if (fid != null) {
            fruitService.delFruit(fid);
            return "redirect:fruit.do";
        }
        return "error";
    }

    private String toAdd(){
        return "add";
    }

    private String add(String fname, Integer price, Integer fcount, String remark) {
        Fruit fruit = new Fruit(0, fname, price, fcount, remark);
        fruitService.addFruit(fruit);
        return "redirect:fruit.do";
    }

    private String index(String oper, String keyword, Integer pageNo, HttpServletRequest req) {
        HttpSession session = req.getSession();

        if (pageNo == null) {
            pageNo = 1;
        }
        if (StringUtil.isNotEmpty(oper) && "search".equals(oper)) {
            if (StringUtil.isEmpty(keyword)) {
                keyword = "";
            }
            session.setAttribute("keyword", keyword);
        } else {
            Object keywordObj = session.getAttribute("keyword");
            if (keywordObj != null) {
                keyword = (String) keywordObj;
            } else {
                keyword = "";
            }
        }

        session.setAttribute("pageNo", pageNo);
        List<Fruit> fruitList = fruitService.getFruitList(keyword, pageNo);
        session.setAttribute("fruitList", fruitList);

        int pageCount = fruitService.getPageCount(keyword);
//        System.out.println("pageNo = " + pageNo);
//        System.out.println("pageCount = " + pageCount);
        session.setAttribute("pageCount", pageCount);
        return "index";
    }

}
