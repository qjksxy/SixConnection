package github.qjksxy.status;

import org.junit.Test;

public class ChessBoardTest {
    @Test
    public void test() {
        ChessBoard.initChessBoard(9, 9);
        ChessBoard.putChess(ChessBoard.pos(10, 10), ChessBoard.WHITE_CHESS);
        ChessBoard.putChess(ChessBoard.pos(19, 11), ChessBoard.BLACK_CHESS);
        ChessBoard.putChess(ChessBoard.pos(15, 10), ChessBoard.WHITE_CHESS);
        ChessBoard.putChess(ChessBoard.pos(15, 12), ChessBoard.BLACK_CHESS);
        ChessBoard.putChess(ChessBoard.pos(10, 9), ChessBoard.WHITE_CHESS);
        ChessBoard.putChess(ChessBoard.pos(6, 9), ChessBoard.BLACK_CHESS);
        ChessBoard.putChess(ChessBoard.pos(7, 4), ChessBoard.WHITE_CHESS);
        ChessBoard.putChess(ChessBoard.pos(9, 7), ChessBoard.BLACK_CHESS);
        ChessBoard.putChess(ChessBoard.pos(12, 12), ChessBoard.WHITE_CHESS);
        ChessBoard.putChess(ChessBoard.pos(14, 10), ChessBoard.BLACK_CHESS);
        ChessBoard.putChess(ChessBoard.pos(10, 11), ChessBoard.WHITE_CHESS);
        ChessBoard.putChess(ChessBoard.pos(10, 12), ChessBoard.WHITE_CHESS);
        ChessBoard.putChess(ChessBoard.pos(10, 13), ChessBoard.WHITE_CHESS);
        ChessBoard.putChess(ChessBoard.pos(10, 14), ChessBoard.WHITE_CHESS);

        long time1 = System.currentTimeMillis();
        System.out.println(ChessBoard.getChessBoardStatus());
        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
        System.out.println(ChessBoard.checkHasWin(10, 14));
    }

    @Test
    public void test2() {
        ChessBoard.initChessBoard(19, 19);
        System.out.println(ChessBoard.getChess(0));
    }
}
