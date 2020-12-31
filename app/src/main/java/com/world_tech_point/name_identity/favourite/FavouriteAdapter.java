package com.world_tech_point.name_identity.favourite;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.world_tech_point.name_identity.ContainAdapter;
import com.world_tech_point.name_identity.R;
import com.world_tech_point.name_identity.database.Favourite_DB_Helper;
import com.world_tech_point.name_identity.database.Favourite_DB_Manager;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {

    private Context context;
    private List<FavouriteClass>favouriteClassList;
    private FavouriteClass favouriteClass;

    public FavouriteAdapter(Context context, List<FavouriteClass> favouriteClassList) {
        this.context = context;
        this.favouriteClassList = favouriteClassList;
    }

    @NonNull
    @Override
    public FavouriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.model_baby_name, parent, false);
        return new FavouriteAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull FavouriteAdapter.ViewHolder holder, int position) {

        favouriteClass = favouriteClassList.get(position);
        holder.name.setText(""+(position+1)+". "+favouriteClass.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                containShowAlert(""+(position+1)+". "+favouriteClass.getName(),favouriteClass.getMeaning(),favouriteClass.getCategory());
            }
        });

    }
    @Override
    public int getItemCount() {
        return favouriteClassList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.modelBabyName);
        }
    }

    private void containShowAlert(final String name,final String meaning ,final String category) {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context,
                R.style.Theme_Design_BottomSheetDialog);
        final View bottomSheetView = LayoutInflater.from(context).inflate(R.layout.category_popup_model,
                bottomSheetDialog.findViewById(R.id.containShowPopUp_id));
        TextView favouriteBtn = bottomSheetView.findViewById(R.id.popUpFavouriteBtn);
        TextView closeBtn = bottomSheetView.findViewById(R.id.popUpCloseBtn);
        TextView nameTV = bottomSheetView.findViewById(R.id.popUpNameBtn);
        TextView meaningTV = bottomSheetView.findViewById(R.id.popUpMeaningBtn);
        TextView shareBtn = bottomSheetView.findViewById(R.id.popUpShareBtn);
        TextView copyBtn = bottomSheetView.findViewById(R.id.popCopyBtn);

        nameTV.setText(name);
        meaningTV.setText("Meaning: \n"+meaning);
        favouriteBtn.setText(category);

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareNameMeaning(name,meaning);
            }
        });
        copyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name!= null && meaning != null){
                    ClipboardManager clipboard = (ClipboardManager)
                            context.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("text", name+"\n\n"+meaning);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(context, "Copied!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Text Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }

    private void shareNameMeaning(String name,String meaning) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT,name);
        intent.putExtra(Intent.EXTRA_TEXT,meaning);
        context.startActivity(Intent.createChooser(intent,"Modern Baby Name"));
    }
}
