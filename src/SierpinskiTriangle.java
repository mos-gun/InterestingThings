import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public class SierpinskiTriangle extends JPanel {

    private static Point2D pointA;
    private static Point2D pointB;
    private static Point2D pointC;
    private static Point2D pointRandom;
    private static Point2D pointRandomOld;
    private static Random randomGenerator;
    private static ArrayList<Point2D> listPoints;
    private boolean firstPointDone;

    public SierpinskiTriangle() {
        JFrame frame = new JFrame();
        frame.getContentPane().add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(300,50);
        frame.setSize(1000, 700);
        frame.setBackground(Color.BLACK);
        frame.setVisible(true);
        listPoints = new ArrayList<>();
        firstPointDone = false;
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_SPACE) {
                    for (int i = 0; i < 100; i++) {
//                    System.out.println("SPACE pressed");
                        randomGenerator = new Random();
                        pointRandomOld = pointRandom;
                        switch (randomGenerator.nextInt(1,4)) {
                            case 1 -> {
//                            System.out.println("1 pressed: Edge A");
                                pointRandom = new Point(((int) pointA.getX() + (int) pointRandomOld.getX()) / 2, ((int) pointA.getY() + (int) pointRandomOld.getY()) / 2);
//                            System.out.println("Ax (" + (int) pointA.getX() + ") + Pointx (" + (int) pointRandomOld.getX() + ") /2 = " + ((int) pointA.getX() + (int) pointRandomOld.getX()) / 2);
//                            System.out.println("Ay (" + (int) pointA.getY() + ") + Pointy (" + (int) pointRandomOld.getY() + ") /2 = " + ((int) pointA.getY() + (int) pointRandomOld.getY()) / 2);
                            }
                            case 2 -> {
//                            System.out.println("2 pressed: Edge B");
                                pointRandom = new Point(((int) pointB.getX() + (int) pointRandomOld.getX()) / 2, ((int) pointB.getY() + (int) pointRandomOld.getY()) / 2);
//                            System.out.println("Bx (" + (int) pointB.getX() + ") + Pointx (" + (int) pointRandomOld.getX() + ") /2 = " + ((int) pointB.getX() + (int) pointRandomOld.getX()) / 2);
//                            System.out.println("By (" + (int) pointB.getY() + ") + Pointy (" + (int) pointRandomOld.getY() + ") /2 = " + ((int) pointB.getY() + (int) pointRandomOld.getY()) / 2);
                            }
                            case 3 -> {
//                            System.out.println("3 pressed: Edge C");
                                pointRandom = new Point(((int) pointC.getX() + (int) pointRandomOld.getX()) / 2, ((int) pointC.getY() + (int) pointRandomOld.getY()) / 2);
//                            System.out.println("Cx (" + (int) pointC.getX() + ") + Pointx (" + (int) pointRandomOld.getX() + ") /2 = " + ((int) pointC.getX() + (int) pointRandomOld.getX()) / 2);
//                            System.out.println("Cy (" + (int) pointC.getY() + ") + Pointy (" + (int) pointRandomOld.getY() + ") /2 = " + ((int) pointC.getY() + (int) pointRandomOld.getY()) / 2);
                            }
                            default -> {
                            }
                        }
                        listPoints.add(pointRandom);
                        repaint();
//                    System.out.println("second");
                        System.out.println("anzahl points: " + listPoints.size());
//                    for(Point2D point : listPoints) {
//                        System.out.println(point);
//                    }
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        new SierpinskiTriangle();
    }

    public void paint(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        pointA = new Point(500, 50);
        pointB = new Point(150, 600);
        pointC = new Point(850, 600);
        Polygon polyTriangle = new Polygon(new int[]{(int) pointA.getX(), (int) pointB.getX(), (int) pointC.getX()}, new int[]{(int) pointA.getY(), (int) pointB.getY(), (int) pointC.getY()}, 3);
        graphics.drawPolygon(polyTriangle);
        graphics.drawString("A", (int) pointA.getX()-4, (int) pointA.getY()-10);
        graphics.drawString("B", (int) pointB.getX()-20, (int) pointB.getY()+4);
        graphics.drawString("C", (int) pointC.getX()+12, (int) pointC.getY()+4);

        // random punkt innerhalb des dreiecks finden und zeichnen
        if (!firstPointDone) {
            pointRandom = getRandomPoint(polyTriangle);
            listPoints.add(pointRandom);
            System.out.println("first point: " + pointRandom);
            firstPointDone = true;
        }
        drawAllPoints(graphics);

    }

    public Point2D getRandomPoint(Polygon polyTriangle) {
        randomGenerator = new Random();
        Point2D point = new Point(randomGenerator.nextInt(150,850),randomGenerator.nextInt(50,600));
        while (!polyTriangle.contains(point)) {
            point = new Point(randomGenerator.nextInt(150,850),randomGenerator.nextInt(50,600));
        }
        return point;
    }

    public void drawPoint(Graphics graphics, Point2D point) {
        graphics.drawArc((int) point.getX(), (int) point.getY(), 1, 1, 0, 360);
    }

    public void drawAllPoints(Graphics graphics) {
        for (Point2D listPoint : listPoints) {
            drawPoint(graphics, listPoint);
        }
    }
}