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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import qs.RobotHardware.Hardware;

/**
 *
 * @author Darvin
 */
public class HotGoalFinder {
        public class Scores {
        double rectangularity;
        double aspectRatioVertical;
        double aspectRatioHorizontal;
    }
        
   public boolean goalIsHot() {

        try {

          ColorImage  image = Hardware.camera.getImage();
       
           BinaryImage filteredImage = image.thresholdRGB(206, 255, 145, 255, 213, 170);
            filteredImage = filteredImage.particleFilter(Hardware.cc);

            Scores scores[] = new Scores[filteredImage.getNumberParticles()];

            int verticalTargetCount, horizontalTargetCount = 0;

            if (filteredImage.getNumberParticles() > 0) {
                for (int i = 0; i < 4 && i < filteredImage.getNumberParticles(); i++) {
                    ParticleAnalysisReport report = filteredImage.getParticleAnalysisReport(i);
                    scores[i] = new Scores();

                    scores[i].rectangularity = VisionConstants.scoreRectangularity(report);
                    scores[i].aspectRatioVertical = VisionConstants.scoreAspectRatio(filteredImage, report, i, true);
                    scores[i].aspectRatioHorizontal = VisionConstants.scoreAspectRatio(filteredImage, report, i, false);

                    if (VisionConstants.scoreCompare(scores[i], false)) {

                        filteredImage.free();
                        image.free();
                        SmartDashboard.putBoolean("Hot Goal?", true);
                        return true;
                    }
                }
            }
            filteredImage.free();
            image.free();
        } catch (AxisCameraException ex) {
            ex.printStackTrace();
        } catch (NIVisionException ex) {
            ex.printStackTrace();
        }
            SmartDashboard.putBoolean("Hot Goal?", false);
        return false;
    }
}
