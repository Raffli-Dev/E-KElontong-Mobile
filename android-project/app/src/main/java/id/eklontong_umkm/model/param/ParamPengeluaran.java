package id.eklontong_umkm.model.param;

import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ParamPengeluaran implements Serializable {
    public String id;
    public String expense_date;
    public String reference_no;
    public String expense_for;
    public String expense_amt;
    public String note;
    public String company_id;
    public String users_id;

    public ParamPengeluaran() {
    }

    public ParamPengeluaran(
            String expense_date,
            String reference_no,
            String expense_for,
            String expense_amt,
            String note,
            String company_id,
            String users_id
    ) {
        this.expense_date = expense_date;
        this.reference_no = reference_no;
        this.expense_for = expense_for;
        this.expense_amt = expense_amt;
        this.note = note;
        this.company_id = company_id;
        this.users_id = users_id;
    }

    public Map<String, String> getParams(){
        Map<String, String> param = new HashMap<>();
        if(!TextUtils.isEmpty(id)) param.put("id", id);
        if(!TextUtils.isEmpty(expense_date)) param.put("expense_date", expense_date);
        if(!TextUtils.isEmpty(reference_no)) param.put("reference_no", reference_no);
        if(!TextUtils.isEmpty(expense_for)) param.put("expense_for", expense_for);
        if(!TextUtils.isEmpty(expense_amt)) param.put("expense_amt", expense_amt);
        if(!TextUtils.isEmpty(note)) param.put("note", note);
        if(!TextUtils.isEmpty(company_id)) param.put("company_id", company_id);
        if(!TextUtils.isEmpty(users_id)) param.put("users_id", users_id);
        return param;
    }
}
