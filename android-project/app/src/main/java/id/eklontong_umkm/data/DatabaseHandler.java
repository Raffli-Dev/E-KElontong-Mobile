package id.eklontong_umkm.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import id.eklontong_umkm.model.Cart;
import id.eklontong_umkm.model.CartReturSales;
import id.eklontong_umkm.model.Notification;
import id.eklontong_umkm.model.Order;
import id.eklontong_umkm.model.OrderReturSales;
import id.eklontong_umkm.model.Wishlist;

public class DatabaseHandler extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    private Context context;

    // Database Version
    private static final int DATABASE_VERSION = 3;

    // Database Name
    private static final String DATABASE_NAME = "markeet.db";

    // Table Name
    private static final String TABLE_NOTIFICATION = "notification";
    private static final String TABLE_WISH_LIST = "wish_list";
    private static final String TABLE_ORDER = "table_order";
    private static final String TABLE_ORDER_RETUR_SALES = "table_order_retur_sales";
    private static final String TABLE_CART = "cart";
    private static final String TABLE_CERT_RETUR_SALES = "cart_retur_sales";

    // Table Columns names TABLE_NOTIFICATION
    private static final String COL_NOTIF_ID = "COL_NOTIF_ID";
    private static final String COL_NOTIF_TITLE = "COL_NOTIF_TITLE";
    private static final String COL_NOTIF_CONTENT = "COL_NOTIF_CONTENT";
    private static final String COL_NOTIF_TYPE = "COL_NOTIF_TYPE";
    private static final String COL_NOTIF_OBJ_ID = "COL_NOTIF_OBJ_ID";
    private static final String COL_NOTIF_IMAGE = "COL_NOTIF_IMAGE";
    private static final String COL_NOTIF_READ = "COL_NOTIF_READ";
    private static final String COL_NOTIF_CREATED_AT = "COL_NOTIF_CREATED_AT";

    // Table Columns names TABLE_WISH_LIST
    private static final String COL_WISH_PRODUCT_ID = "COL_WISH_PRODUCT_ID";
    private static final String COL_WISH_NAME = "COL_WISH_NAME";
    private static final String COL_WISH_IMAGE = "COL_WISH_IMAGE";
    private static final String COL_WISH_CREATED_AT = "COL_WISH_CREATED_AT";

    // Table Columns names TABLE_ORDER
    private static final String COL_ORDER_ID = "COL_ORDER_ID";
    private static final String COL_ORDER_CODE = "COL_ORDER_CODE";
    private static final String COL_ORDER_TOTAL_FEES = "COL_ORDER_TOTAL_FEES";
    private static final String COL_ORDER_CREATED_AT = "COL_ORDER_CREATED_AT";
    private static final String COL_ORDER_STATUS = "COL_ORDER_STATUS";
    private static final String COL_ORDER_BUYER = "COL_ORDER_BUYER";
    private static final String COL_ORDER_PAYMENT_STATUS = "COL_ORDER_PAYMENT_STATUS";
    private static final String COL_ORDER_LAST_UPDATE = "COL_ORDER_LAST_UPDATE";

    // Table Columns names TABLE_CART
    private static final String COL_CART_ID = "COL_CART_ID";
    private static final String COL_CART_ORDER_ID = "COL_CART_ORDER_ID";
    private static final String COL_CART_PRODUCT_ID = "COL_CART_PRODUCT_ID";
    private static final String COL_CART_PRODUCT_NAME = "COL_CART_PRODUCT_NAME";
    private static final String COL_CART_IMAGE = "COL_CART_IMAGE";
    private static final String COL_CART_AMOUNT = "COL_CART_AMOUNT";
    private static final String COL_CART_STOCK = "COL_CART_STOCK";
    private static final String COL_CART_PRICE_ITEM = "COL_CART_PRICE_ITEM";
    private static final String COL_CART_CREATED_AT = "COL_CART_CREATED_AT";

    // Table Columns names TABLE_RETUR_PENJUALAN
    private static final String COL_CART_RETUR_SALES_ID = "COL_CART_RETUR_SALES_ID";
    private static final String COL_CART_RETUR_SALES_ORDER_ID = "COL_CART_RETUR_SALES_ORDER_ID";
    private static final String COL_CART_RETUR_SALES_PRODUCT_ID = "COL_CART_RETUR_SALES_PRODUCT_ID";
    private static final String COL_CART_RETUR_SALES_PRODUCT_NAME = "COL_CART_RETUR_SALES_PRODUCT_NAME";
    private static final String COL_CART_RETUR_SALES_IMAGE = "COL_CART_RETUR_SALES_IMAGE";
    private static final String COL_CART_RETUR_SALES_AMOUNT = "COL_CART_RETUR_SALES_AMOUNT";
    private static final String COL_CART_RETUR_SALES_STOCK = "COL_CART_RETUR_SALES_STOCK";
    private static final String COL_CART_RETUR_SALES_PRICE_ITEM = "COL_CART_RETUR_SALES_PRICE_ITEM";
    private static final String COL_CART_RETUR_SALES_CREATED_AT = "COL_CART_RETUR_SALES_CREATED_AT";
    private static final String COL_CART_RETUR_SALES_IDS = "COL_CART_RETUR_SALES_IDS";
    private static final String COL_CART_RETUR_SALES_QTY = "COL_CART_RETUR_SALES_QTY";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        this.db = getWritableDatabase();
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase d) {
        createTableNotification(d);
        createTableWishlist(d);
        createTableOrder(d);
        createTableCart(d);
        createTableCartReturSales(d);
    }

    private void createTableNotification(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NOTIFICATION + " ("
                + COL_NOTIF_ID + " INTEGER PRIMARY KEY, "
                + COL_NOTIF_TITLE + " TEXT, "
                + COL_NOTIF_CONTENT + " TEXT, "
                + COL_NOTIF_TYPE + " TEXT, "
                + COL_NOTIF_OBJ_ID + " NUMERIC, "
                + COL_NOTIF_IMAGE + " TEXT, "
                + COL_NOTIF_READ + " INTEGER DEFAULT 0, "
                + COL_NOTIF_CREATED_AT + " NUMERIC "
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    private void createTableWishlist(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_WISH_LIST + " ("
                + COL_WISH_PRODUCT_ID + " INTEGER PRIMARY KEY, "
                + COL_WISH_NAME + " TEXT, "
                + COL_WISH_IMAGE + " TEXT, "
                + COL_WISH_CREATED_AT + " NUMERIC "
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    private void createTableOrder(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_ORDER + " ("
                + COL_ORDER_ID + " INTEGER PRIMARY KEY, "
                + COL_ORDER_CODE + " TEXT, "
                + COL_ORDER_BUYER + " TEXT, "
                + COL_ORDER_TOTAL_FEES + " TEXT, "
                + COL_ORDER_CREATED_AT + " NUMERIC, "
                + COL_ORDER_LAST_UPDATE + " NUMERIC, "
                + COL_ORDER_STATUS + " TEXT ,"
                + COL_ORDER_PAYMENT_STATUS + " TEXT "
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    private void createTableCart(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_CART + " ("
                + COL_CART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_CART_ORDER_ID + " INTEGER, "
                + COL_CART_PRODUCT_ID + " INTEGER, "
                + COL_CART_PRODUCT_NAME + " TEXT, "
                + COL_CART_IMAGE + " TEXT, "
                + COL_CART_AMOUNT + " INTEGER, "
                + COL_CART_STOCK + " INTEGER, "
                + COL_CART_PRICE_ITEM + " NUMERIC, "
                + COL_CART_CREATED_AT + " NUMERIC "
                + ")";
        db.execSQL(CREATE_TABLE);
    }
    private void createTableCartReturSales(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_CERT_RETUR_SALES + " ("
                + COL_CART_RETUR_SALES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_CART_RETUR_SALES_ORDER_ID + " INTEGER, "
                + COL_CART_RETUR_SALES_PRODUCT_ID + " INTEGER, "
                + COL_CART_RETUR_SALES_PRODUCT_NAME + " TEXT, "
                + COL_CART_RETUR_SALES_IMAGE + " TEXT, "
                + COL_CART_RETUR_SALES_AMOUNT + " INTEGER, "
                + COL_CART_RETUR_SALES_STOCK + " INTEGER, "
                + COL_CART_RETUR_SALES_PRICE_ITEM + " NUMERIC, "
                + COL_CART_RETUR_SALES_CREATED_AT + " NUMERIC, "
                + COL_CART_RETUR_SALES_IDS + " INTEGER, "
                + COL_CART_RETUR_SALES_QTY + " INTEGER "
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("DB ", "onUpgrade " + oldVersion + " to " + newVersion);
        if (oldVersion == 1 && newVersion == 2){
            db.execSQL("ALTER TABLE "+TABLE_ORDER+" ADD COLUMN "+COL_ORDER_STATUS+" TEXT DEFAULT ''");
        }

        if (oldVersion == 2 && newVersion == 3){
            db.execSQL("ALTER TABLE "+TABLE_ORDER+" ADD COLUMN "+COL_ORDER_BUYER+" TEXT DEFAULT ''");
            db.execSQL("ALTER TABLE "+TABLE_ORDER+" ADD COLUMN "+COL_ORDER_LAST_UPDATE+" NUMERIC DEFAULT 0");
            db.execSQL("ALTER TABLE "+TABLE_ORDER+" ADD COLUMN "+COL_ORDER_PAYMENT_STATUS+" TEXT DEFAULT ''");
        }

    }

    public void truncateDB(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WISH_LIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
    public void saveNotification(Notification notification) {
        ContentValues values = getNotificationValue(notification);
        // Inserting or Update Row
        db.insertWithOnConflict(TABLE_NOTIFICATION, null, values, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public void saveWishlist(Wishlist wishlist) {
        ContentValues values = getWishlistValue(wishlist);
        // Inserting or Update Row
        db.insertWithOnConflict(TABLE_WISH_LIST, null, values, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public void saveOrder(Order order) {
        ContentValues values = getOrderValue(order);
        // Inserting or Update Row
        db.insertWithOnConflict(TABLE_ORDER, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        for (Cart c : order.cart_list) {
            saveCart(c);
        }
    }
    public void saveOrderReturSales(OrderReturSales order) {
        ContentValues values = getOrderReturSalesValue(order);
        // Inserting or Update Row
        db.insertWithOnConflict(TABLE_ORDER_RETUR_SALES, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        for (CartReturSales c : order.cart_list) {
            saveCartReturSales(c);
        }
    }

    public void saveOrders(List<Order> orders) {
        db.beginTransaction();
        try {
            for (Order order : orders) {
                ContentValues values = getOrderValue(order);
                db.update(TABLE_ORDER, values, COL_ORDER_ID + "=?", new String[]{order.id.toString()});
                clearCart(order.id);
                for (Cart c : order.cart_list) {
                    saveCart(c);
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void updateStatusOrder(String code, String status) {
        ContentValues newValues = new ContentValues();
        newValues.put(COL_ORDER_STATUS, status);
        String[] args = new String[]{code};
        db.update(TABLE_ORDER, newValues, COL_ORDER_CODE + "=?", args);
    }

    public void saveCart(Cart cart) {
        ContentValues values = getCartValue(cart);
        // Inserting or Update Row
        db.insertWithOnConflict(TABLE_CART, null, values, SQLiteDatabase.CONFLICT_REPLACE);
    }
    public void saveCartReturSales(CartReturSales cartReturSales) {
        ContentValues values = getCartReturSalesValue(cartReturSales);
        // Inserting or Update Row
        db.insertWithOnConflict(TABLE_CERT_RETUR_SALES, null, values, SQLiteDatabase.CONFLICT_REPLACE);
    }

    private ContentValues getNotificationValue(Notification model) {
        ContentValues values = new ContentValues();
        values.put(COL_NOTIF_ID, model.id);
        values.put(COL_NOTIF_TITLE, model.title);
        values.put(COL_NOTIF_CONTENT, model.content);
        values.put(COL_NOTIF_TYPE, model.type);
        values.put(COL_NOTIF_OBJ_ID, model.obj_id);
        values.put(COL_NOTIF_IMAGE, model.image);
        values.put(COL_NOTIF_READ, model.read ? 1 : 0);
        values.put(COL_NOTIF_CREATED_AT, model.created_at);
        return values;
    }

    private ContentValues getWishlistValue(Wishlist model) {
        ContentValues values = new ContentValues();
        values.put(COL_WISH_PRODUCT_ID, model.product_id);
        values.put(COL_WISH_NAME, model.name);
        values.put(COL_WISH_IMAGE, model.image);
        values.put(COL_WISH_CREATED_AT, model.created_at);
        return values;
    }

    private ContentValues getOrderValue(Order model) {
        ContentValues values = new ContentValues();
        values.put(COL_ORDER_ID, model.id);
        values.put(COL_ORDER_CODE, model.code);
        values.put(COL_ORDER_BUYER, model.buyer);
        values.put(COL_ORDER_TOTAL_FEES, model.total_fees);
        values.put(COL_ORDER_CREATED_AT, model.created_at);
        values.put(COL_ORDER_LAST_UPDATE, model.last_update);
        values.put(COL_ORDER_STATUS, model.status);
        values.put(COL_ORDER_PAYMENT_STATUS, model.payment_status);
        return values;
    }
    private ContentValues getOrderReturSalesValue(OrderReturSales model) {
        ContentValues values = new ContentValues();
        values.put(COL_ORDER_ID, model.id);
        values.put(COL_ORDER_CODE, model.code);
        values.put(COL_ORDER_BUYER, model.buyer);
        values.put(COL_ORDER_TOTAL_FEES, model.total_fees);
        values.put(COL_ORDER_CREATED_AT, model.created_at);
        values.put(COL_ORDER_LAST_UPDATE, model.last_update);
        values.put(COL_ORDER_STATUS, model.status);
        values.put(COL_ORDER_PAYMENT_STATUS, model.payment_status);
        return values;
    }

    private ContentValues getCartValue(Cart model) {
        ContentValues values = new ContentValues();
        values.put(COL_CART_ID, model.id);
        values.put(COL_CART_ORDER_ID, model.order_id);
        values.put(COL_CART_PRODUCT_ID, model.product_id);
        values.put(COL_CART_PRODUCT_NAME, model.product_name);
        values.put(COL_CART_IMAGE, model.image);
        values.put(COL_CART_AMOUNT, model.amount);
        values.put(COL_CART_STOCK, model.stock);
        values.put(COL_CART_PRICE_ITEM, model.price_item);
        values.put(COL_CART_CREATED_AT, model.created_at);
        return values;
    }
    private ContentValues getCartReturSalesValue(CartReturSales model) {
        ContentValues values = new ContentValues();
        values.put(COL_CART_RETUR_SALES_ID, model.id);
        values.put(COL_CART_RETUR_SALES_ORDER_ID, model.order_id);
        values.put(COL_CART_RETUR_SALES_PRODUCT_ID, model.product_id);
        values.put(COL_CART_RETUR_SALES_PRODUCT_NAME, model.product_name);
        values.put(COL_CART_RETUR_SALES_IMAGE, model.image);
        values.put(COL_CART_RETUR_SALES_AMOUNT, model.amount);
        values.put(COL_CART_RETUR_SALES_STOCK, model.stock);
        values.put(COL_CART_RETUR_SALES_PRICE_ITEM, model.price_item);
        values.put(COL_CART_RETUR_SALES_CREATED_AT, model.created_at);
        values.put(COL_CART_RETUR_SALES_IDS, model.sales_id);
        values.put(COL_CART_RETUR_SALES_QTY, model.sales_qty);
        return values;
    }

    public Notification getNotification(long id) {
        Notification obj = new Notification();
        String query = "SELECT * FROM " + TABLE_NOTIFICATION + " n WHERE n." + COL_NOTIF_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{id + ""});
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            obj = getNotificationByCursor(cursor);
        }
        return obj;
    }

    public Wishlist getWishlist(long id) {
        Wishlist obj = null;
        String query = "SELECT * FROM " + TABLE_WISH_LIST + " w WHERE w." + COL_WISH_PRODUCT_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{id + ""});
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            obj = getWishlistByCursor(cursor);
        }
        return obj;
    }

    public Order getOrder(long id) {
        Order obj = null;
        String query = "SELECT * FROM " + TABLE_ORDER + " o WHERE o." + COL_ORDER_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{id + ""});
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            obj = getOrderByCursor(cursor);
            obj.cart_list = getCartByOrderId(id);
        }
        return obj;
    }

    public Cart getCart(long product_id) {
        Cart obj = null;
        String query = "SELECT * FROM " + TABLE_CART + " c WHERE " + COL_CART_ORDER_ID + "=" + (-1) + " AND c." + COL_CART_PRODUCT_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{product_id + ""});
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            obj = getCartByCursor(cursor);
        }
        return obj;
    }

    public CartReturSales getCartReturSales(long product_id) {
        CartReturSales obj = null;
        String query = "SELECT * FROM " + TABLE_CERT_RETUR_SALES + " c WHERE " + COL_CART_RETUR_SALES_ORDER_ID + "=" + (-1) + " AND c." + COL_CART_RETUR_SALES_PRODUCT_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{product_id + ""});
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            obj = getCartReturSalesByCursor(cursor);
        }
        return obj;
    }

    private Notification getNotificationByCursor(Cursor cur) {
        Notification obj = new Notification();
        obj.id = cur.getLong(cur.getColumnIndex(COL_NOTIF_ID));
        obj.title = cur.getString(cur.getColumnIndex(COL_NOTIF_TITLE));
        obj.content = cur.getString(cur.getColumnIndex(COL_NOTIF_CONTENT));
        obj.type = cur.getString(cur.getColumnIndex(COL_NOTIF_TYPE));
        obj.obj_id = cur.getLong(cur.getColumnIndex(COL_NOTIF_OBJ_ID));
        obj.image = cur.getString(cur.getColumnIndex(COL_NOTIF_IMAGE));
        obj.read = cur.getInt(cur.getColumnIndex(COL_NOTIF_READ)) == 1;
        obj.created_at = cur.getLong(cur.getColumnIndex(COL_NOTIF_CREATED_AT));
        return obj;
    }

    private Wishlist getWishlistByCursor(Cursor cur) {
        Wishlist obj = new Wishlist();
        obj.product_id = cur.getLong(cur.getColumnIndex(COL_WISH_PRODUCT_ID));
        obj.name = cur.getString(cur.getColumnIndex(COL_WISH_NAME));
        obj.image = cur.getString(cur.getColumnIndex(COL_WISH_IMAGE));
        obj.created_at = cur.getLong(cur.getColumnIndex(COL_WISH_CREATED_AT));
        return obj;
    }

    private Order getOrderByCursor(Cursor cur) {
        Order obj = new Order();
        obj.id = cur.getLong(cur.getColumnIndex(COL_ORDER_ID));
        obj.code = cur.getString(cur.getColumnIndex(COL_ORDER_CODE));
        obj.buyer = cur.getString(cur.getColumnIndex(COL_ORDER_BUYER));
        obj.total_fees = cur.getString(cur.getColumnIndex(COL_ORDER_TOTAL_FEES));
        obj.created_at = cur.getLong(cur.getColumnIndex(COL_ORDER_CREATED_AT));
        obj.last_update = cur.getLong(cur.getColumnIndex(COL_ORDER_LAST_UPDATE));
        obj.status = cur.getString(cur.getColumnIndex(COL_ORDER_STATUS));
        obj.payment_status = cur.getString(cur.getColumnIndex(COL_ORDER_PAYMENT_STATUS));
        obj.cart_list = getCartByOrderId(obj.id);
        return obj;
    }

    private Cart getCartByCursor(Cursor cur) {
        Cart obj = new Cart();
        obj.id = cur.getLong(cur.getColumnIndex(COL_CART_ID));
        obj.order_id = cur.getLong(cur.getColumnIndex(COL_CART_ORDER_ID));
        obj.product_id = cur.getLong(cur.getColumnIndex(COL_CART_PRODUCT_ID));
        obj.product_name = cur.getString(cur.getColumnIndex(COL_CART_PRODUCT_NAME));
        obj.image = cur.getString(cur.getColumnIndex(COL_CART_IMAGE));
        obj.amount = cur.getInt(cur.getColumnIndex(COL_CART_AMOUNT));
        obj.stock = cur.getLong(cur.getColumnIndex(COL_CART_STOCK));
        obj.price_item = cur.getDouble(cur.getColumnIndex(COL_CART_PRICE_ITEM));
        obj.created_at = cur.getLong(cur.getColumnIndex(COL_CART_CREATED_AT));
        return obj;
    }
    private CartReturSales getCartReturSalesByCursor(Cursor cur) {
        CartReturSales obj = new CartReturSales();
        obj.id = cur.getLong(cur.getColumnIndex(COL_CART_RETUR_SALES_ID));
        obj.order_id = cur.getLong(cur.getColumnIndex(COL_CART_RETUR_SALES_ORDER_ID));
        obj.product_id = cur.getLong(cur.getColumnIndex(COL_CART_RETUR_SALES_PRODUCT_ID));
        obj.product_name = cur.getString(cur.getColumnIndex(COL_CART_RETUR_SALES_PRODUCT_NAME));
        obj.image = cur.getString(cur.getColumnIndex(COL_CART_RETUR_SALES_IMAGE));
        obj.amount = cur.getInt(cur.getColumnIndex(COL_CART_RETUR_SALES_AMOUNT));
        obj.stock = cur.getLong(cur.getColumnIndex(COL_CART_RETUR_SALES_STOCK));
        obj.price_item = cur.getDouble(cur.getColumnIndex(COL_CART_RETUR_SALES_PRICE_ITEM));
        obj.created_at = cur.getLong(cur.getColumnIndex(COL_CART_RETUR_SALES_CREATED_AT));
        obj.sales_id = cur.getLong(cur.getColumnIndex(COL_CART_RETUR_SALES_IDS));
        obj.sales_qty = cur.getLong(cur.getColumnIndex(COL_CART_RETUR_SALES_QTY));
        return obj;
    }

    public List<Notification> getNotificationsByPage(int limit, int offset) {
        List<Notification> items = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * FROM " + TABLE_NOTIFICATION + " n ORDER BY n." + COL_NOTIF_ID + " DESC LIMIT " + limit + " OFFSET " + offset + " ");
        Cursor cursor = db.rawQuery(sb.toString(), null);
        if (cursor.moveToFirst()) {
            items = getListNotificationsByCursor(cursor);
        }
        return items;
    }

    public List<Wishlist> getWishlistByPage(int limit, int offset) {
        List<Wishlist> items = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * FROM " + TABLE_WISH_LIST + " w ORDER BY w." + COL_WISH_CREATED_AT + " DESC LIMIT " + limit + " OFFSET " + offset + " ");
        Cursor cursor = db.rawQuery(sb.toString(), null);
        if (cursor.moveToFirst()) {
            items = getListWishlistByCursor(cursor);
        }
        return items;
    }

    public List<Order> getOrderList(int limit, int offset) {
        List<Order> items = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * FROM " + TABLE_ORDER + " o ORDER BY o." + COL_ORDER_ID + " DESC LIMIT " + limit + " OFFSET " + offset + " ");
        Cursor cursor = db.rawQuery(sb.toString(), null);
        if (cursor.moveToFirst()) {
            items = getListOrderByCursor(cursor);
        }
        return items;
    }

    public List<Cart> getActiveCartList() {
        List<Cart> items = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * FROM " + TABLE_CART + " c WHERE c." + COL_CART_ORDER_ID + "=" + (-1) + " ORDER BY c." + COL_CART_CREATED_AT + " DESC ");
        Cursor cursor = db.rawQuery(sb.toString(), null);
        if (cursor.moveToFirst()) {
            items = getListCartByCursor(cursor);
        }
        return items;
    }
    public List<CartReturSales> getActiveCarReturSalestList() {
        List<CartReturSales> items = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * FROM " + TABLE_CERT_RETUR_SALES + " c WHERE c." + COL_CART_RETUR_SALES_ORDER_ID + "=" + (-1) + " ORDER BY c." + COL_CART_RETUR_SALES_CREATED_AT + " DESC ");
        Cursor cursor = db.rawQuery(sb.toString(), null);
        if (cursor.moveToFirst()) {
            items = getListCartReturSalesByCursor(cursor);
        }
        return items;
    }

    public List<Cart> getCartByOrderId(long order_id) {
        List<Cart> items = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * FROM " + TABLE_CART + " c WHERE c." + COL_CART_ORDER_ID + "=" + order_id + " ORDER BY c." + COL_CART_CREATED_AT + " DESC ");
        Cursor cursor = db.rawQuery(sb.toString(), null);
        if (cursor.moveToFirst()) {
            items = getListCartByCursor(cursor);
        }
        return items;
    }

    private List<Notification> getListNotificationsByCursor(Cursor cur) {
        List<Notification> items = new ArrayList<>();
        if (cur.moveToFirst()) {
            do {
                items.add(getNotificationByCursor(cur));
            } while (cur.moveToNext());
        }
        return items;
    }

    private List<Wishlist> getListWishlistByCursor(Cursor cur) {
        List<Wishlist> items = new ArrayList<>();
        if (cur.moveToFirst()) {
            do {
                items.add(getWishlistByCursor(cur));
            } while (cur.moveToNext());
        }
        return items;
    }

    private List<Order> getListOrderByCursor(Cursor cur) {
        List<Order> items = new ArrayList<>();
        if (cur.moveToFirst()) {
            do {
                items.add(getOrderByCursor(cur));
            } while (cur.moveToNext());
        }
        return items;
    }

    private List<Cart> getListCartByCursor(Cursor cur) {
        List<Cart> items = new ArrayList<>();
        if (cur.moveToFirst()) {
            do {
                items.add(getCartByCursor(cur));
            } while (cur.moveToNext());
        }
        return items;
    }
    private List<CartReturSales> getListCartReturSalesByCursor(Cursor cur) {
        List<CartReturSales> items = new ArrayList<>();
        if (cur.moveToFirst()) {
            do {
                items.add(getCartReturSalesByCursor(cur));
            } while (cur.moveToNext());
        }
        return items;
    }

    public void deleteNotification(Long id) {
        db.delete(TABLE_NOTIFICATION, COL_NOTIF_ID + " = ?", new String[]{id.toString()});
    }

    // delete all records
    public void deleteNotification() {
        db.execSQL("DELETE FROM " + TABLE_NOTIFICATION);
    }

    public void deleteWishlist(Long id) {
        db.delete(TABLE_WISH_LIST, COL_WISH_PRODUCT_ID + " = ?", new String[]{id.toString()});
    }

    // delete all records
    public void deleteWishlist() {
        db.execSQL("DELETE FROM " + TABLE_WISH_LIST);
    }

    public void deleteOrder(Long order_id) {
        db.delete(TABLE_ORDER, COL_ORDER_ID + " = ?", new String[]{order_id.toString()});
        db.delete(TABLE_CART, COL_CART_ORDER_ID + " = ?", new String[]{order_id.toString()});
    }

    // delete all records
    public void deleteOrder() {
        db.execSQL("DELETE FROM " + TABLE_ORDER);
        db.execSQL("DELETE FROM " + TABLE_CART + " WHERE " + COL_CART_ORDER_ID + "<>-1");
    }

    public void deleteActiveCart(Long product_id) {
        db.delete(TABLE_CART, COL_CART_ORDER_ID + " = ? AND " + COL_CART_PRODUCT_ID + " = ?", new String[]{"-1", product_id.toString()});
    }
    public void deleteActiveCartReturSales(Long product_id) {
        db.delete(TABLE_CERT_RETUR_SALES, COL_CART_RETUR_SALES_ORDER_ID + " = ? AND " + COL_CART_RETUR_SALES_PRODUCT_ID + " = ?", new String[]{"-1", product_id.toString()});
    }

    // delete all records
    public void deleteActiveCart() {
        db.execSQL("DELETE FROM " + TABLE_CART + " WHERE " + COL_CART_ORDER_ID + "=-1");
    }
    public void deleteActiveCartReturSales() {
        db.execSQL("DELETE FROM " + TABLE_CERT_RETUR_SALES + " WHERE " + COL_CART_RETUR_SALES_ORDER_ID + "=-1");
    }

    public void clearCart(Long order_id) {
        db.delete(TABLE_CART, COL_CART_ORDER_ID + " = ?", new String[]{order_id.toString()});
    }

    public int getNotificationSize() {
        int count = (int) DatabaseUtils.queryNumEntries(db, TABLE_NOTIFICATION);
        return count;
    }

    public int getUnreadNotificationSize() {
        String countQuery = "SELECT n." + COL_NOTIF_ID + " FROM " + TABLE_NOTIFICATION + " n WHERE n." + COL_NOTIF_READ + "=0";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public int getWishlistSize() {
        int count = (int) DatabaseUtils.queryNumEntries(db, TABLE_WISH_LIST);
        return count;
    }

    public int getOrderSize() {
        int count = (int) DatabaseUtils.queryNumEntries(db, TABLE_ORDER);
        return count;
    }

    public int getCartSize() {
        int count = (int) DatabaseUtils.queryNumEntries(db, TABLE_CART);
        return count;
    }

    public boolean isOrderExist(String code) {
        String countQuery = "SELECT o." + COL_ORDER_ID + " FROM " + TABLE_ORDER + " o WHERE o." + COL_ORDER_CODE + "='" + code+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    public int getActiveCartSize() {
        String countQuery = "SELECT c." + COL_CART_ID + " FROM " + TABLE_CART + " c WHERE c." + COL_CART_ORDER_ID + "=" + (-1);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
}
