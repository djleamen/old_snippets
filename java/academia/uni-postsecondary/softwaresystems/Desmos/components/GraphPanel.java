package csci2020u.lab09.components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.LinkedList;
import javax.swing.JPanel;
import csci2020u.lab09.GraphGUI;
import csci2020u.lab09.components.functions.Function;
import csci2020u.lab09.listeners.PointClickListener;

public class GraphPanel extends JPanel {

    private final LinkedList<GraphComponent> components = new LinkedList<>();
    private final GraphGUI gui;

    public GraphPanel(GraphGUI gui) {
        this.gui = gui;
    }

    public Function getFunction() {
        for (GraphComponent c : components) {
            if (c instanceof Function) {
                return (Function) c;
            }
        }
        return null;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Function function = getFunction();
        components.clear();
        CartesianPlane plane = new CartesianPlane(gui);
        setPlane(plane);

        if (function != null) {
            components.add(function);
            HashSet<Point> points = function.getXIntercepts();

            if (function.getFirstDerivative().contains("x")) {
                points.addAll(function.getCriticalPoints());
                if (function.getSecondDerivative().contains("x")) {
                    points.addAll(function.getInflectionPoints());
                }
            }

            components.addAll(points);
            PointClickListener p = (PointClickListener) getMouseListeners()[0];
            p.setPoints(components);
        }

        components.forEach((c) -> {
            c.draw((Graphics2D) graphics);
        });
    }

    public void setFunction(Function function) {
        components.remove(getFunction());
        components.add(function);
    }

    public void setPlane(CartesianPlane plane) {
        components.add(0, plane);
    }
}
