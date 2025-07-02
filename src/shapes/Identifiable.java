/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package shapes;

import java.awt.Shape;

/**
 *
 * @author alejandro
 */
public interface Identifiable extends Shape{
    public abstract int getID();
    @Override
    public abstract String toString();
}
