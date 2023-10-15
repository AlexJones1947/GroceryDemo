package com.app.grocerydemo.kotlin_demo.db

import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues
import android.content.Context
import java.lang.Exception
import java.util.ArrayList
import java.util.HashMap

class DatabaseHandler(context: Context?) : SQLiteOpenHelper(context, DBNAME, null, DBVERSION) {
    private var db: SQLiteDatabase? = null
    private val texConstKey = " TEXT NOT NULL, "
    private val texConstKey1 = " TEXT, "
    private val doubConstKey = " DOUBLE NOT NULL, "
    private val selectContKey = "Select *  from "
    private val whereConstKey = " where "
    override fun onCreate(db: SQLiteDatabase) {
        this.db = db
        val exe = ("CREATE TABLE IF NOT EXISTS " + CART_TABLE
                + "(" + VARIENT_ID + " integer primary key, "
                + COLUMN_QTY + " DOUBLE NOT NULL,"
                + COLUMN_IMAGE + texConstKey
                + PRODUCT_ID + texConstKey
                + COLUMN_NAME + texConstKey
                + COLUMN_PRICE + doubConstKey
                + COLUMN_UNIT_VALUE + doubConstKey
                + COLUMN_UNIT + doubConstKey
                + COLUMN_REWARDS + doubConstKey
                + COLUMN_INCREAMENT + doubConstKey
                + COLUMN_STOCK + doubConstKey
                + COLUMN_TITLE + texConstKey
                + COLUMN_FAV + texConstKey1
                + COLUMN_DESCRIPTION + " TEXT NOT NULL "
                + ")")
        db.execSQL(exe)
    }

    fun setCart(map: HashMap<String, String>, qty: Int): Boolean {
        db = writableDatabase
        return if (isInCart(map[VARIENT_ID])) {
            db!!.execSQL("update " + CART_TABLE + " set " + COLUMN_QTY + " = '" + qty + "' where " + VARIENT_ID + "=" + map[VARIENT_ID])
            db!!.close()
            false
        } else {
            val values = ContentValues()
            values.put(
                VARIENT_ID,
                map[VARIENT_ID]
            )
            values.put(COLUMN_QTY, qty)
            values.put(
                COLUMN_IMAGE,
                map[COLUMN_IMAGE]
            )
            values.put(
                PRODUCT_ID,
                map[PRODUCT_ID]
            )
            values.put(
                COLUMN_INCREAMENT,
                map[COLUMN_INCREAMENT]
            )
            values.put(
                COLUMN_NAME,
                map[COLUMN_NAME]
            )
            values.put(
                COLUMN_PRICE,
                map[COLUMN_PRICE]
            )
            values.put(
                COLUMN_REWARDS,
                map[COLUMN_REWARDS]
            )
            values.put(
                COLUMN_UNIT_VALUE,
                map[COLUMN_UNIT_VALUE]
            )
            values.put(
                COLUMN_UNIT,
                map[COLUMN_UNIT]
            )
            values.put(
                COLUMN_STOCK,
                map[COLUMN_STOCK]
            )
            values.put(
                COLUMN_TITLE,
                map[COLUMN_TITLE]
            )
            values.put(
                COLUMN_FAV,
                map[COLUMN_FAV]
            )
            values.put(
                COLUMN_DESCRIPTION,
                map[COLUMN_DESCRIPTION]
            )
            db!!.insert(
                CART_TABLE,
                null,
                values
            )
            db!!.close()
            true
        }
    }

    fun updatePrice(map: Map<String?, String>, price: Int) {
        db = writableDatabase
        if (isInCart(map[VARIENT_ID])) {
            db!!.execSQL("update " + CART_TABLE + " set " + COLUMN_PRICE + " = '" + price + "' where " + VARIENT_ID + "=" + map[VARIENT_ID])
            db!!.close()
        } else {
            db!!.close()
        }
    }

    fun updateFav(map: Map<String?, String>, fav: String): Boolean {
        db = writableDatabase
        return if (isInCart(map[VARIENT_ID])) {
            db!!.execSQL("update " + CART_TABLE + " set " + COLUMN_FAV + " = '" + fav + "' where " + VARIENT_ID + "=" + map[VARIENT_ID])
            db!!.close()
            false
        } else {
            db!!.close()
            true
        }
    }

