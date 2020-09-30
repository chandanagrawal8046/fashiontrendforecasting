/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fashion.services;
import java.net.InetAddress;
/**
 *
 * @author sharayu
 */
public class MachineInfo {
    
    private String hostname;
    private String ipaddress;
    
    public MachineInfo()
    {
        hostname="";
        ipaddress="";
        try
        {
            getInfo();
        }
        catch(Exception ex){}
    }
    
    private void getInfo() throws Exception {
        try
        {
            InetAddress addr = InetAddress.getLocalHost();
            System.out.println("Local HostAddress: "+addr.getHostAddress());
            ipaddress=String.valueOf(addr.getHostAddress());
            hostname = addr.getHostName();
            System.out.println("Local host name: "+hostname);
        }
        catch(Exception ex){System.out.println("Error : "+ex.getMessage());}      
   }

    public String getHostname() {
        return hostname;
    }

    public String getIpaddress() {
        return ipaddress;
    }
}

