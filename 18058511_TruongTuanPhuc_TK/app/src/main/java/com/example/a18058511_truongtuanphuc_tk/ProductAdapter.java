package com.example.a18058511_truongtuanphuc_tk;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List mproduct;
    private Context mcontext;


    public ProductAdapter(List mproduct, Context mcontext) {
        this.mproduct = mproduct;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View productView = inflater.inflate(R.layout.product_item, parent, false);
        ProductViewHolder viewholder = new ProductViewHolder(productView);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product x= (Product) mproduct.get(position);
        holder.txtproductprice.setText(x.getPrice()+"");
        holder.txtproductname.setText(x.getName());

        holder.btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte=new Intent(v.getContext(),MainActivity4.class);

                    inte.putExtra("product",x);
                v.getContext().startActivity(inte);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mproduct.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        public TextView txtproductprice;
        public  TextView txtproductname;
        public Button btnupdate;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txtproductprice = itemView.findViewById(R.id.txtprice);
            txtproductname = itemView.findViewById(R.id.txtname);
            btnupdate = itemView.findViewById(R.id.btnupdate);
        }
    }
}
