package com.fastcampus.ch4.controller;

import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.domain.PageHandler;
import com.fastcampus.ch4.domain.SearchCondition;
import com.fastcampus.ch4.service.BoardService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;

    @PostMapping("/modify")
    public String modify(BoardDto boardDto, SearchCondition sc, HttpSession session, Model m, RedirectAttributes rattr) {
        String writer = (String) session.getAttribute("id");
        boardDto.setWriter(writer);

        try {
            // redirect로 paramater 전달 시 RedirectAttributes 에 담아서 사용 model에 담지 말자
//            m.addAttribute("page", page); (X) RedirectAttributes와 동시 사용 안됨
//            rattr.addAttribute("page", sc.getPage());
//            rattr.addAttribute("pageSize", sc.getPageSize());
            int rowCont = boardService.modify(boardDto); // insert

            if (rowCont != 1) {
                throw new Exception("Modify failed");
            }

            rattr.addFlashAttribute("msg", "MOD_OK");

        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute(boardDto); // 예외 발생 시 작성한 내용 전달
            rattr.addFlashAttribute("msg", "MOD_ERR");
            return "board";
        }
        return "redirect:/board/list" + sc.getQueryString();
    }

    @GetMapping("/write")
    public String write(Model m) {
        m.addAttribute("mode", "new");
        return "board"; // 읽기와 쓰기에 사용, 쓰기에 사용할때는 mode = new
    }

    @PostMapping("/write")
    public String write(BoardDto boardDto,HttpSession session, Model m, RedirectAttributes rattr) {
        String writer = (String) session.getAttribute("id");
        boardDto.setWriter(writer);

        try {
            int rowCont = boardService.write(boardDto); // insert

            if (rowCont != 1) {
                throw new Exception("Write failed");
            }

            rattr.addFlashAttribute("msg", "WRT_OK");

            return "redirect:/board/list";
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute(boardDto); // 예외 발생 시 작성한 내용 전달
            rattr.addFlashAttribute("msg", "WRT_ERR");
            return "board";
        }

    }

    @PostMapping("/remove")
    public String remove(Integer bno, SearchCondition sc, HttpSession session, Model m, RedirectAttributes rattr) {
        String writer = (String)session.getAttribute("id");

        try {
            int rowCont = boardService.remove(bno, writer);

            if (rowCont != 1) {
                throw new Exception("board remove error");
            }
            //rattr.addFlashAttribute session을 이용 한번만 쓰고 없어짐(일회성)
            rattr.addFlashAttribute("msg", "DEL_OK");
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "DEL_ERR");
        }

        return "redirect:/board/list" + sc.getQueryString();
    }

    @GetMapping("/read")
    public String read(Integer bno, SearchCondition sc, Model m) {
        try {
            BoardDto boardDto = boardService.read(bno);
//            m.addAttribute("boardDto", boardDto); - 아래 문장과 동일
            m.addAttribute(boardDto);
            m.addAttribute("page", sc.getPage());
            m.addAttribute("pageSize", sc.getPageSize());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "board";
    }

    @GetMapping("/list")//@ModelAttribute 생략가능
    public String list(@ModelAttribute SearchCondition sc, Model m, HttpServletRequest request) {
        if (!loginCheck(request))
            return "redirect:/login/login?toURL=" + request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동

        try {
            int totalCnt = boardService.getSearchResultCnt(sc);
            m.addAttribute("totalCnt", totalCnt);

            PageHandler ph = new PageHandler(totalCnt, sc);

            List<BoardDto> list = boardService.getSearchResultPage(sc);
            m.addAttribute("list", list);
            m.addAttribute("ph", ph);


        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "List_ERR");
            m.addAttribute("totalCnt", 0);
        }


        return "boardList"; // 로그인을 한 상태이면, 게시판 화면으로 이동
    }

    private boolean loginCheck(HttpServletRequest request) {
        // 1. 세션을 얻어서
        HttpSession session = request.getSession();
        // 2. 세션에 id가 있는지 확인, 있으면 true를 반환
        return session.getAttribute("id") != null;
    }
}
