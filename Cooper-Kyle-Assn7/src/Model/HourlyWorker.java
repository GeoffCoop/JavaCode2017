package Model;

/**
 * Created by User on 11/14/2016.
 */
public class HourlyWorker extends Smarty {

    private String name;
    private String math;
    private String income;
    private String hours;
    private String say;

    public HourlyWorker(String n, String m, String i, String h, String pIQ, String s){
        name = n;
        math = m;
        income = i;
        hours = h;
        IQ = pIQ;
        say =s;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String doMath() {
        //multiplication
        return math;
    }

    @Override
    public String saySomethingSmart() {
        //animals
        return say;
    }

    @Override
    public String getPersonType() {
        return "Hourly Worker";
    }

    @Override
    public String getIncome() {
        return income;
    }
    public String getHoursWorked()
    {
       return hours;
    }
}
