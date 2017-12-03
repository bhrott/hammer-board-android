package br.com.bhr.hammerboard.ui.viewboard;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.bhr.hammerboard.R;
import br.com.bhr.hammerboard.core.ActionResult;
import br.com.bhr.hammerboard.domain.board.BoardCardEntity;
import br.com.bhr.hammerboard.domain.board.BoardEntity;
import br.com.bhr.hammerboard.domain.board.BoardException;
import br.com.bhr.hammerboard.domain.board.viewboard.BoardSectionModel;
import br.com.bhr.hammerboard.ui.newboard.NewBoardFormFragment;
import br.com.bhr.hammerboard.ui.viewboard.cardsectionlist.ViewSectionCardListFragment;

public class ViewBoardActivity extends AppCompatActivity {

    private ViewBoardSectionsFragment viewBoardSectionsFragment;
    private BoardEntity boardEntity;

    private ActionResult<BoardException, BoardSectionModel> onSelectedSectionChangedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_board);

        this.boardEntity = ViewBoardManager.getInstance().getBoard();

        this.setTitle(this.boardEntity.getName());

        this.setupListeners();

        this.renderBoardSectionList();
    }

    @Override
    protected void onDestroy() {
        ViewBoardManager.getInstance().removeOnSelectedSectionChangeListener(this.onSelectedSectionChangedListener);

        super.onDestroy();
    }

    private void setupListeners() {
        this.onSelectedSectionChangedListener = new ActionResult<BoardException, BoardSectionModel>() {
            @Override
            public void onSuccess(BoardSectionModel result) {
                renderCardListForSection(result);
            }

            @Override
            public void onError(BoardException e) {
                Toast.makeText(ViewBoardActivity.this, "Oops! The section couldn't be loaded =(.", Toast.LENGTH_SHORT).show();
            }
        };

        ViewBoardManager.getInstance().addOnSelectedSectionChangeListener(this.onSelectedSectionChangedListener);
    }

    private void renderBoardSectionList() {
        FragmentManager fm = getSupportFragmentManager();

        this.viewBoardSectionsFragment = new ViewBoardSectionsFragment();

        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment_content, this.viewBoardSectionsFragment);
        ft.commit();
    }

    private void renderCardListForSection(BoardSectionModel section) {
        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_content, new ViewSectionCardListFragment());
        ft.commit();
    }
}
