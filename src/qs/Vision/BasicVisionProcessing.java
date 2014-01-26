/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qs.Vision;

import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import qs.GeneralModules.AvianceRobot;
import qs.RobotHardware.Hardware;
import qs.ThreadSetup.AvianceThread;
import qs.ThreadSetup.AvianceThreadManager;

/**
 *
 * @author Darvin
 */
public class BasicVisionProcessing extends AvianceThread{
    public BasicVisionProcessing(){
    AvianceThreadManager.getInstance().addThread(AvianceRobot.teleopThreads, this);
    }  
   
    
    protected void iteration(){
     
        try {
            ColorImage image = Hardware.camera.getImage();
              BinaryImage thresholdImage = image.thresholdHSV(105, 137, 230, 255, 133, 183); 
               BinaryImage bigObjectsImage = thresholdImage.removeSmallObjects(false,2);
               BinaryImage convexhull = bigObjectsImage.convexHull(false);
               BinaryImage filteredImage = convexhull.particleFilter(Hardware.cc);
               ParticleAnalysisReport[] reports = filteredImage.getOrderedParticleAnalysisReports();
               
               for(int i= 0; i < reports.length; i++){
          System.out.println(reports[i].particleArea);
               
               }
            thresholdImage.free();
            bigObjectsImage.free();
            convexhull.free();
            filteredImage.free();
            
            
        } catch (AxisCameraException ex) {
            ex.printStackTrace();
        } catch (NIVisionException ex) {
            ex.printStackTrace();
        }
        
        
}

            
    protected void reset(){}
    
}
