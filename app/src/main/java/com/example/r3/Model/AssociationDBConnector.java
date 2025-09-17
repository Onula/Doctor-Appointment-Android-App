package com.example.r3.Model;

public class AssociationDBConnector {
    static DBManager dbMan = new DBManager();
    private static String myIP = dbMan.getMyIP();
    private static final String ASSOCIATION_PHP_SCRIPT_URL = "http://"+myIP+"/PHPDbServices/Association.php";
    private String actionName;
    private String url;


    //
    public boolean insertPhysiotherapy(String AFM, String name, String adress) {
        // Replacing with the current function name to understand the php which action to do
        actionName = "insertPhysiotherapy";
        url = ASSOCIATION_PHP_SCRIPT_URL+
                "?action="+actionName+
                "&PhysiotherapyAFM="+AFM+
                "&PhysiotherapyName="+name+
                "&PhysiotherapyAdress="+adress;

        // Build the insertPhysiotherapy request { buildRequest(..) }
        // Send the request and handle the response { getResponse(..) }
        return dbMan.getResponse(url);
    }

    //
    public boolean insertProvision(String Code, String Name, String Price, String Description ) {
        // Replacing with the current function name to understand the php which action to do
        actionName = "insertProvision";

        url = ASSOCIATION_PHP_SCRIPT_URL+
                "?action="+actionName+
                "&ProvisionID="+Code+
                "&ProvisionName="+Name+
                "&ProvisionPrice="+Price+
                "&ProvisionDescription="+Description;

        // Build the insertPhysiotherapy request { buildRequest(..) }
        // Send the request and handle the response { handleSendRequest(..) }
        return dbMan.getResponse(url);
    }
}
