package Model;

/**
 * Created by User on 11/14/2016.
 */
public class ContractWorker extends Smarty{

    private String name;
    private String math;
    private String income;
    private String say;
    private String contracts;

    public ContractWorker(String n, String m, String i, String pIQ, String s, String c)
    {
        name  = n;
        math = m;
        income = i;
        IQ = pIQ;
        say = s;
        contracts = c;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String doMath() {
        //division
        return math;
    }

    @Override
    public String saySomethingSmart() {
        //astronomy
        return say;
    }

    @Override
    public String getPersonType() {
        return "Contract Worker";
    }

    @Override
    public String getIncome() {
        return income;
    }
    public String getContractsCompleted()
    {
        return contracts;
    }
}
