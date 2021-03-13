package com.company.pieces;

import com.company.gamesetup.Board;
import com.company.gamesetup.Player;

public abstract class Piece {
    // Pieces keep a track of their position on the board, as well as the board itself tracking this position

    private int m_x, m_y;
    private Player m_player;
    private Board m_gameboard;
    private String m_type;
    private int m_timesMoved;

    public Piece(int x, int y, Player player, Board gameboard) {
        this.m_x = x;
        this.m_y = y;
        this.m_player = player;
        this.m_gameboard = gameboard;
        this.m_timesMoved = 0;
    }

    public int getM_x() {
        return m_x;
    }

    public int getM_y() {
        return m_y;
    }

    public Player getM_player() {
        return m_player;
    }

    public Board getM_gameboard() {
        return m_gameboard;
    }

    public String getM_type() {
        return m_type;
    }

    public int getM_timesMoved() {
        return m_timesMoved;
    }

    public void setPosition(int x, int y) {
        this.m_x = x;
        this.m_y = y;
    }

    public void setM_type(String type) {
        this.m_type = type;
    }

    public void setM_gameboard(Board m_gameboard) {
        this.m_gameboard = m_gameboard;
    }

    public void setM_player(Player m_player) {
        this.m_player = m_player;
    }

    public void setM_timesMoved(int m_timesMoved) {
        this.m_timesMoved = m_timesMoved;
    }

    public void incrementM_timesMoved() {
        this.m_timesMoved++;
    }

    public abstract boolean canMove(int x, int y);

    public abstract boolean canCapture(int x, int y);

}
