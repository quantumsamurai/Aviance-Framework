/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qs.frc.network;

import com.sun.squawk.io.j2me.socket.Protocol;
import javax.microedition.io.Connector;


/**
 *
 * @author pawel
 */
public class ConnectionManager {
    
    
    /**Attempts to establish a connection to the server and returns a AvianceConnection
     * with connected socket
     * 
     * @param address server address in the format "ip:port"
     * @return NULL on failure
     */
    public static AvianceConnection connectTo(String address){
        try{
            String temp = "socket://";
            temp += address;
            
            return new AvianceConnection((Protocol) Connector.open(temp));
        }
        catch(Exception e){
            return null;
        }
    }
}
