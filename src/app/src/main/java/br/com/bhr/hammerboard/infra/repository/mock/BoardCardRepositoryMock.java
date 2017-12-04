package br.com.bhr.hammerboard.infra.repository.mock;

import java.util.ArrayList;

import br.com.bhr.hammerboard.core.ActionResult;
import br.com.bhr.hammerboard.domain.board.BoardCardEntity;
import br.com.bhr.hammerboard.domain.board.BoardCardRepository;
import br.com.bhr.hammerboard.domain.board.BoardException;

/**
 * Created by ben on 02/12/2017.
 */

public class BoardCardRepositoryMock implements BoardCardRepository {
    @Override
    public void getCardsForSection(String sectionId, ActionResult<BoardException, ArrayList<BoardCardEntity>> actionResult) {
        ArrayList<BoardCardEntity> list = new ArrayList<>();

        if (sectionId.equals("tools")) {
            list.add(new BoardCardEntity("1", "This is a mocked card, you cant remove me, but try and if you can, i give you a gift."));
            list.add(new BoardCardEntity("2", "This is a mocked card, you cant remove me, but try and if you can, i give you a gift."));
            list.add(new BoardCardEntity("3", "This is a mocked card, you cant remove me, but try and if you can, i give you a gift."));
        }

        actionResult.onSuccess(list);
    }
}