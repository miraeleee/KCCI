package com.test.singleton;

public class CompanyTest {
    public static void main(String[] args) {
        Company myCompany1 = Company.getInstatnce();
        Company myCompany2 = Company.getInstatnce();
        System.out.println(myCompany1 == myCompany2);
    }
}
class Company{
    private static Company instance = new Company();
    private Company(){}

    public static Company getInstatnce(){
        if(instance == null)
        instance = new Company();

        return instance;
    }
}
