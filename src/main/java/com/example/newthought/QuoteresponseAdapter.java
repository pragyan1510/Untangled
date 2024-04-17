package com.example.newthought;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuoteresponseAdapter extends RecyclerView.Adapter<quoteViewHolder>{
    Context context;
    List<QouteResponse> list;
    copyListener listener;

    public QuoteresponseAdapter(Context context, List<QouteResponse> list, copyListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public quoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new quoteViewHolder(LayoutInflater.from(context).inflate(R.layout.list_quote,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull quoteViewHolder holder, int position) {
        holder.textview_quote.setText(list.get(position).getText());
        holder.textview_author.setText(list.get(position).getAuthor());

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Toast.makeText(context, "marked as favourite", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "removed from favorites", Toast.LENGTH_SHORT).show();
                }
            }
        });





        holder.btn_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onclick(list.get(holder.getAdapterPosition()).getText());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class quoteViewHolder extends RecyclerView.ViewHolder{

    TextView textview_quote, textview_author;
    Button btn_copy;

    CheckBox checkBox;

    public quoteViewHolder(@NonNull View itemView) {
        super(itemView);
        textview_quote = itemView.findViewById(R.id.textview_quote);
        textview_author = itemView.findViewById(R.id.textview_author);
        btn_copy = itemView.findViewById(R.id.button_copy);
        checkBox = itemView.findViewById(R.id.cbheart);
    }
}
