package DAO;

import java.io.Serializable;

public class PadreDAO implements Serializable {

    private String correo;
    private String pass;

   private String nom;
    private String apellidop;

    private String apellidom;

    public String getCodigoarental() {
        return codigoarental;
    }

    public void setCodigoarental(String codigoarental) {
        this.codigoarental = codigoarental;
    }

    private String codigoarental;


    public String getCorreo() {
        return correo;
    }
    public PadreDAO(){

    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getApellidop() {
        return apellidop;
    }

    public void setApellidop(String apellidop) {
        this.apellidop = apellidop;
    }

    public String getApellidom() {
        return apellidom;
    }

    public void setApellidom(String apellidom) {
        this.apellidom = apellidom;
    }



}
