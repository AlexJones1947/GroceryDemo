package com.app.grocerydemo.kotlin_demo.Adapter

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.grocerydemo.R
import com.app.grocerydemo.interfaces.Categorygridquantity
import com.app.grocerydemo.kotlin_demo.db.DatabaseHandler
import com.app.grocerydemo.kotlin_demo.util.SharedPref
import com.app.grocerydemo.model.NewCartModel
import com.app.grocerydemo.model.NewCategoryDataModel
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.lang.IndexOutOfBoundsException
import java.text.DecimalFormat
import java.util.ArrayList
import java.util.HashMap

class CategoryGridAdapter(
    categoryGridList: List<NewCategoryDataModel>,
    context: Context?,
    categorygridquantity: Categorygridquantity
) : RecyclerView.Adapter<CategoryGridAdapter.MyViewHolder?>() {
    var context: Context? = null
    var categorygridquantity: Categorygridquantity
    private val categoryDataModels: List<NewCategoryDataModel>
    private var dbcart: DatabaseHandler

    //  private SessionManagement sessionManagement;
    private val varientKey = "varient_id"
    private var dFormat: DecimalFormat? = null
    var myFavNew: List<String> = ArrayList()
    var newCartModelList: List<NewCartModel> = ArrayList<NewCartModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.layout_category_list, parent, false)
        context = parent.getContext()
        dbcart = DatabaseHandler(context)
        //        if (sessionManagement == null) {
//            sessionManagement = new SessionManagement(context);
//        }
        dFormat = DecimalFormat("#.##")
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
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

    private fun updateMultiply(pos: Int, i: Int) {
        val map = HashMap<String, String>()
        map[varientKey] = categoryDataModels[pos].getVarientId()
        map["product_name"] = categoryDataModels[pos].getProductName()
        map["product_id"] = categoryDataModels[pos].getProductId()
        map["category_id"] = categoryDataModels[pos].getProductId()
        map["title"] = categoryDataModels[pos].getProductName()
        map["price"] = categoryDataModels[pos].getPrice()
        map["mrp"] = categoryDataModels[pos].getMrp()
        map["product_image"] = categoryDataModels[pos].getVarientImage()
        map["status"] = "0"
        map["in_stock"] = ""
        map["unit_value"] = categoryDataModels[pos].getQuantity()
        map["unit"] =
            if (categoryDataModels[pos].getUnit() != null) categoryDataModels[pos].getUnit() else ""
        map["increament"] = "0"
        map["rewards"] = "0"
        map["stock"] = categoryDataModels[pos].getStock()
        map["product_description"] = categoryDataModels[pos].getDescription()
        if (i > 0) {
            dbcart.setCart(map, i)
        } else {
            dbcart.removeItemFromCart(map[varientKey]!!)
        }
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val preferences: SharedPreferences =
                    context!!.getSharedPreferences("Grocery", Context.MODE_PRIVATE)
                preferences.edit().putInt("cardqnty", dbcart.cartCount).apply()
                categorygridquantity.onCartItemAddOrMinus()
            }
        } catch (e: IndexOutOfBoundsException) {
            Log.d("qwer", e.toString())
        }
    }


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var prodNAme: TextView
        var pDescrptn: TextView
        var pQuan: TextView
        var pPrice: TextView
        var pdiscountOff: TextView
        var pMrp: TextView
        var minus: TextView
        var plus: TextView
        var txtQuan: TextView
        var txtUnitvalue: TextView
        var currencyIndicator: TextView
        var currencyIndicator2: TextView
        var image: ImageView
        var iv_like_unlike: ImageView
        var btnAdd: LinearLayout
        var llAddQuan: LinearLayout
        var outofsIn: LinearLayout
        var outofs: LinearLayout
        var rlQuan: LinearLayout
        var progressBar: ProgressBar

        init {
            prodNAme = view.findViewById<TextView>(R.id.txt_pName)
            currencyIndicator = view.findViewById<TextView>(R.id.currency_indicator)
            currencyIndicator2 = view.findViewById<TextView>(R.id.currency_indicator_2)
            pDescrptn = view.findViewById<TextView>(R.id.txt_pInfo)
            pQuan = view.findViewById<TextView>(R.id.txt_unit)
            pPrice = view.findViewById<TextView>(R.id.txt_Pprice)
            image = view.findViewById(R.id.prodImage)
            iv_like_unlike = view.findViewById(R.id.iv_like_unlike)
            pdiscountOff = view.findViewById<TextView>(R.id.txt_discountOff)
            pMrp = view.findViewById<TextView>(R.id.txt_Mrp)
            rlQuan = view.findViewById<LinearLayout>(R.id.rlQuan)
            btnAdd = view.findViewById<LinearLayout>(R.id.btn_Add)
            llAddQuan = view.findViewById<LinearLayout>(R.id.ll_addQuan)
            txtQuan = view.findViewById<TextView>(R.id.txtQuan)
            minus = view.findViewById<TextView>(R.id.minus)
            plus = view.findViewById<TextView>(R.id.plus)
            txtUnitvalue = view.findViewById<TextView>(R.id.txt_unitvalue)
            llAddQuan = view.findViewById<LinearLayout>(R.id.ll_addQuan)
            outofsIn = view.findViewById<LinearLayout>(R.id.outofs_in)
            outofs = view.findViewById<LinearLayout>(R.id.outofs)
            progressBar = itemView.findViewById<ProgressBar>(R.id.progressB)
        }
    }

    init {
        categoryDataModels = categoryGridList
        dbcart = DatabaseHandler(context)
        // this.sessionManagement = new SessionManagement(context);
        this.categorygridquantity = categorygridquantity
        val myFav = SharedPref.getFavListData(context)
        if (!TextUtils.isEmpty(myFav)) {
            val gson1 = Gson()
            myFavNew = gson1.fromJson<List<String>>(
                myFav,
                object : TypeToken<List<String?>?>() {}.getType()
            )
        }
    }

    override fun getItemCount(): Int {
        return 10
    }
}