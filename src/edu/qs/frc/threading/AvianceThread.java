/*
 */
package edu.qs.frc.threading;

/**
 *
 * @author pawel
 */
public class AvianceThread extends Thread{
    
    protected boolean interrupted;
    protected long sleepTime = 30; //10 is really small.. now lets do a CPU percent test
    
    public AvianceThread(){
        super();
        interrupted = false;
    }
    
    /**
     * AvianceThreadHousekeeping
     * 
     * Sleeps the thread while being aware of possible interrupts
     * If an interrupt does occur an exception is thrown
     * 
     * @param sleepTime
     * @throws AvianceThreadInterruptedException 
     */
    protected void AvianceThreadHousekeeping(long sleepTime) throws AvianceThreadInterruptedException{
        if (interrupted){
            throw new AvianceThreadInterruptedException();
        }
        try{
            Thread.sleep(sleepTime);
        }
        catch(InterruptedException e){
            throw new AvianceThreadInterruptedException();
        }
    }
    
    public void interrupt(){
        if(Thread.currentThread().isAlive()){
            interrupted = true;
            super.interrupt();
        }
    }
    
//    protected void startup(){
//        
//    }
    
    /**
     * All threads extending this class must override this method to add functionality
     * This is just a template but AvianceThreadHousekeeping *MUST* be invoked instead of sleep()
     * and all user code *MUST* be placed in side a try-catch block catching AvianceThreadInterruptedException
     * and ceasing all robot operations while cleaning up. At he end of the catch block where AvianceThreadInterruptedException is caught,
     * user must set the interrupt flag to false (interrupt = false) otherwise the thread will NOT restart***
     */
    public void run(){
        
        //the interrupt flag should be cleared before calling house keeping
        interrupted = false;
        
       startup();
        
        try{
            while(true){
                
                // user code goes here
                iteration(); //or this is called, in which case children should overload it
                
                //calling the following method results in the thread sleeping while being ready for interrupts
                AvianceThreadHousekeeping(sleepTime);
            }
        }
        catch(AvianceThreadInterruptedException e){
            
            //System.out.println("Default AvianceThread run() being used. This is okay as long as iteration() is overloaded with code");
            
            //this block signifies the end of this thread's cycle,
            //if anything needs to be cleaned up it should be done here
            
            reset(); //user cleanup code in reset
            interrupted = false;
        }
    }
    
    protected void iteration(){
        
    }
    
    protected void startup(){
        
    }
    
    protected void reset(){
        
    }
}
