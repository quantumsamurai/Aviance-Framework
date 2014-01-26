/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qs.Vision;

import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.NIVision;
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
public class AdvancedVision extends AvianceThread{
    public AdvancedVision(){
    AvianceThreadManager.getInstance().addThread(AvianceRobot.teleopThreads, this);}
       
    
    public class Scores {
        double rectangularity;
        double aspectRatioVertical;
        double aspectRatioHorizontal;
    }
        public class TargetReport {
		int verticalIndex;
		int horizontalIndex;
		boolean Hot;
		double totalScore;
		double leftScore;
		double rightScore;
		double tapeWidthScore;
		double verticalScore;
    };
      
	int verticalTargets[] = new int[VisionConstants.MAX_PARTICLES];
	int horizontalTargets[] = new int[VisionConstants.MAX_PARTICLES];
	int verticalTargetCount, horizontalTargetCount;
    
 protected void iteration(){
     TargetReport target = new TargetReport();
 
        try {
            ColorImage image = Hardware.camera.getImage();
             BinaryImage thresholdImage = image.thresholdHSV(105, 137, 230, 255, 133, 183);
             BinaryImage filteredImage = thresholdImage.particleFilter(Hardware.cc);  
             Scores scores[] = new Scores[filteredImage.getNumberParticles()];
             horizontalTargetCount = verticalTargetCount = 0;
     
             if(filteredImage.getNumberParticles() > 0){
             for (int i = 0; i < VisionConstants.MAX_PARTICLES && i < filteredImage.getNumberParticles(); i++) {
			ParticleAnalysisReport report = filteredImage.getParticleAnalysisReport(i);
                        scores[i] = new Scores();
					
			//Score each particle on rectangularity and aspect ratio
			scores[i].rectangularity = VisionConstants.scoreRectangularity(report);
			scores[i].aspectRatioVertical = VisionConstants.scoreAspectRatio(filteredImage, report, i, true);
			scores[i].aspectRatioHorizontal = VisionConstants.scoreAspectRatio(filteredImage, report, i, false);	
                        
                        
                   target.totalScore = target.leftScore = target.rightScore = target.tapeWidthScore = target.verticalScore = 0;
			target.verticalIndex = verticalTargets[0];
             
                        for (int a = 0; a < verticalTargetCount; a++)
			{
				ParticleAnalysisReport verticalReport = filteredImage.getParticleAnalysisReport(verticalTargets[a]);
				for (int j = 0; j < horizontalTargetCount; j++)
				{
                                    ParticleAnalysisReport horizontalReport = filteredImage.getParticleAnalysisReport(horizontalTargets[j]);
                                    double horizWidth, horizHeight, vertWidth, leftScore, rightScore, tapeWidthScore, verticalScore, total;
	
                                    //Measure equivalent rectangle sides for use in score calculation
                                    horizWidth = NIVision.MeasureParticle(filteredImage.image, horizontalTargets[j], false, NIVision.MeasurementType.IMAQ_MT_EQUIVALENT_RECT_LONG_SIDE);
                                    vertWidth = NIVision.MeasureParticle(filteredImage.image, verticalTargets[a], false, NIVision.MeasurementType.IMAQ_MT_EQUIVALENT_RECT_SHORT_SIDE);
                                    horizHeight = NIVision.MeasureParticle(filteredImage.image, horizontalTargets[j], false, NIVision.MeasurementType.IMAQ_MT_EQUIVALENT_RECT_SHORT_SIDE);
						
                                    //Determine if the horizontal target is in the expected location to the left of the vertical target
                                    leftScore = VisionConstants.ratioToScore(1.2*(verticalReport.boundingRectLeft - horizontalReport.center_mass_x)/horizWidth);
                                    //Determine if the horizontal target is in the expected location to the right of the  vertical target
                                    rightScore = VisionConstants.ratioToScore(1.2*(horizontalReport.center_mass_x - verticalReport.boundingRectLeft - verticalReport.boundingRectWidth)/horizWidth);
                                    //Determine if the width of the tape on the two targets appears to be the same
                                    tapeWidthScore = VisionConstants.ratioToScore(vertWidth/horizHeight);
                                    //Determine if the vertical location of the horizontal target appears to be correct
                                    verticalScore = VisionConstants.ratioToScore(1-(verticalReport.boundingRectTop - horizontalReport.center_mass_y)/(4*horizHeight));
                                    total = leftScore > rightScore ? leftScore:rightScore;
                                    total += tapeWidthScore + verticalScore;

                                    //If the target is the best detected so far store the information about it
                                    if(total > target.totalScore)
                                    {
                                            target.horizontalIndex = horizontalTargets[j];
                                            target.verticalIndex = verticalTargets[a];
                                            target.totalScore = total;
                                            target.leftScore = leftScore;
                                            target.rightScore = rightScore;
                                            target.tapeWidthScore = tapeWidthScore;
                                            target.verticalScore = verticalScore;
                                    }
                                }
                                //Determine if the best target is a Hot target
                                target.Hot = VisionConstants.hotOrNot(target);
                            }

                            if(verticalTargetCount > 0)
                            {
                                    //Information about the target is contained in the "target" structure
                                    //To get measurement information such as sizes or locations use the
                                    //horizontal or vertical index to get the particle report as shown below
                                    ParticleAnalysisReport distanceReport = filteredImage.getParticleAnalysisReport(target.verticalIndex);
                                    double distance = computeDistance(filteredImage, distanceReport, target.verticalIndex);
                                    if(target.Hot)
                                    {
                                            System.out.println("Hot target located");
                                            System.out.println("Distance: " + distance);
                                    } else {
                                            System.out.println("No hot target present");
                                            System.out.println("Distance: " + distance);
                                    }
                            }
             
             
             
             
             
             }
                            
        } }catch (AxisCameraException ex) {
            ex.printStackTrace();
        } catch (NIVisionException ex) {
            ex.printStackTrace();
        }
   
 }
  private double computeDistance(BinaryImage image, ParticleAnalysisReport report, int particleNumber) throws NIVisionException {
        double rectLong, height;
        int targetHeight;

        rectLong = NIVision.MeasureParticle(image.image, particleNumber, false, NIVision.MeasurementType.IMAQ_MT_EQUIVALENT_RECT_LONG_SIDE);
            //using the smaller of the estimated rectangle long side and the bounding rectangle height results in better performance
        //on skewed rectangles
        height = Math.min(report.boundingRectHeight, rectLong);
        targetHeight = 32;

        return VisionConstants.Y_IMAGE_RES * targetHeight / (height * 12 * 2 * Math.tan(VisionConstants.VIEW_ANGLE * Math.PI / (180 * 2)));
    }
 protected void reset(){}
}
