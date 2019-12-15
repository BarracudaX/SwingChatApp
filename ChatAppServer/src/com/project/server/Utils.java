package com.project.server;

import javax.swing.*;
import java.awt.*;

public class Utils {

    public enum Fill {
        BOTH(GridBagConstraints.BOTH),HORIZONTAL(GridBagConstraints.HORIZONTAL);

        private final int value;


        Fill(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

    public enum Anchor{
        CENTER(GridBagConstraints.CENTER);

        private final int value;

        Anchor(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static class GridBagConstraintBuilder {

        private int gridx = 0;
        private int gridy = 0;
        private int weightx = 0;
        private int weighty = 0;
        private Insets insets = new Insets(0,0,0,0);
        private int anchor = GridBagConstraints.CENTER;
        private int fill = GridBagConstraints.NONE;
        private int gridWidth = 1;
        private int gridHeight = 1;

        public GridBagConstraintBuilder() {

        }

        public GridBagConstraintBuilder setColumn(int column) {
            this.gridx = column;
            return this;
        }

        public GridBagConstraintBuilder setRow(int row) {
            this.gridy = row;
            return this;
        }

        public GridBagConstraintBuilder setColumnWeight(int columnWeight) {
            this.weightx = columnWeight;
            return this;
        }

        public GridBagConstraintBuilder setRowWeight(int rowWeight) {
            this.weighty = rowWeight;
            return this;
        }

        public GridBagConstraintBuilder setInsets(Insets insets) {
            this.insets = new Insets(insets.top, insets.left, insets.bottom, insets.right);
            return this;
        }

        public GridBagConstraintBuilder setAnchor(Anchor anchor) {
            this.anchor = anchor.getValue();
            return this;
        }

        public GridBagConstraintBuilder setFill(Fill fill) {
            this.fill = fill.getValue();
            return this;
        }

        public GridBagConstraintBuilder setColumnWidth(int columnWidth) {
            this.gridWidth = columnWidth;
            return this;
        }

        public GridBagConstraintBuilder setRowWidth(int rowWidth) {
            this.gridHeight = rowWidth;
            return this;
        }

        public GridBagConstraintBuilder reset(){
            gridx = 0;
            gridy = 0;
            weightx = 0;
            weighty = 0;
            insets = new Insets(0,0,0,0);
            anchor = GridBagConstraints.CENTER;
            fill = GridBagConstraints.NONE;
            gridWidth = 1;
            gridHeight = 1;

            return this;
        }

        public GridBagConstraints build(){
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = gridx;
            constraints.gridy = gridy;
            constraints.fill = fill;
            constraints.anchor = anchor;
            constraints.weighty = weighty;
            constraints.weightx = weightx;
            constraints.insets = insets;
            constraints.gridwidth = gridWidth;
            constraints.gridheight = gridHeight;

            return constraints;
        }
    }

}
