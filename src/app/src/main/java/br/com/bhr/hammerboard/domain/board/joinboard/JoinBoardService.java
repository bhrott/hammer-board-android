package br.com.bhr.hammerboard.domain.board.joinboard;

import br.com.bhr.hammerboard.core.ActionResult;
import br.com.bhr.hammerboard.domain.board.BoardEntity;
import br.com.bhr.hammerboard.domain.board.BoardException;
import br.com.bhr.hammerboard.domain.board.BoardRepository;

/**
 * Created by ben on 29/11/2017.
 */

public class JoinBoardService {
    private BoardRepository boardRepository;

    public JoinBoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void joinWithShareCode(String shareCode, final ActionResult<BoardException, BoardEntity> actionResult) {
        if (shareCode == null || shareCode.isEmpty()) {
            actionResult.onError(new BoardException("The share code is required."));
            return;
        }

        this.boardRepository.getByShareCode(shareCode, new ActionResult<BoardException, BoardEntity>() {
            @Override
            public void onSuccess(BoardEntity result) {
                if (result == null) {
                    actionResult.onError(new BoardException("This board doesn't exist."));
                    return;
                }

                actionResult.onSuccess(result);
            }

            @Override
            public void onError(BoardException e) {
                actionResult.onError(e);
            }
        });
    }
}
