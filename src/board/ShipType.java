package board;

/**
 *
 * @author Patryk
 */
public enum ShipType {
    Fighter("Fighters"), Cruiser("Cruiser"), Carrier("Carrier");
    
    String textureName;
    
    ShipType (String textureName){
        this.textureName = textureName;
    }
}
