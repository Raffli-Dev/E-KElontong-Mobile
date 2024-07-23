package id.eklontong_umkm.model;

import java.io.Serializable;
import java.util.List;

public class UntungRugi implements Serializable {
    public String opening_stock_price;
    public String purchase_tax_amt;
    public String pur_total;
    public String pur_other_charges_amt;
    public String purchase_discount_amt;
    public String purchase_paid_amount;
    public String purchase_return_tax_amt;
    public String pur_return_total;
    public String pur_return_other_charges_amt;
    public String purchase_return_discount_amt;
    public String purchase_return_paid_amount;
    public String sales_tax_amt;
    public String sal_other_charges_amt;
    public String sales_discount_amt;
    public String sal_total;
    public String sales_paid_amount;
    public String sales_return_tax_amt;
    public String sal_return_total;
    public String sal_return_other_charges_amt;
    public String sales_return_discount_amt;
    public String sales_return_paid_amount;
    public String exp_total;
    public String purchase_due_total;
    public String purchase_return_due_total;
    public String sales_due_total;
    public String sales_return_due_total;
    public String net_sales;
    public String sales_return_total;
    public String gross_profit;
    public String tot_net_profit;
    public String total_purchase_price;
    public String total_price_per_unit;
    public String total_laba_kotor;
    public String laba_usaha;
    public List<Pengeluaran> expense_list;
}
