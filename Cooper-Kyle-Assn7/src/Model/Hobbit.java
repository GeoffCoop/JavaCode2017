package Model;

/**
 * Created by User on 11/14/2016.
 */
public class Hobbit implements PersonType, Simpleton {

    private String name;
    private String math;
    private String say;
    private String carrots;

    public Hobbit(String n, String m, String s, String c)
    {
        name = n;
        math = m;
        say = s;
        carrots = c;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String doMath() {
        //addition
        return math;
    }

    @Override
    public String saySomethingSmart() {
        //gardening
        return say;
    }

    @Override
    public String getPersonType() {
        return "Hobbit";
    }
    public String getCarrotsPicked()
    {
        return carrots;
    }

}
