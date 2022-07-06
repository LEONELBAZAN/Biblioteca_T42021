package com.leonel.mantenimientosql;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.leonel.mantenimientosql.dao.LibroDAO;
import com.leonel.mantenimientosql.entidades.Libro;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorPersonalizado  extends RecyclerView.Adapter<AdaptadorPersonalizado.MiviewHolder> {

    private Context context;
    private List<Libro> listarLibros= new ArrayList<>();
    public AdaptadorPersonalizado(Context context, List<Libro>listarLibros){
        this.context=context;
        this.listarLibros=listarLibros;

    }

    @NonNull
    @Override
    public AdaptadorPersonalizado.MiviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater= LayoutInflater.from(context);
        View vista= inflater.inflate(R.layout.fila,parent,false);

        return new MiviewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPersonalizado.MiviewHolder holder, int position) {
        holder.filaTitulo.setText(listarLibros.get(position).getNombre()+"");
        holder.filaAutor.setText(listarLibros.get(position).getAutor()+"");
        holder.filaEditorial.setText(listarLibros.get(position).getEditorial()+"");
        holder.filaPrecio.setText(listarLibros.get(position).getPrecio()+"");

        if (listarLibros.get(position).getPrecio()>200){
            holder.imageView2.setImageResource(R.drawable.libro);
        }else{
            holder.imageView2.setImageResource(R.drawable.libro2);
        }

        holder.filaEditar.setOnClickListener(new View.OnClickListener(){

            @Override
               public void onClick(View view){
                Intent intent=new Intent(context,EditarActivity.class);
                intent.putExtra("id",listarLibros.get(position).getId()+"");
                intent.putExtra("nombre",listarLibros.get(position).getNombre()+"");
                intent.putExtra("autor",listarLibros.get(position).getAutor()+"");
                intent.putExtra("editorial",listarLibros.get(position).getEditorial()+"");
                intent.putExtra("precio",listarLibros.get(position).getPrecio()+"");

                context.startActivity(intent);
            }
        });

        holder.filaEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ventana = new AlertDialog.Builder(context);
                ventana.setTitle("Confirmar");
                ventana.setMessage("Deseas elimianr libro");
                ventana.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LibroDAO libroDAO=new LibroDAO(context);
                        libroDAO.abrirBD();
                       String mensaje= libroDAO.eliminarLibro(listarLibros.get(position).getId());

// segundo modal
                        AlertDialog.Builder ventana1 = new AlertDialog.Builder(context);
                        ventana1.setTitle("Mensaje Informativo");
                        ventana1.setMessage(mensaje);
                        ventana1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(context, principal_Activity.class);
                                context.startActivity(intent);
                            }
                        });
                        ventana1.create().show();


                    }
                });
                ventana.setNegativeButton("no",null);
                ventana.create().show();

            }
        });
    }

    @Override
    public int getItemCount() {

        return listarLibros.size();
    }

    public class MiviewHolder extends  RecyclerView.ViewHolder{
        TextView filaTitulo,filaAutor,filaEditorial,filaPrecio;
        ImageButton filaEditar,filaEliminar;
        ImageView imageView2;

        public  MiviewHolder(@NonNull View itemView) {
            super(itemView);


            filaTitulo = itemView.findViewById(R.id.filaTitulo);
            filaAutor = itemView.findViewById(R.id.filaAutor);
            filaEditorial = itemView.findViewById(R.id.filaEditorial);
            filaPrecio = itemView.findViewById(R.id.filaPrecio);
            imageView2=itemView.findViewById((R.id.imageView2));


            filaEditar = itemView.findViewById(R.id.filaEditar);
            filaEliminar = itemView.findViewById(R.id.filaEliminar);

        }
    }
}

