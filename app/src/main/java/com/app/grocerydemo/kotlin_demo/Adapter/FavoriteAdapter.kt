package com.app.grocerydemo.kotlin_demo.Adapter

import android.content.Context
import com.app.grocerydemo.model.NewCartModel
import com.app.grocerydemo.interfaces.Categorygridquantity
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import com.app.grocerydemo.R
import android.os.Build
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.app.grocerydemo.kotlin_demo.db.DatabaseHandler
import com.app.grocerydemo.kotlin_demo.util.SharedPref
import com.google.gson.Gson
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import java.lang.IndexOutOfBoundsException
import java.util.ArrayList
import java.util.HashMap

class FavoriteAdapter(
    var context: Context,
    private val topSelling: List<NewCartModel>,
    private val prodcutDetailsVerifier: Categorygridquantity
) : RecyclerView.Adapter<FavoriteAdapter.MyViewHolder>() {
    private var dbcart: DatabaseHandler

    //  private SessionManagement sessionManagement;
    private val varientKey = "varient_id"
    private val db: DatabaseHandler
    var myFavNew: List<String> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_product_add, parent, false)
        context = parent.context
        dbcart = DatabaseHandler(context)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

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
                */
        /*holder.pPrice.setText("" + dFormat.format(price));
                holder.pMrp.setText("" + dFormat.format(mrp));*/
        /*
                holder.pPrice.setText("" + price);
                holder.pMrp.setText("" + mrp);
            } else {
                holder.txtQuan.setText("" + (i - 1));
               */
        /* holder.pPrice.setText("" + dFormat.format((price * (i - 1))));
                holder.pMrp.setText("" + dFormat.format((mrp * (i - 1))));*/
        /*
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

    override fun getItemCount(): Int {
        //return topSelling.size();
        return 10
    }

    private fun updateMultiply(pos: Int, i: Int) {
        try {
            val map = HashMap<String, String>()
            map[varientKey] = topSelling[pos].varientId
            map["product_name"] = topSelling[pos].productName
            map["category_id"] = topSelling[pos].productId
            map["product_id"] = topSelling[pos].productId
            map["title"] = topSelling[pos].productName
            map["price"] = topSelling[pos].price
            map["mrp"] = topSelling[pos].mrp
            map["product_image"] = topSelling[pos].productImage
            map["status"] = ""
            map["in_stock"] = ""
            map["unit_value"] = topSelling[pos].quantity
            map["unit"] = topSelling[pos].unit
            map["increament"] = "0"
            map["rewards"] = "0"
            map["stock"] = topSelling[pos].stock
            map["product_description"] = topSelling[pos].description
            if (i > 0) {
                dbcart.setCart(map, i)
            } else {
                dbcart.removeItemFromCart(map[varientKey])
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val preferencess = context.getSharedPreferences("Grocery", Context.MODE_PRIVATE)
                preferencess.edit().putInt("cardqnty", dbcart.cartCount).apply()
            }
        } catch (e: IndexOutOfBoundsException) {
            e.printStackTrace()
        }
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        var prodNAme: TextView
        var pDescrptn: TextView
        var pQuan: TextView
        var pPrice: TextView
        var pdiscountOff: TextView
        var pMrp: TextView
        var minus: TextView
        var plus: TextView
        var txtQuan: TextView
        var currencyIndicator: TextView
        var currencyIndicator2: TextView
        var image: ImageView
        var iv_like_unlike: ImageView
        var btnAdd: LinearLayout
        var llAddQuan: LinearLayout
        var outofs: LinearLayout
        var outofsIn: LinearLayout
        var rlQuan: RelativeLayout
        var progressBar: ProgressBar
        override fun onClick(view: View) {}

        init {
            prodNAme = view.findViewById(R.id.txt_pName)
            currencyIndicator = view.findViewById(R.id.currency_indicator)
            currencyIndicator2 = view.findViewById(R.id.currency_indicator_2)
            pDescrptn = view.findViewById(R.id.txt_pInfo)
            pQuan = view.findViewById(R.id.txt_unit)
            pPrice = view.findViewById(R.id.txt_Pprice)
            image = view.findViewById(R.id.prodImage)
            iv_like_unlike = view.findViewById(R.id.iv_like_unlike)
            pdiscountOff = view.findViewById(R.id.txt_discountOff)
            pMrp = view.findViewById(R.id.txt_Mrp)
            rlQuan = view.findViewById(R.id.rlQuan)
            btnAdd = view.findViewById(R.id.btn_Add)
            llAddQuan = view.findViewById(R.id.ll_addQuan)
            outofs = view.findViewById(R.id.outofs)
            outofsIn = view.findViewById(R.id.outofs_in)
            txtQuan = view.findViewById(R.id.txtQuan)
            minus = view.findViewById(R.id.minus)
            plus = view.findViewById(R.id.plus)
            progressBar = view.findViewById<View>(R.id.progressB) as ProgressBar
        }
    }

    companion object {
        private const val LIMIT = 6
    }

    init {
        dbcart = DatabaseHandler(context)
        //   sessionManagement = new SessionManagement(context);
        db = DatabaseHandler(context)
        val myFav = SharedPref.getFavListData(
            context
        )
        if (!TextUtils.isEmpty(myFav)) {
            val gson1 = Gson()
            myFavNew = gson1.fromJson(myFav, object : TypeToken<List<String?>?>() {}.type)
        }
    }
}