package Model;

/**
 * Created by User on 11/14/2016.
 */
public abstract class Smarty implements Simpleton, PersonType{
    String IQ = "0";
    public String getIQ() {
        return IQ ;
    }
    abstract String getIncome();
}
