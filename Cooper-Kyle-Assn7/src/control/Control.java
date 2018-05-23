package control;


import Model.*;

import java.util.ArrayList;

/**
 * Created by User on 11/14/2016.
 */
public class Control {

    public ArrayList<PersonType> list = new ArrayList<PersonType>();

    public Control()
    {
        createNewContractWorker("Steve","9/3=3","60000","99","Mmmmm.... Stars","8");
        createNewContractWorker("Barbra","20/5=4","75000","105","Astronomy's like a box of chocolates...", "6");
        createNewHourlyWorker("Gale","5*1=1","45000","1589","85","Bears, Beats, Battlestar Galactica");
        createNewHourlyWorker("Jeff", "74*22=1628", "65000", "1400","93", "I choose you electrical mouse!!!");
        createNewHobbit("Frodo", "1+19=3 episodes","No, Sam!!!", "0");
        createNewHobbit("Damian the Stout","7+9=16","Fear my gardening hoe... err... i mean spear!!", "3569");
    }

    public void createNewHobbit(String name, String math, String say, String carrots)
    {
        Hobbit hobbit = new Hobbit(name, math, say, carrots);
        list.add(hobbit);
    }

    public void createNewContractWorker(String name, String math, String income, String pIQ, String say, String contracts)
    {
        ContractWorker cWorker = new ContractWorker(name, math, income, pIQ, say, contracts);
        list.add(cWorker);
    }

    public void createNewHourlyWorker(String name, String math, String income, String hours, String pIQ, String say)
    {
        HourlyWorker hWorker = new HourlyWorker(name, math, income, hours, pIQ, say);
        list.add(hWorker);
    }

    public String getMathList(ArrayList<PersonType> array){
        String ret = "";
        for (PersonType p: array) {
            if (p instanceof Hobbit)
                ret += ((Hobbit) p).getName() + ",\t " + p.getPersonType() + ",\t " + ((Hobbit) p).doMath() + "\n";
            else if (p instanceof ContractWorker)
                ret += ((ContractWorker) p).getName() + ",\t " + p.getPersonType() + ",\t " + ((ContractWorker) p).doMath() + "\n";
            else if (p instanceof HourlyWorker)
                ret += ((HourlyWorker) p).getName() + ",\t " + p.getPersonType() + ",\t " + ((HourlyWorker) p).doMath() + "\n";
        }
        return ret;
    }
    public String getIncomeList(ArrayList<PersonType> array){
        String ret = "";
        for (PersonType p: array) {
            if (p instanceof ContractWorker)
                ret += ((ContractWorker) p).getName() + ",\t " + p.getPersonType() + ",\t " + ((ContractWorker) p).getIncome() + "\n";
            else if (p instanceof HourlyWorker)
                ret += ((HourlyWorker) p).getName() + ",\t " + p.getPersonType() + ",\t " + ((HourlyWorker) p).getIncome() + "\n";
        }
        return ret;
    }
    public String getHoursList(ArrayList<PersonType> array){
        String ret = "";
        for (PersonType p : array) {
            if (p instanceof HourlyWorker)
                ret += ((HourlyWorker) p).getName() + ",\t " + p.getPersonType() + ",\t " + ((HourlyWorker) p).getHoursWorked() + "\n";
        }
        return ret;
    }
    public String getIQList(ArrayList<PersonType> array){
        String ret = "";
        for (PersonType p : array) {
            if (p instanceof ContractWorker)
                ret += ((ContractWorker) p).getName() + ",\t " + p.getPersonType() + ",\t " + ((Smarty) p).getIQ() + "\n";
            else if (p instanceof HourlyWorker)
                ret += ((HourlyWorker) p).getName() + ",\t " + p.getPersonType() + ",\t " + ((Smarty) p).getIQ() + "\n";
        }
        return ret;
    }
    public String getSayList(ArrayList<PersonType> array){
        String ret = "";
        for (PersonType p : array) {
            if (p instanceof Hobbit)
                ret += ((Hobbit) p).getName() + ",\t " + p.getPersonType() + ",\t " + ((Hobbit) p).saySomethingSmart() + "\n";
            else if (p instanceof ContractWorker)
                ret += ((ContractWorker) p).getName() + ",\t " + p.getPersonType() + ",\t " + ((ContractWorker) p).saySomethingSmart() + "\n";
            else if (p instanceof HourlyWorker)
                ret += ((HourlyWorker) p).getName() + ",\t " + p.getPersonType() + ",\t " + ((HourlyWorker) p).saySomethingSmart() + "\n";
        }
        return ret;
    }
    public String getCarrotsList(ArrayList<PersonType> array){
        String ret = "";
        for (PersonType p : array) {
            if (p instanceof Hobbit)
                ret += ((Hobbit) p).getName() + ",\t " + p.getPersonType() + ",\t " + ((Hobbit) p).getCarrotsPicked() + "\n";
        }
        return ret;
    }
    public String getContractsList(ArrayList<PersonType> array){
        String ret = "";
        for(PersonType p : array) {
            if (p instanceof ContractWorker)
                ret += ((ContractWorker) p).getName() + ",\t " + p.getPersonType() + ",\t " + ((ContractWorker) p).getContractsCompleted() + "\n";
        }
        return ret;
    }

    private String getType(PersonType p)
    {
        if (p instanceof Hobbit) return "Hobbit ";
        else if (p instanceof ContractWorker) return "Contract Worker ";
        else if (p instanceof HourlyWorker)return "Hourly Worker ";
        else return "Unknown";
    }

}

