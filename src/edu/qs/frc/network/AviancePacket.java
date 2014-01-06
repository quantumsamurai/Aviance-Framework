/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qs.frc.network;

/**
 *
 * @author pawel
 */
public class AviancePacket {
    
    private byte[] stream;
    
    public AviancePacket(byte[] data){
        stream = new byte[data.length];
       System.arraycopy(data, 0, stream, 0, data.length);
    }
    
    public AviancePacket(byte[] data, int size){
        stream = new byte[size];
        System.arraycopy(data, 0, stream, 0, size);
    }
    
    public byte[] getPacketStream(){
        return stream;
    }
}