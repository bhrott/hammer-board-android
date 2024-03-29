package br.com.bhr.hammerboard.core;

import br.com.bhr.hammerboard.domain.board.BoardCardRepository;
import br.com.bhr.hammerboard.domain.board.BoardRepository;
import br.com.bhr.hammerboard.domain.board.joinboard.JoinBoardService;
import br.com.bhr.hammerboard.domain.board.newboard.NewBoardService;
import br.com.bhr.hammerboard.domain.board.viewboard.ViewBoardService;
import br.com.bhr.hammerboard.domain.board.viewboard.card.BoardCardService;
import br.com.bhr.hammerboard.infra.repository.firebase.BoardCardRepositoryFIR;
import br.com.bhr.hammerboard.infra.repository.firebase.BoardRepositoryFIR;
import br.com.bhr.hammerboard.infra.repository.mock.BoardCardRepositoryMock;
import br.com.bhr.hammerboard.infra.repository.mock.BoardRepositoryMock;

/**
 * Created by ben on 28/11/2017.
 */

public class DependencyManager {
    private static DependencyManager instance;
    public static DependencyManager getInstance() {
        if (instance == null) {
            instance = new DependencyManager();
        }

        return  instance;
    }

    private BoardRepository boardRepository;
    private BoardRepository getBoardRepository() {
        if (this.boardRepository == null) {
            this.boardRepository = new BoardRepositoryFIR();
        }

        return this.boardRepository;
    }

    private BoardCardRepository boardCardRepository;
    private BoardCardRepository getBoardCardRepository() {
        if (this.boardCardRepository == null) {
            this.boardCardRepository = new BoardCardRepositoryFIR();
        }

        return  this.boardCardRepository;
    }

    private NewBoardService newBoardService;
    public NewBoardService getNewBoardService() {
        if (this.newBoardService == null) {
            this.newBoardService = new NewBoardService(this.getBoardRepository());
        }

        return this.newBoardService;
    }

    private JoinBoardService joinBoardService;
    public JoinBoardService getJoinBoardService() {
        if (this.joinBoardService == null) {
            this.joinBoardService = new JoinBoardService(this.getBoardRepository());
        }

        return this.joinBoardService;
    }

    private ViewBoardService viewBoardService;
    public ViewBoardService getViewBoardService() {
        if (this.viewBoardService == null) {
            this.viewBoardService = new ViewBoardService(this.getBoardCardRepository());
        }

        return this.viewBoardService;
    }

    private BoardCardService boardCardService;
    public BoardCardService getBoardCardService() {
        if (this.boardCardService == null) {
            this.boardCardService = new BoardCardService(this.getBoardCardRepository());
        }

        return this.boardCardService;
    }
}
