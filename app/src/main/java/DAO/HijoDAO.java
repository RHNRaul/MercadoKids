package DAO;

import java.io.Serializable;

public class HijoDAO implements Serializable {
   private String correo;
   private String pass;

   private String CodigoPaterno;

   public HijoDAO(){


   }


   public String getCorreo() {
      return correo;
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

   public String getCodigoPateron() {
      return CodigoPaterno;
   }

   public void setCodigoPateron(String codigoPateron) {
      CodigoPaterno = codigoPateron;
   }





}
