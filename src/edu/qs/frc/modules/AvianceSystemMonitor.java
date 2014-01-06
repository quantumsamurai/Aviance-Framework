/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qs.frc.modules;

import edu.qs.frc.threading.AvianceThread;
import edu.qs.frc.threading.AvianceThreadInterruptedException;
import edu.qs.frc.threading.AvianceThreadManager;
import java.util.Hashtable;
import java.util.Vector;

/** AvianceSystemMonitor servers as a system log for all modules on the robot as
 * well as a debug interface in which it can poll modules for errors or other data
 * The system monitor may also gather data such as alliance name, color, team number,
 * battery voltage and match time 
 *
 * @author pawel
 */
public class AvianceSystemMonitor extends AvianceThread{
    
    /**
     * the objects that use the system monitor are clients, each individual module (AvianceThread)
     * passes itself as a parameter so the monitor could keep track of each client
     * 
     * registered clients are those that request to be polled 
     */
    private Hashtable clients = new Hashtable();
    private Hashtable registeredClients = new Hashtable();
    
    private static AvianceSystemMonitor instance = new AvianceSystemMonitor();
    
    /**
     * If a client is not found by the other functions then this is used to add it
     * pre: client does not already exist in the clients table
     * @param client 
     */
    private void addClient(Object client){
        clients.put(client, new Vector());
    }
    
    /**
     * Ideally messages are strings but this class can distinguish between them
     * and this is a general model
     * @param client The calling module's 'this' pointer or in the case of a static class,
     * its name
     * @param message The report, ideally as a String
     */
    public void report(Object client, Object message){
        if(!clients.containsKey(client)){
            addClient(client);
        }
        ((Vector) clients.get(client)).addElement(message);
    }
    
    /**
     * AvianceSystemMonitor polls the DriverStation as well as registered clients
     * for updates
     */
    public void run(){
        try{
            while(true){
                
                
                
                
                AvianceThreadHousekeeping(sleepTime);
            }
        }
        catch(AvianceThreadInterruptedException e){
            //system thread have been interrupted
        }
    }
    
    public String getSystemReport(){
        String temp = "";
        //here we add robot information such as color, status, etc
        //after robo info all error reports are appended
        return temp;
    }
    
    private AvianceSystemMonitor(){
        AvianceThreadManager.getInstance().addThread(AvianceRobot.systemThreadGroup, this);
    }
    public static AvianceSystemMonitor getInstance(){
        return instance;
    }
}
