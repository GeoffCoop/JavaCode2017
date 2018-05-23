package cs2410.control;

import cs2410.view.MineCell;
import cs2410.view.Scoreboard;
import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * Created by User on 11/30/2016.
 */
public class Controller {

    public Image flag = new Image("/cs2410/images/flag.jpg");
    public Image unknown = new Image("/cs2410/images/qm.jpg");
    public ArrayList<MineCell> cells;
    public boolean start = false;
    int width,height;
    public Scoreboard scoreboard;

    public Controller(int passedWidth, int passedHeight)
    {
        width = passedWidth; height = passedHeight;
        newBoard();
    }

    public void newBoard()
    {
        cells = new ArrayList<MineCell>();
        for(int i = 0; i < width*height; ++i )
        {
            if(i < width*height/4)  cells.add(i, new MineCell(true, cells, this));
            else cells.add(i, new MineCell(cells, this));
        }
        MineCell.lost = false;
        //scoreboard.time = 0;
    }

    public void setScoreboard(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }
}
