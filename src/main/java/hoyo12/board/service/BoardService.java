package hoyo12.board.service;

import hoyo12.board.boardRepository.BoardRepository;
import hoyo12.board.dto.BoardDTO;
import hoyo12.board.entity.BoardEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;


    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity); // entity 만 받을수 있기 때문에  dbo -> entity
    }

    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        ArrayList boardlist = new ArrayList<>();

        for (BoardEntity boardEntity : boardEntityList) {
            boardlist.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardlist;
    }

    @Transactional
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public BoardDTO findById(long id){

//         BoardEntity boardEntity =  boardRepository.findById(id).orElse(null);
//
//         if (boardEntity != null) {
//             BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
//             return  boardDTO;
//         }else{return null;}
//

        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
            return boardDTO;
        }else{
            return  null;
        }

    }

}
