/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships;

/**
 *
 * @author Patryk
 */
public class Crusier extends Ship{
    
    static int maxHealth = 2;
    static int movementRange = 2;
    static int weaponsRange = 2;
    static int damage = 1;
    
    public Crusier(boolean computer) {
        super(computer, Crusier.maxHealth, Crusier.damage);
    }
}
