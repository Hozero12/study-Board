package hoyo12.board.service;

import hoyo12.board.boardRepository.BoardRepository;
import hoyo12.board.dto.BoardDTO;
import hoyo12.board.entity.BoardEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;


    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity); // entity 만 받을수 있기 때문에  dbo -> entity
    }

}