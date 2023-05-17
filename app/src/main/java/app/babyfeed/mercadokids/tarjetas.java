package app.babyfeed.mercadokids;

import DAO.ProductoDAO;
import DTO.ProductoDTO;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class tarjetas extends RecyclerView.Adapter<tarjetas.tarjetasholder> {

    private List<ProductoDAO> lista;
    private LayoutInflater Inflater;

    private Context context;

    final tarjetas.OnItemClickListener listener;

    int imagenes[] = {R.drawable.mustang,R.drawable.lambo,R.drawable.nis};


    public tarjetas(Context context,OnItemClickListener listener){
        this.Inflater = LayoutInflater.from(context);
        this.context = context;
        this.lista = listas();
        this.listener = listener;
    }
  /*  public tarjetas(Context context,tarjetas.OnItemClickListener escucha){
        this.Inflater = LayoutInflater.from(context);
        this.context = context;
        this.lista = listas();
        this.listener = escucha;
    } */

    public interface OnItemClickListener {
        void onItemClick(ProductoDAO pro);


    }

    @SuppressLint("Range")
    private List<ProductoDAO> listas (){

        //PadreDTO pa = new PadreDTO(context);
        ProductoDTO pro = new ProductoDTO(context);
        Cursor nuevo = pro.consulta();
        List<ProductoDAO> nuevalista = new ArrayList<>();
        int contador = 0;
        if (nuevo.moveToFirst()) {
            do {
           ProductoDAO producto = new ProductoDAO();
                   producto.setImagen(imagenes[contador]);
                   producto.setNombre(nuevo.getString(nuevo.getColumnIndex("Nombre")));
                   producto.setDescripcion(nuevo.getString(nuevo.getColumnIndex("Descripcion")));
             producto.setPrecio(nuevo.getInt(nuevo.getColumnIndex("Precio")));
                   nuevalista.add(producto);
                    contador++;
            } while (nuevo.moveToNext());

        }


        return nuevalista;
    }


    @NotNull
    @Override
    public tarjetasholder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = Inflater.inflate(R.layout.activity_tarjeta,null);
       return new tarjetasholder(view);
    }

    @Override
    public void onBindViewHolder(tarjetasholder holder, int position) {
        holder.bindData(lista.get(position));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void setItem(List<ProductoDAO> items){ lista = items; }


    public class tarjetasholder extends RecyclerView.ViewHolder{
        TextView et1,et2,et3;
        ImageView EV1;
       public tarjetasholder(View itemView) {
           super(itemView);
           et1 = itemView.findViewById(R.id.textTitulo);
           et2 = itemView.findViewById(R.id.textDescripcion);
           et3 = itemView.findViewById(R.id.textPrecio);
           EV1 = itemView.findViewById(R.id.Imagen);
       }

       void bindData(final ProductoDAO productoDAO){
           int imagen = productoDAO.getImagen();
           String titulo = productoDAO.getNombre();
           String descripcion = productoDAO.getDescripcion();
           int precio = productoDAO.getPrecio();
           EV1.setImageResource(imagen);
           et1.setText(titulo);
           et2.setText(descripcion);
           et3.setText(precio + " MXN");
           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   listener.onItemClick(productoDAO);
               }
           });
         /*  EV1.setImageResource(productoDAO.getImagen());
           et1.setText(productoDAO.getNombre());
           et2.setText(productoDAO.getDescripcion());
           et3.setText(productoDAO.getPrecio()); */
          // Toast.makeText(context,productoDAO.getNombre(),Toast.LENGTH_LONG).show();
       }


   }

}
