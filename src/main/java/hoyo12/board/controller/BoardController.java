package hoyo12.board.controller;

import hoyo12.board.dto.BoardDTO;
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
    public String post(@PathVariable("boardId") long id ,  Model model) {
        boardService.updateHits(id);
        BoardDTO find_post  = boardService.findById(id);

        model.addAttribute("board", find_post);

        return "detail";
    }
}
