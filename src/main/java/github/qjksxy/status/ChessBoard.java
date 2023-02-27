package github.qjksxy.status;

import java.util.HashMap;
import java.util.Map;

public class ChessBoard {
    public static final int VACANT_CHESS = 0;
    public static final int BLACK_CHESS = 1;
    public static final int WHITE_CHESS = 2;
    public static final int DEFAULT_ROW = 19;
    public static final int DEFAULT_COL = 19;
    public static final int NOT_YET_WON = 10;
    public static final int BLACK_WINS = 11;
    public static final int WHITE_WINS = 12;
    public static final int ILLEGAL_POS = 0;

    private static final Map<Integer, Integer> chesses = new HashMap<>();
    private static int row = 19;
    private static int col = 19;
    private ChessBoard() {}

    /**
     * 位置合法性检查
     * @param row 行
     * @param col 列
     * @return 是否合法
     */
    public static boolean posIsIegal(int row, int col) {
        return row > 0 && row <= ChessBoard.row &&
                col > 0 && col <= ChessBoard.col;
    }

    /**
     * 位置合法性检查
     * @param pos 位置
     * @return 是否合法
     */
    public static boolean posIsIegal(Integer pos) {
        int row = pos / 100;
        int col = pos % 100;
        return posIsIegal(row, col);
    }

    /**
     * 根据行列生成位置，若行列数据不合法，则返回0
     * @param row 行
     * @param col 列
     * @return 整型坐标值。若行列数据不合法返回 0
     */
    public static Integer pos(int row, int col) {
        if (posIsIegal(row, col)) {
            return 100 * row + col;
        } else {
            return ILLEGAL_POS;
        }
    }

    /**
     * 初始化棋盘，需要指定棋盘行数和列数, 若行数列数不合理，则会设置为默认值
     * @param row 棋盘行数，合理范围为 [9, 99]
     * @param col 棋盘列数, 合理范围为 [9, 99]
     */
    public static void initChessBoard(int row, int col) {
        clearChessBoard();
        if (row > 8 && row < 100) {
            ChessBoard.row = row;
        } else {
            ChessBoard.row = DEFAULT_ROW;
        }
        if (col > 8 && col < 100) {
            ChessBoard.col = col;
        } else {
            ChessBoard.col = DEFAULT_COL;
        }
    }

    /**
     * 获取棋盘指定位置处棋子
     * @param pos 由 pos 函数计算得到
     * @return
     * VACANT_CHESS   无棋子
     * BLACK_CHESS    黑旗
     * WHITE_CHESS    白棋
     */
    public static Integer getChess(Integer pos) {
        if (pos == ILLEGAL_POS) {
            return VACANT_CHESS;
        }
        Integer chess = chesses.get(pos);
        chess = chess == null ? VACANT_CHESS : chess;
        return chess;
    }

    /**
     * 放置棋子
     * @param pos 由 pos 函数计算得到
     * @param chess 棋子
     */
    public static void putChess(Integer pos, Integer chess) {
        if (chess >= 0 && chess <= 2 && posIsIegal(pos)) {
            chesses.put(pos, chess);
        }
    }

    /**
     * 清空棋盘所有棋子
     */
    public static void clearChessBoard() {
        chesses.clear();
    }

    /**
     * 获取棋盘所有位置的棋子状态
     * @return
     */
    public static StringBuilder getChessBoardStatus() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int forRow = 1; forRow <= row; forRow++) {
            for (int forCol = 1; forCol <= col; forCol++) {
                Integer chess = getChess(pos(forRow, forCol));
                stringBuilder.append(chess).append(", ");
            }
            stringBuilder.append('\n');
        }
        return stringBuilder;
    }

    /**
     * 检测棋子落下后是否获胜
     * @param row 棋子行
     * @param col 棋子列
     * @return
     * NOT_YET_WON    尚未获胜
     * BLACK_WINS     黑方获胜
     * WHITE_WINS     白方获胜
     */
    public static int checkHasWin(int row, int col) {
        int chess = getChess(pos(row, col));
        if (chess == 0) {
            return NOT_YET_WON;
        }
        int xLen = 0;
        int yLen = 0;
        int zLen = 0;
        for (int i = - 5; i < 6; i++) {
            if (getChess(pos(row + i, col)) == chess) {
                xLen++;
            } else {
                xLen = 0;
            }
            if (getChess(pos(row, col + i)) == chess) {
                yLen++;
            } else {
                yLen = 0;
            }
            if (getChess(pos(row + i, col + i)) == chess) {
                zLen++;
            } else {
                zLen = 0;
            }
            if (xLen == 6 || yLen == 6 || zLen == 6) {
                return chess == BLACK_CHESS ? BLACK_WINS : WHITE_WINS;
            }
        }
        return NOT_YET_WON;
    }
}
