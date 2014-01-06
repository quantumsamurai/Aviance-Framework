/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qs.frc.network;

import com.sun.squawk.io.j2me.socket.Protocol;
import edu.qs.frc.threading.AvianceThread;
import edu.qs.frc.threading.AvianceThreadInterruptedException;
import edu.qs.frc.threading.AvianceThreadManager;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
/**
 *
 * @author pawel
 */
public class AvianceConnection extends Protocol{
    
    private Protocol socket;
    private OutputStream outstream;
    private InputStream instream;
    private final Vector incommingPackets, outgoingPackets;
    private final String threadGroup = "connx" + this.hashCode();
    private AvianceThreadManager threadManager = AvianceThreadManager.getInstance();
    private int buffer_length = 1024;
    
    public AvianceConnection(Protocol inSocket){
        incommingPackets = new Vector();
        outgoingPackets = new Vector();
        
        socket = inSocket;
        try{
            instream = socket.openInputStream();
            outstream = socket.openOutputStream();
        }
        catch(IOException e){
            // FATAL ERROR STREAM COULD NOT BE LAUNCHED
            System.err.println("FATAL ERROR in AvianceConnection: Input or Output streams could not be opend :(");
        }
        
        threadManager.createGroup(threadGroup);
        threadManager.addThread(threadGroup, new Sender());
        threadManager.addThread(threadGroup, new Receiver());
        threadManager.startThreads(threadGroup);
    }
    
    /**
     * Returns the next Packet in the queue and removes it from the queue
     * @return the next Packet
     */
    public AviancePacket getNextPacket(){
        if(incommingPackets.isEmpty()){
            return null;
        }
        else{
            Object temp = incommingPackets.firstElement();
            
            synchronized(incommingPackets){
                incommingPackets.removeElementAt(0);
            }
            
            return (AviancePacket) temp;
        }
    }
    
    /**
     * packetsAvailible() returns true if there are packets in the incoming queue
     * @return true if packets are ready to be pulled
     */
    public boolean packetsAvailible(){
        return !incommingPackets.isEmpty();
    }
    
    /**
     * Adds the specified packet to the outgoing packets queue to be sent
     * @param send 
     */
    public void sendPacket(AviancePacket send){
        synchronized(outgoingPackets){
            outgoingPackets.addElement(send);
        }
    }
    
    /**
     * closes the connection and cleans up i.e. removes running threads
     */
    public void close(){
        threadManager.interruptThreads(threadGroup);
        threadManager.removeGroup(threadGroup);
        try{
            super.close();
        }
        catch(IOException e){
            
        }
    }
    
    /**
     * class Sender extends AvianceThread
     * Responsible for sending any packets in the queue
     */
    private class Sender extends AvianceThread{
        
        public Sender(){
            super();
        }
        
        /** run()
         * sends packets as they become available 
         */
        public void run(){
            try{
                while(true){
                    
                    while(!outgoingPackets.isEmpty()){
                        try{
                            outstream.write(((AviancePacket) outgoingPackets.firstElement()).getPacketStream());
                            
                            //this will only run if write() did not throw an exception
                            synchronized(outgoingPackets){
                                outgoingPackets.removeElementAt(0);
                            }
                            
                            AvianceThreadHousekeeping(sleepTime / 5); //some rest in between sends
                        }
                        catch(IOException e){
                            //failed to write to socket
                            System.err.println("ERROR IN AvianceConnection: outstream.write() failed");
                            
                            /**
                             * for now we sleep and try again, we should come up with a better solution..
                             */
                            AvianceThreadHousekeeping(sleepTime);
                            continue;
                        }
                    }
                    
                    this.AvianceThreadHousekeeping(sleepTime);
                }
            }
            catch(AvianceThreadInterruptedException e){
                try {
                    //pause execution- thread died
                    outstream.close();
                } catch (IOException ex) {
                }
            }
        }
        
    }
    
    /**
     * class Receiver
     * responsible for listening to data and constructing packets as they come in
     */
    private class Receiver extends AvianceThread{
        
        public Receiver(){
            super();
        }
        
        public void run(){
            try{
                AviancePacket temp;
                byte[] stream = new byte[buffer_length];
                int read;
                
                while(true){
                    
                    try{
                        read = instream.read(stream);
                        temp = new AviancePacket(stream, read);
                        
                        synchronized(outgoingPackets){
                            outgoingPackets.addElement(temp);
                        }
                    }
                    catch(IOException e){
                        //reading from stream failed
                    }
                    
                    
                    this.AvianceThreadHousekeeping(sleepTime);
                }
            }
            catch(AvianceThreadInterruptedException e){
                try {
                    //pause exection- thread died
                    instream.close();
                } catch (IOException ex) {
                }
            }
        }
    }
    
}
