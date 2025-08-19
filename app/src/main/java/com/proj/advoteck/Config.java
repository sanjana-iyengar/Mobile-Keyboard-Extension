package com.proj.advoteck;

public class Config {

    public static String ipAddress = "192.168.43.253";

    public static String registerDependent = "http://"+ipAddress+"/Advoteck/register_dependent.php";
    public static String registerCareGiver = "http://"+ipAddress+"/Advoteck/register_caregiver.php";
    public static String loginCareGiver = "http://"+ipAddress+"/Advoteck/login_caregiver.php";
    public static String loginDependent = "http://"+ipAddress+"/Advoteck/login_dependent.php";
    //requestForm
    public static String requestForm = "http://"+ipAddress+"/Advoteck/requestForm.php";
    public static String viewRequests = "http://"+ipAddress+"/Advoteck/getAllRequests.php";

}
