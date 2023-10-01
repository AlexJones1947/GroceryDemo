package com.app.grocerydemo.Adapter;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.app.grocerydemo.R;
import com.app.grocerydemo.db.DatabaseHandler;
import com.app.grocerydemo.interfaces.Categorygridquantity;
import com.app.grocerydemo.model.NewCartModel;
import com.app.grocerydemo.util.CurvedBottomNavigationView;
import com.app.grocerydemo.util.SharedPref;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyViewHolder> {
    private static final int LIMIT = 6;
    Context context;
    private List<NewCartModel> topSelling;
    private DatabaseHandler dbcart;
  //  private SessionManagement sessionManagement;
    private String varientKey = "varient_id";
    private Categorygridquantity prodcutDetailsVerifier;
    private DatabaseHandler db;
    List<String> myFavNew = new ArrayList<>();

    public FavoriteAdapter(Context context,  List<NewCartModel> topSelling, Categorygridquantity prodcutDetailsVerifier) {
        this.context = context;
        this.topSelling = topSelling;
        this.prodcutDetailsVerifier = prodcutDetailsVerifier;
        dbcart = new DatabaseHandler(context);
     //   sessionManagement = new SessionManagement(context);
        db = new DatabaseHandler(context);

        String myFav = SharedPref.getFavListData(context);
        if (!TextUtils.isEmpty(myFav)) {
            Gson gson1 = new Gson();
            myFavNew = gson1.fromJson(myFav, new TypeToken<List<String>>() {
            }.getType());
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_product_add, parent, false);
        context = parent.getContext();
        dbcart = new DatabaseHandler(context);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

       /* NewCartModel cc = topSelling.get(position);
     //   holder.currencyIndicator.setText(sessionManagement.getCurrency());
      //  holder.currencyIndicator2.setText(sessionManagement.getCurrency());
        holder.prodNAme.setText(cc.getProductName());
        holder.pDescrptn.setText(cc.getDescription());
        holder.pQuan.setText(cc.getQuantity() + " " + cc.getUnit());
        DecimalFormat dFormat = new DecimalFormat("#.##");
        holder.pPrice.setText(dFormat.format(Double.parseDouble(cc.getPrice())));
        //  String totalOff = String.valueOf(Double.parseDouble(cc.getMrp()) - Double.parseDouble(cc.getPrice()));

        double firstSolution = Double.parseDouble(cc.getMrp()) - Double.parseDouble(cc.getPrice());
        double firstSolution1 = firstSolution / Double.parseDouble(cc.getMrp());
        double res = firstSolution1 * 100.0f;
        holder.pdiscountOff.setText(dFormat.format(res) + "%" + " Off");
        holder.pMrp.setText(dFormat.format(Double.parseDouble(cc.getMrp())));
        holder.pMrp.setPaintFlags(holder.pMrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
     //   sessionManagement.setStoreId(cc.getStoreId());
        if (Integer.parseInt(cc.getStock()) > 0) {
            holder.outofs.setVisibility(View.GONE);
            holder.outofsIn.setVisibility(View.VISIBLE);
        } else {
            holder.outofsIn.setVisibility(View.GONE);
            holder.outofs.setVisibility(View.VISIBLE);
        }


        int qtyd = Integer.parseInt(dbcart.getInCartItemQtys(topSelling.get(position).getVarientId()));
        if (qtyd > 0) {
            holder.btnAdd.setVisibility(View.GONE);
            holder.llAddQuan.setVisibility(View.VISIBLE);
            holder.txtQuan.setText("" + qtyd);
            double priced = Double.parseDouble(cc.getPrice());
            double mrpd = Double.parseDouble(cc.getMrp());
            holder.pPrice.setText("" + dFormat.format((priced * qtyd)));
            holder.pMrp.setText("" + dFormat.format((mrpd * qtyd)));
        } else {
            holder.btnAdd.setVisibility(View.VISIBLE);
            holder.llAddQuan.setVisibility(View.GONE);
            holder.pPrice.setText(dFormat.format(Double.parseDouble(cc.getPrice())));
            holder.pMrp.setText(dFormat.format(Double.parseDouble(cc.getMrp())));
            holder.txtQuan.setText("" + 0);
        }

        holder.progressBar.setVisibility(View.VISIBLE);
        Glide.with(context)
                .load("IMG_URL" + cc.getProductImage())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.new_place_holder).error(R.drawable.new_place_holder))
                .into(holder.image);
        double price = Double.parseDouble(topSelling.get(position).getPrice());
        double mrp = Double.parseDouble(topSelling.get(position).getMrp());


        holder.itemView.setOnClickListener(v -> {


        });


        holder.plus.setOnClickListener(v -> {
            try {
                if (dbcart == null) {
                    dbcart = new DatabaseHandler(v.getContext());
                }
                int i = Integer.parseInt(dbcart.getInCartItemQtys(topSelling.get(position).getVarientId()));
                if (i < Integer.parseInt(cc.getStock())) {
                    holder.btnAdd.setVisibility(View.GONE);
                    holder.llAddQuan.setVisibility(View.VISIBLE);
                    holder.txtQuan.setText("" + (i + 1));
                    holder.pPrice.setText("" + price);
                    holder.pMrp.setText("" + price);
                    updateMultiply(position, (i + 1));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        holder.minus.setOnClickListener(v -> {
            int i = Integer.parseInt(dbcart.getInCartItemQtys(topSelling.get(position).getVarientId()));
            if ((i - 1) < 0 || (i - 1) == 0) {
                holder.btnAdd.setVisibility(View.VISIBLE);
                holder.llAddQuan.setVisibility(View.GONE);
                holder.txtQuan.setText("" + 0);
                *//*holder.pPrice.setText("" + dFormat.format(price));
                holder.pMrp.setText("" + dFormat.format(mrp));*//*
                holder.pPrice.setText("" + price);
                holder.pMrp.setText("" + mrp);
            } else {
                holder.txtQuan.setText("" + (i - 1));
               *//* holder.pPrice.setText("" + dFormat.format((price * (i - 1))));
                holder.pMrp.setText("" + dFormat.format((mrp * (i - 1))));*//*
                holder.pPrice.setText("" + price);
                holder.pMrp.setText("" + mrp);
            }
            updateMultiply(position, (i - 1));
        });
        holder.btnAdd.setOnClickListener(v -> {
            holder.btnAdd.setVisibility(View.GONE);
            holder.llAddQuan.setVisibility(View.VISIBLE);
            holder.txtQuan.setText("1");
            updateMultiply(position, 1);
        });

        holder.iv_like_unlike.setImageResource(R.drawable.fav_fill);

        holder.iv_like_unlike.setOnClickListener(v -> {
            if (myFavNew.contains(topSelling.get(position).getProductId())) {
                myFavNew.remove(position);
            }
            topSelling.remove(position);
            notifyDataSetChanged();


            Gson gson = new Gson();
            String mapData = gson.toJson(myFavNew);
            SharedPref.putFavListData(context, mapData);
            SharedPref.putFavModel(context, topSelling);

        });*/
    }

    @Override
    public int getItemCount() {
        //return topSelling.size();
        return 10;
    }

    private void updateMultiply(int pos, int i) {
        try {
            HashMap<String, String> map = new HashMap<>();
            map.put(varientKey, topSelling.get(pos).getVarientId());
            map.put("product_name", topSelling.get(pos).getProductName());
            map.put("category_id", topSelling.get(pos).getProductId());
            map.put("product_id", topSelling.get(pos).getProductId());
            map.put("title", topSelling.get(pos).getProductName());
            map.put("price", topSelling.get(pos).getPrice());
            map.put("mrp", topSelling.get(pos).getMrp());
            map.put("product_image", topSelling.get(pos).getProductImage());
            map.put("status", "");
            map.put("in_stock", "");
            map.put("unit_value", topSelling.get(pos).getQuantity());
            map.put("unit", topSelling.get(pos).getUnit());
            map.put("increament", "0");
            map.put("rewards", "0");
            map.put("stock", topSelling.get(pos).getStock());
            map.put("product_description", topSelling.get(pos).getDescription());

            if (i > 0) {
                dbcart.setCart(map, i);
            } else {
                dbcart.removeItemFromCart(map.get(varientKey));
            }


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                SharedPreferences preferencess = context.getSharedPreferences("Grocery", Context.MODE_PRIVATE);
                preferencess.edit().putInt("cardqnty", dbcart.getCartCount()).apply();
            }


        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView prodNAme;
        TextView pDescrptn;
        TextView pQuan;
        TextView pPrice;
        TextView pdiscountOff;
        TextView pMrp;
        TextView minus;
        TextView plus;
        TextView txtQuan;
        TextView currencyIndicator;
        TextView currencyIndicator2;
        ImageView image;
        ImageView iv_like_unlike;
        LinearLayout btnAdd;
        LinearLayout llAddQuan;
        LinearLayout outofs;
        LinearLayout outofsIn;
        RelativeLayout rlQuan;
        ProgressBar progressBar;

        public MyViewHolder(View view) {
            super(view);
            prodNAme = view.findViewById(R.id.txt_pName);
            currencyIndicator = view.findViewById(R.id.currency_indicator);
            currencyIndicator2 = view.findViewById(R.id.currency_indicator_2);
            pDescrptn = view.findViewById(R.id.txt_pInfo);
            pQuan = view.findViewById(R.id.txt_unit);
            pPrice = view.findViewById(R.id.txt_Pprice);
            image = view.findViewById(R.id.prodImage);
            iv_like_unlike = view.findViewById(R.id.iv_like_unlike);
            pdiscountOff = view.findViewById(R.id.txt_discountOff);
            pMrp = view.findViewById(R.id.txt_Mrp);
            rlQuan = view.findViewById(R.id.rlQuan);
            btnAdd = view.findViewById(R.id.btn_Add);
            llAddQuan = view.findViewById(R.id.ll_addQuan);
            outofs = view.findViewById(R.id.outofs);
            outofsIn = view.findViewById(R.id.outofs_in);
            txtQuan = view.findViewById(R.id.txtQuan);
            minus = view.findViewById(R.id.minus);
            plus = view.findViewById(R.id.plus);
            progressBar = (ProgressBar) view.findViewById(R.id.progressB);
        }

        @Override
        public void onClick(View view) {

        }
    }

}
