package edu.hw1;

public class Task8 {
    public static void main(String[] args) {
        int[][] board = new int[][] {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
        };
        System.out.println(knightBoardCapture(board));
    }

    public static boolean knightBoardCapture(int[][] board) {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                if (board[i][j] != 0) {
                    if (i > 0) {
                        if (j > 1) {
                            if (board[i - 1][j - 2] == 1) {
                                return false;
                            }
                        }
                        if (j < 6) {
                            if (board[i - 1][j + 2] == 1) {
                                return false;
                            }
                        }
                        if (i > 1) {
                            if (j > 0) {
                                if (board[i - 2][j - 1] == 1) {
                                    return false;
                                }
                            }
                            if (j < 7) {
                                if (board[i - 2][j + 1] == 1) {
                                    return false;
                                }
                            }
                        }
                    }
                    if (i < 7) {
                        if (j > 1) {
                            if (board[i + 1][j - 2] == 1) {
                                return false;
                            }
                        }
                        if (j < 6) {
                            if (board[i + 1][j + 2] == 1) {
                                return false;
                            }
                        }
                        if (i < 6) {
                            if (j > 0) {
                                if (board[i + 2][j - 1] == 1) {
                                    return false;
                                }
                            }
                            if (j < 7) {
                                if (board[i + 2][j + 1] == 1) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
