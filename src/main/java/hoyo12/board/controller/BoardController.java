package hoyo12.board.controller;

import hoyo12.board.dto.BoardDTO;
import hoyo12.board.entity.BoardEntity;
import hoyo12.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) {
        log.info("boarDto = {}", boardDTO);
        boardService.save(boardDTO);
        return "index";

    }

    @GetMapping("/list")
    public String findAll(Model model) {
        List<BoardDTO> boardList = boardService.findAll();

        model.addAttribute("boardList", boardList);

        return "list";
    }

    @GetMapping("/{boardId}")
    public String post(@PathVariable Long boardId, Model model) {
        boardService.updateHits(boardId);
        BoardDTO find_post = boardService.findById(boardId);

        model.addAttribute("board", find_post);

        return "detail";
    }

    @GetMapping("/update/{boarId}")
    public String updateForm(@PathVariable Long boarId, Model model) {
        BoardDTO boardDTO = boardService.findById(boarId);
        model.addAttribute("boardUpdate", boardDTO);

        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
        BoardDTO board = boardService.update(boardDTO);
        model.addAttribute("board", board);
        return "detail";

    }
}
