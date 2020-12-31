package com.world_tech_point.name_identity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.world_tech_point.name_identity.database.Favourite_DB_Helper;
import com.world_tech_point.name_identity.database.Favourite_DB_Manager;
import com.world_tech_point.name_identity.favourite.FavouriteClass;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class ContainAdapter extends RecyclerView.Adapter<ContainAdapter.ViewHolder> {

    private Context context;
    private List<ContainsClass> containsClassList;
    private ContainsClass containsClass;
    String category;

    Favourite_DB_Helper favouriteDb_Helper;
    SQLiteDatabase db;
    String nameExits = null;
    Favourite_DB_Manager dbManager;

    int addCount;

    public ContainAdapter(Context context, List<ContainsClass> containsClassList, String category) {
        this.context = context;
        this.containsClassList = containsClassList;
        this.category = category;
    }

    @NonNull
    @Override
    public ContainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.model_baby_name, parent, false);
        dbManager = new Favourite_DB_Manager(context);
        favouriteDb_Helper = new Favourite_DB_Helper(context);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContainAdapter.ViewHolder holder, final int position) {

        containsClass = containsClassList.get(position);
        holder.modelBabyName.setText(""+(position+1)+". "+containsClass.getName());
        holder.modelBabyName.setAnimation(AnimationUtils.loadAnimation(context, R.anim.bottom_animation));

        holder.modelShowMeaning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                containsClass = containsClassList.get(position);
                containShowAlert(containsClass.getName(),containsClass.getMeaning(), category, position);
            }
        });
    }

    private void containShowAlert(final String name,final String meaning ,final String category, int position) {

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

        nameTV.setText(""+(position+1)+". "+name);
        meaningTV.setText("Meaning: \n"+meaning);

        favouriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert(name,meaning,category);
            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               shareNameMeaning(name,meaning, category);
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

    private void alert(String name, String meaning, String category) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage("Are you Sure to add favourite list?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FavouriteClass favouriteClass = new FavouriteClass(category,name,meaning);
                        boolean isSuccess = dbManager.save_Data(favouriteClass);
                        if (isSuccess){
                            Toasty.info(context,"Favourite Added Success",Toasty.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }


    private void shareNameMeaning(String name,String meaning, String category) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT,name);
        intent.putExtra(Intent.EXTRA_TEXT,meaning);
        context.startActivity(Intent.createChooser(intent,category));
    }

    @Override
    public int getItemCount() {
        return containsClassList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView modelBabyName, modelShowMeaning;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            modelBabyName = itemView.findViewById(R.id.modelBabyName);
            modelShowMeaning = itemView.findViewById(R.id.modelShowMeaning);
        }
    }
}
