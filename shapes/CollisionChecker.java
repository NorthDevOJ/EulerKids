/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shapes;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

/**
 *
 * @author alejandro
 */
public class CollisionChecker {

    public static boolean polygonIntersectsRectangle(Polygon polygon, Rectangle rectangle) {
        // Check if any of the polygon's vertices are inside the rectangle
        for (int i = 0; i < polygon.npoints; i++) {
            if (rectangle.contains(polygon.xpoints[i], polygon.ypoints[i])) {
                return true;
            }
        }

        // Check if any of the rectangle's vertices are inside the polygon
        for (int i = 0; i < 4; i++) {
            if (polygon.contains(rectangle.x + i, rectangle.y + i)) {
                return true;
            }
        }

        // If none of the vertices are inside, check if any polygon edges intersect with rectangle edges
        for (int i = 0; i < polygon.npoints; i++) {
            int x1 = polygon.xpoints[i];
            int y1 = polygon.ypoints[i];
            int x2 = polygon.xpoints[(i + 1) % polygon.npoints];
            int y2 = polygon.ypoints[(i + 1) % polygon.npoints];

            if (lineIntersectsRectangle(x1, y1, x2, y2, rectangle)) {
                return true;
            }
        }

        return false;
    }

    private static boolean lineIntersectsRectangle(int x1, int y1, int x2, int y2, Rectangle rectangle) {
        // Check if the line intersects any side of the rectangle
        Line2D line = new Line2D.Double(x1, y1, x2, y2);
        return line.intersects(rectangle);
    }
}

