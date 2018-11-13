package com.example.guanghuili.checkesandchess.Checkers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Checker implements Serializable {
    private int row;
    private int column;
    private boolean crownStatus;

    public Checker(){

    }

    public Checker(int row, int column){
        this.row = row;
        this.column = column;
        this.crownStatus = false;
    }

    public Checker(Checker checker){
        this.row = checker.getRow();
        this.column = checker.getColumn();
        this.crownStatus = checker.isCrownStatus();
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isCrownStatus() {
        return crownStatus;
    }

    public void setCrownStatus(boolean crownStatus) {
        this.crownStatus = crownStatus;
    }

    public ArrayList<int[]> getMove(List<List<Checker>> checkerList) {
        return null;
    }

    public ArrayList<int[]> getMove2(List<List<Checker>> checkerList) {
        return null;
    }

    public ArrayList<int[]> getKillList() {
        return null;
    }
}