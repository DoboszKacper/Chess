package com.dobosz.chess.entieties;

public enum FigureColor {
    WHITE(" w"), BLACK(" b"), NONE("----");

    String shortColor;

    FigureColor(String shortColor) {
        this.shortColor = shortColor;
    }

    public String getShortColor() {
        return shortColor;
    }
}
