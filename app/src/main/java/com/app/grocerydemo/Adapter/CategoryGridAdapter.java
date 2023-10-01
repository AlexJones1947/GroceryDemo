package com.app.grocerydemo.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.app.grocerydemo.R;
import com.app.grocerydemo.db.DatabaseHandler;
import com.app.grocerydemo.interfaces.Categorygridquantity;
import com.app.grocerydemo.model.NewCartModel;
import com.app.grocerydemo.model.NewCategoryDataModel;
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

public class CategoryGridAdapter extends RecyclerView.Adapter<CategoryGridAdapter.MyViewHolder> {
    Context context;
    Categorygridquantity categorygridquantity;
    private List<NewCategoryDataModel> categoryDataModels;
    private DatabaseHandler dbcart;
  //  private SessionManagement sessionManagement;
    private String varientKey = "varient_id";
    private DecimalFormat dFormat;
    List<String> myFavNew = new ArrayList<>();
    List<NewCartModel> newCartModelList = new ArrayList<>();


    public CategoryGridAdapter(List<NewCategoryDataModel> categoryGridList, Context context, Categorygridquantity categorygridquantity) {
        this.categoryDataModels = categoryGridList;
        this.dbcart = new DatabaseHandler(context);
       // this.sessionManagement = new SessionManagement(context);
        this.categorygridquantity = categorygridquantity;

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
                .inflate(R.layout.layout_category_list, parent, false);
        context = parent.getContext();
        dbcart = new DatabaseHandler(context);
//        if (sessionManagement == null) {
//            sessionManagement = new SessionManagement(context);
//        }
        dFormat = new DecimalFormat("#.##");
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        /*NewCategoryDataModel cc = categoryDataModels.get(position);

      //  holder.currencyIndicator.setText(sessionManagement.getCurrency());
       // holder.currencyIndicator2.setText(sessionManagement.getCurrency());
        holder.prodNAme.setText(cc.getProductName());
//        holder.pPrice.setText(cc.getPrice());
        holder.txtUnitvalue.setText(cc.getUnit());
        holder.pQuan.setText(cc.getQuantity());
//        holder.pMrp.setText(cc.getMrp());
        holder.pDescrptn.setText(cc.getDescription());
      //  sessionManagement.setStoreId(cc.getStoreId());

        if (Integer.parseInt(cc.getStock()) > 0) {
            holder.outofs.setVisibility(View.GONE);
            holder.outofsIn.setVisibility(View.VISIBLE);
        } else {
            holder.outofsIn.setVisibility(View.GONE);
            holder.outofs.setVisibility(View.VISIBLE);
        }

        int qtyd = Integer.parseInt(dbcart.getInCartItemQtys(cc.getVarientId()));
        double priced = Double.parseDouble(cc.getPrice());
        double mrpd = Double.parseDouble(cc.getMrp());
        if (qtyd > 0) {
            holder.btnAdd.setVisibility(View.GONE);
            holder.llAddQuan.setVisibility(View.VISIBLE);
            holder.txtQuan.setText("" + qtyd);
            holder.pPrice.setText("" + dFormat.format((priced * qtyd)));
            holder.pMrp.setText("" + dFormat.format((mrpd * qtyd)));
        } else {
            holder.btnAdd.setVisibility(View.VISIBLE);
            holder.llAddQuan.setVisibility(View.GONE);
            holder.pPrice.setText(dFormat.format(Double.parseDouble(cc.getPrice())));
            holder.pMrp.setText(dFormat.format(Double.parseDouble(cc.getMrp())));
            holder.txtQuan.setText("" + 0);
        }

        double firstSolution = Double.parseDouble(cc.getMrp()) - Double.parseDouble(cc.getPrice());
        double firstSolution1= firstSolution / Double.parseDouble(cc.getMrp());
        double res = firstSolution1 * 100.0f;
        holder.pdiscountOff.setText(dFormat.format(res) + "%" + " Off");


        holder.pMrp.setPaintFlags(holder.pMrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.itemView.setOnClickListener(v -> {
          //  categorygridquantity.onProductDetials(position);
        });


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

        holder.rlQuan.setOnClickListener(view -> categorygridquantity.onClick(view, position, cc.getProductId(), cc.getProductName()));

        holder.plus.setOnClickListener(v -> {
            holder.btnAdd.setVisibility(View.GONE);
            holder.llAddQuan.setVisibility(View.VISIBLE);
            int i = Integer.parseInt(dbcart.getInCartItemQtys(cc.getVarientId()));
            if (i < Integer.parseInt(cc.getStock())) {
                holder.txtQuan.setText("" + (i + 1));
                holder.pPrice.setText("" + dFormat.format((priced * (i + 1))));
                holder.pMrp.setText("" + dFormat.format((mrpd * (i + 1))));
                updateMultiply(position, i + 1);
            }
        });
        holder.minus.setOnClickListener(v -> {
            int i = Integer.parseInt(dbcart.getInCartItemQtys(cc.getVarientId()));
            if ((i - 1) < 0 || (i - 1) == 0) {
                holder.btnAdd.setVisibility(View.VISIBLE);
                holder.llAddQuan.setVisibility(View.GONE);
                holder.txtQuan.setText("" + 0);
                holder.pPrice.setText("" + dFormat.format(priced));
                holder.pMrp.setText("" + dFormat.format(mrpd));
            } else {
                holder.txtQuan.setText("" + (i - 1));
                holder.pPrice.setText("" + dFormat.format((priced * (i - 1))));
                holder.pMrp.setText("" + dFormat.format((mrpd * (i - 1))));
            }
            updateMultiply(position, i - 1);
        });
        holder.btnAdd.setOnClickListener(v -> {
            holder.btnAdd.setVisibility(View.GONE);
            holder.llAddQuan.setVisibility(View.VISIBLE);
            holder.txtQuan.setText("1");
            updateMultiply(position, 1);
        });


        if (myFavNew.contains(categoryDataModels.get(position).getProductId().toString())) {
            holder.iv_like_unlike.setImageResource(R.drawable.fav_fill);
        } else {
            holder.iv_like_unlike.setImageResource(R.drawable.fav_unfill);
        }

        holder.iv_like_unlike.setOnClickListener(v -> {
            try {
                NewCartModel cartModel = new NewCartModel();
                cartModel.setVarientId("" + categoryDataModels.get(position).getVarientId());
                cartModel.setVarientImage("" + categoryDataModels.get(position).getVarientImage());
                cartModel.setStock("" + categoryDataModels.get(position).getStock());
                cartModel.setQuantity("" + categoryDataModels.get(position).getQuantity());
                cartModel.setUnit("" + categoryDataModels.get(position).getUnit());
                cartModel.setMrp("" + categoryDataModels.get(position).getMrp());
                cartModel.setPrice("" + categoryDataModels.get(position).getPrice());
                cartModel.setDescription("" + categoryDataModels.get(position).getDescription());
                cartModel.setProductName("" + categoryDataModels.get(position).getProductName());
                cartModel.setProductId("" + categoryDataModels.get(position).getProductId());
                cartModel.setProductImage("" + categoryDataModels.get(position).getProductImage());
                cartModel.setStoreId("" + categoryDataModels.get(position).getStoreId());


                newCartModelList = SharedPref.getFavModel(context);
                if (myFavNew.contains(categoryDataModels.get(position).getProductId())) {
                    holder.iv_like_unlike.setImageResource(R.drawable.fav_unfill);
                    myFavNew.remove(categoryDataModels.get(position).getProductId());
                    if (newCartModelList != null) {
                        for (int indexing = 0; indexing < newCartModelList.size(); indexing++) {
                            if (newCartModelList.get(indexing).getProductId().equals(categoryDataModels.get(position).getProductId())) {
                                newCartModelList.remove(indexing);
                            }
                        }
                    }
                } else {
                    holder.iv_like_unlike.setImageResource(R.drawable.fav_fill);
                    myFavNew.add(categoryDataModels.get(position).getProductId());
                    if (newCartModelList != null)
                        newCartModelList.add(cartModel);
                    else {
                        newCartModelList = new ArrayList<>();
                        newCartModelList.add(cartModel);
                    }
                }
                Gson gson = new Gson();
                String mapData = gson.toJson(myFavNew);
                SharedPref.putFavListData(context, mapData);
                SharedPref.putFavModel(context, newCartModelList);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
*/

    }

