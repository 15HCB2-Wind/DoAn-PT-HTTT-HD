using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

public class Configs
{
    public const string CONNECTION_STRING = @"Server=CHIPHONG\SQLEXPRESS_2012;Database=Service_AnimalCare;Trusted_Connection=True;";

    public const bool DEBUG_MODE = false;

    public const string TOKEN_PASSWORD  = "&YH*hf4JEUFHKEKLRJH93#$i";

    public const string CHECK_TOKEN = "http://localhost:30000/account/checktoken";

    public const string AREA_ID = "HCM";

    public const string UPDATE_COWSTATE = "http://localhost:8080/Service_Management/webresources/Bo/updateState";

    public const string UPDATE_MILK = "http://localhost:8080/Service_Management/webresources/KhoSua/updateMilk";
}
