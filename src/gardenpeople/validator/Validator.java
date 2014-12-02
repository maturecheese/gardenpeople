package gardenpeople.validator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mark-i5 on 29/11/2014.
 */
public class Validator {

    protected ArrayList<String> errors;
    protected String model = "default";
    public Validator(String model){
        this.model = model;
        this.errors = new ArrayList<String>();
    }

    public ArrayList<String> getErrors(){
        return this.errors;
    }

    public String getConfirmationWithTime (){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return  "sucessfully saved your " + model + " at " + dateFormat.format(date);

    }

    public void setModel(String model) {
        this.model = model;
    }
}
