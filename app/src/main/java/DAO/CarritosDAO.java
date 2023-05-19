package DAO;

public class CarritosDAO {

    private int ID;
    private int IDPRODUCTO;
    private String codigoparental;


    public CarritosDAO(){

    }
    public CarritosDAO(int ID, int IDPRODUCTO, String codigoparental) {
        this.ID = ID;
        this.IDPRODUCTO = IDPRODUCTO;
        this.codigoparental = codigoparental;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDPRODUCTO() {
        return IDPRODUCTO;
    }

    public void setIDPRODUCTO(int IDPRODUCTO) {
        this.IDPRODUCTO = IDPRODUCTO;
    }

    public String getCodigoparental() {
        return codigoparental;
    }

    public void setCodigoparental(String codigoparental) {
        this.codigoparental = codigoparental;
    }
}