    fun getFav(id: String): String {
        db = readableDatabase
        val qry = selectContKey + CART_TABLE + whereConstKey + VARIENT_ID + " = " + id
        val cursor = db!!.rawQuery(qry, null)
        cursor.moveToFirst()
        return cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_QTY))
    }

    fun isInCart(id: String?): Boolean {
        db = readableDatabase
        val qry = selectContKey + CART_TABLE + whereConstKey + VARIENT_ID + " = " + id
        val cursor = db!!.rawQuery(qry, null)
        cursor.moveToFirst()
        return cursor.count > 0
    }

    fun getCartItemQty(id: String): String {
        db = readableDatabase
        val qry = selectContKey + CART_TABLE + whereConstKey + VARIENT_ID + " = " + id
        val cursor = db!!.rawQuery(qry, null)
        cursor.moveToFirst()
        return cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_QTY))
    }

    fun getInCartItemQty(id: String): String {
        return if (isInCart(id)) {
            db = readableDatabase
            val qry =
                selectContKey + CART_TABLE + whereConstKey + VARIENT_ID + " = " + id
            val cursor = db!!.rawQuery(qry, null)
            cursor.moveToFirst()
            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_QTY))
        } else {
            "0.0"
        }
    }

    fun getInCartItemQtys(id: String): String {
        return if (isInCart(id)) {
            db = readableDatabase
            val qry =
                selectContKey + CART_TABLE + whereConstKey + VARIENT_ID + " = " + id
            val cursor = db!!.rawQuery(qry, null)
            cursor.moveToFirst()
            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_QTY))
        } else {
            "0"
        }
    }

    val cartCount: Int
        get() {
            var count = 0
            var inExp = false
            db = readableDatabase
            val qry = selectContKey + CART_TABLE
            var cursor = db!!.rawQuery(qry, null)
            try {
                count = cursor.count
            } catch (e: Exception) {
                inExp = true
                e.printStackTrace()
            } finally {
                cursor.close()
                if (inExp) {
                    db = readableDatabase
                    cursor = db!!.rawQuery(qry, null)
                    count = cursor.count
                    cursor.close()
                }
            }
            return count
        }
    val totalAmount: String
        get() {
            db = readableDatabase
            val qry =
                "Select SUM(" + COLUMN_QTY + " * " + COLUMN_PRICE + ") as total_amount  from " + CART_TABLE
            val cursor = db!!.rawQuery(qry, null)
            cursor.moveToFirst()
            val total = cursor.getString(cursor.getColumnIndexOrThrow("total_amount"))
            return total ?: "0"
        }
    val cartAll: List<HashMap<String, String>>
        get() {
            val list: MutableList<HashMap<String, String>> = ArrayList()
            db = readableDatabase
            val qry = selectContKey + CART_TABLE
            val cursor = db!!.rawQuery(qry, null)
            cursor.moveToFirst()
            for (i in 0 until cursor.count) {
                val map = HashMap<String, String>()
                map[VARIENT_ID] =
                    cursor.getString(cursor.getColumnIndexOrThrow(VARIENT_ID))
                map[COLUMN_QTY] =
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_QTY))
                map[COLUMN_IMAGE] =
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE))
                map[PRODUCT_ID] =
                    cursor.getString(cursor.getColumnIndexOrThrow(PRODUCT_ID))
                map[COLUMN_NAME] =
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
                map[COLUMN_PRICE] =
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRICE))
                map[COLUMN_REWARDS] =
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_REWARDS))
                map[COLUMN_UNIT_VALUE] =
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_UNIT_VALUE))
                map[COLUMN_UNIT] =
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_UNIT))
                map[COLUMN_INCREAMENT] =
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_INCREAMENT))
                map[COLUMN_STOCK] =
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STOCK))
                map[COLUMN_TITLE] =
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
                map[COLUMN_FAV] =
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FAV))
                map[COLUMN_DESCRIPTION] =
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION))
                list.add(map)
                cursor.moveToNext()
            }
            return list
        }
    val columnRewards: String
        get() {
            db = readableDatabase
            val qry = "SELECT rewards FROM " + CART_TABLE
            val cursor = db!!.rawQuery(qry, null)
            cursor.moveToFirst()
            val reward = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_REWARDS))
            return reward ?: "0"
        }

    fun clearCart() {
        db = readableDatabase
        db!!.execSQL("delete from " + CART_TABLE)
    }

    fun removeItemFromCart(id: String) {
        db = readableDatabase
        db!!.execSQL("delete from " + CART_TABLE + whereConstKey + VARIENT_ID + " = " + id)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    companion object {
        const val CART_TABLE = "cart"
        const val VARIENT_ID = "varient_id"
        const val PRODUCT_ID = "product_id"
        const val COLUMN_QTY = "qty"
        const val COLUMN_IMAGE = "product_image"
        const val COLUMN_NAME = "product_name"
        const val COLUMN_PRICE = "price"
        const val COLUMN_REWARDS = "rewards"
        const val COLUMN_INCREAMENT = "increament"
        const val COLUMN_UNIT_VALUE = "unit_value"
        const val COLUMN_UNIT = "unit"
        const val COLUMN_STOCK = "stock"
        const val COLUMN_TITLE = "title"
        const val COLUMN_FAV = "fav"
        const val COLUMN_DESCRIPTION = "product_description"
        private const val DBNAME = "demhynnjf"
        private const val DBVERSION = 2
    }
}