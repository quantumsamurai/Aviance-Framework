/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qs.frc.threading;

import java.util.Hashtable;
import java.util.Vector;

/** Responsible for maintaining thread groups, Each group may be started or interrupted
 * on demand.
 * The neat thing about this manager is that any thread group could safely reside here
 * without fear of being interrupted unless the interrupter knows the group ID
 *
 * @author pawel
 */
public class AvianceThreadManager {
    
    /** managerInstance
     * The single instance of this class
     */
    private static final AvianceThreadManager managerInstance = new AvianceThreadManager();
    private Hashtable threadGroups = new Hashtable(); // <String, Vector>
    
    /**Interrupts all threads in a given thread group
     * 
     * @param groupID
     * @return FALSE if the specified thread group does not exist
     */
    public boolean interruptThreads(String groupID){
        
        if(!threadGroups.containsKey(groupID)){
            return false;
        }
        else{
            System.out.println("interrupting all threads in group: " + groupID);
            ((Vector) threadGroups.get(groupID)).setElementAt((false ? Boolean.TRUE : Boolean.FALSE), 0);
            for(int c = 1; c < ((Vector) threadGroups.get(groupID)).size(); c++){
                ((AvianceThread) ((Vector) threadGroups.get(groupID)).elementAt(c)).interrupt();
            }
            return true;
        }
    }
    
    /**Starts execution of all threads that are not already running in the specified thread group
     * 
     * @param groupID
     * @return FALSE if the group does not exists
     */
    public boolean startThreads(String groupID){
        
        
        
        if(!threadGroups.containsKey(groupID)){  //oops, should be containsKey, not contains, lol 
            System.err.println("Thread group not found: " + groupID);
            return false;
        }
        else{
        System.out.println("starting all threads in group: " + groupID);
            
            ((Vector) threadGroups.get(groupID)).setElementAt((true ? Boolean.TRUE : Boolean.FALSE), 0);
            for(int c = 1; c < ((Vector) threadGroups.get(groupID)).size(); c++){
                if(!((AvianceThread) ((Vector) threadGroups.get(groupID)).elementAt(c)).isAlive()){
                    new Thread(((AvianceThread) ((Vector) threadGroups.get(groupID)).elementAt(c))).start(); //this little fix might take care of everything, let's see for sure..
                }
            }// you wanna run a practice to ensure all modes work. i have not writted any modules besides the tank drive, and even that was just a concept. modules i was saving for build season but lets get the hang of how to write them
            return true;
        }
    }
    
    /**Adds a thread to a specified thread group
     * succeeds if the specified thread group exists and does not already contain the thread
     * If the group's status is set to running and the added thread is not already running
     * then the thread is started
     * 
     * @param groupID The ID of the group as a String
     * @param thread Thread to ass to the group
     * @return TRUE if thread was added to the group, FALSE if the specified
     * group does not exist
     */
    public boolean addThread(String groupID, AvianceThread thread){
        
        
        System.out.println("adding " + thread.getName() + " to " + groupID);
        
        if (!threadGroups.containsKey(groupID)){
            this.createGroup(groupID);
            System.out.println("creating the threadgroup..." + groupID);
           // return false; //this is why... just change this     it needs to create the group if it doesnt exists, this is where i left off
        }
        {
            if(((Vector) threadGroups.get(groupID)).contains(thread)){
                return false;
            }
            ((Vector) threadGroups.get(groupID)).addElement(thread);
            if(((Boolean) ((Vector) threadGroups.get(groupID)).elementAt(0)).booleanValue() && !thread.isAlive()){
                thread.start();
            }
            return true;
        }
    }
    
    /**Removes the specified thread form the specified thread group
     * 
     * @param groupID
     * @param thread thread to be removed
     */
    public void removeThread(String groupID, AvianceThread thread){
        if (threadGroups.containsKey(groupID)){
            if(((Vector) threadGroups.get(groupID)).contains(thread)){
                ((Vector) threadGroups.get(groupID)).removeElement(thread);
            }
        }
    }
    
    /**Stops execution of all threads in a specified thread group and removes the group
     * 
     * @param groupID 
     */
    public void removeGroup(String groupID){
        if (threadGroups.containsKey(groupID)){
            interruptThreads(groupID);
            threadGroups.remove(groupID);
        }
    }
    
    /**Creates a new thread group with the specified name, the first object is
     * always a Boolean with the group's state. on creation group's state is false
     * 
     * @param groupID The name of the group
     * @return FALSE if the group already exists
     */
    public boolean createGroup(String groupID){
        if(!threadGroups.containsKey(groupID)){
            threadGroups.put(groupID, new Vector());
            ((Vector) threadGroups.get(groupID)).addElement(Boolean.FALSE);
            return true;
        }
        else return false;
    }

    
    public static AvianceThreadManager getInstance(){
        return managerInstance;
    }
    private AvianceThreadManager(){}
}