    private void updateMultiply(int pos, int i) {
        HashMap<String, String> map = new HashMap<>();
        map.put(varientKey, categoryDataModels.get(pos).getVarientId());
        map.put("product_name", categoryDataModels.get(pos).getProductName());
        map.put("product_id", categoryDataModels.get(pos).getProductId());
        map.put("category_id", categoryDataModels.get(pos).getProductId());
        map.put("title", categoryDataModels.get(pos).getProductName());
        map.put("price", categoryDataModels.get(pos).getPrice());
        map.put("mrp", categoryDataModels.get(pos).getMrp());
        map.put("product_image", categoryDataModels.get(pos).getVarientImage());
        map.put("status", "0");
        map.put("in_stock", "");
        map.put("unit_value", categoryDataModels.get(pos).getQuantity());
        map.put("unit", categoryDataModels.get(pos).getUnit() != null ? categoryDataModels.get(pos).getUnit() : "");
        map.put("increament", "0");
        map.put("rewards", "0");
        map.put("stock", categoryDataModels.get(pos).getStock());
        map.put("product_description", categoryDataModels.get(pos).getDescription());
        if (i > 0) {
            dbcart.setCart(map, i);
        } else {
            dbcart.removeItemFromCart(map.get(varientKey));
        }
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                SharedPreferences preferences = context.getSharedPreferences("Grocery", Context.MODE_PRIVATE);
                preferences.edit().putInt("cardqnty", dbcart.getCartCount()).apply();
                categorygridquantity.onCartItemAddOrMinus();
            }
        } catch (IndexOutOfBoundsException e) {
            Log.d("qwer", e.toString());
        }
    }

    @Override
    public int getItemCount() {
        //return categoryDataModels.size();
        return 10;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView prodNAme;
        TextView pDescrptn;
        TextView pQuan;
        TextView pPrice;
        TextView pdiscountOff;
        TextView pMrp;
        TextView minus;
        TextView plus;
        TextView txtQuan;
        TextView txtUnitvalue;
        TextView currencyIndicator;
        TextView currencyIndicator2;
        ImageView image;
        ImageView iv_like_unlike;
        LinearLayout btnAdd;
        LinearLayout llAddQuan;
        LinearLayout outofsIn;
        LinearLayout outofs;
        LinearLayout rlQuan;
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
            txtQuan = view.findViewById(R.id.txtQuan);
            minus = view.findViewById(R.id.minus);
            plus = view.findViewById(R.id.plus);
            txtUnitvalue = view.findViewById(R.id.txt_unitvalue);
            llAddQuan = view.findViewById(R.id.ll_addQuan);
            outofsIn = view.findViewById(R.id.outofs_in);
            outofs = view.findViewById(R.id.outofs);
            progressBar = itemView.findViewById(R.id.progressB);
        }


    }
}
