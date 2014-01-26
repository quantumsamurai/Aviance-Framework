/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qs.RobotHardware;

import edu.wpi.first.wpilibj.AccumulatorResult;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.SensorBase;
import edu.wpi.first.wpilibj.parsing.ISensor;

/**
 *
 * @author admin
 */
public class CustomGyroClass extends SensorBase implements ISensor {
      static final int kOversampleBits = 10;
    static final int kAverageBits = 0;
    static final double kSamplesPerSecond = 50.0;
    static final double kCalibrationSampleTime = 5.0;
    static final double kDefaultVoltsPerDegreePerSecond = 0.007;
    AnalogChannel m_analog;
    double m_voltsPerDegreePerSecond;
    double m_offset;
    int m_center;
    boolean m_channelAllocated;
    AccumulatorResult result;

    
    
         public CustomGyroClass(int channel) {
        m_analog = new AnalogChannel(channel);
        m_channelAllocated = true;
        initGyro();
    }
             public double getAngle() {
        if (m_analog == null) {
            return 0.0;
        } else {
            m_analog.getAccumulatorOutput(result);

            long value = result.value - (long) (result.count * m_offset);

            double scaledValue = value * 1e-9 * m_analog.getLSBWeight() * (1 << m_analog.getAverageBits()) /
                    (m_analog.getModule().getSampleRate() * m_voltsPerDegreePerSecond);

            return scaledValue;
        }
    }
    
    
        public double getRate() {      
        if(m_analog == null) {
            return 0.0;
        } else {
            return (m_analog.getAverageValue() - ((double)m_center + m_offset)) * 1e-9 * m_analog.getLSBWeight() 
                    / ((1 << m_analog.getOversampleBits()) * m_voltsPerDegreePerSecond);
        }
    }

            public void setSensitivity(double voltsPerDegreePerSecond) {
        m_voltsPerDegreePerSecond = voltsPerDegreePerSecond;
    }
         
    private void initGyro() {
        result = new AccumulatorResult();
        if (m_analog == null) {
            System.out.println("Null m_analog");
        }
        m_voltsPerDegreePerSecond = kDefaultVoltsPerDegreePerSecond;

        m_analog.setAverageBits(kAverageBits);
        m_analog.setOversampleBits(kOversampleBits);
        double sampleRate = kSamplesPerSecond * (1 << (kAverageBits + kOversampleBits));
        m_analog.getModule().setSampleRate(sampleRate);

        Time(1.0);
        m_analog.initAccumulator();

        Time(kCalibrationSampleTime);

        m_analog.getAccumulatorOutput(result);

        m_center = (int) ((double)result.value / (double)result.count + .5);

        m_offset = ((double)result.value / (double)result.count) - (double)m_center;

        m_analog.setAccumulatorCenter(m_center);

        m_analog.setAccumulatorDeadband(0); ///< TODO: compute / parameterize this
        m_analog.resetAccumulator();
    }
    

    public void reset() {
        if (m_analog != null) {
            m_analog.resetAccumulator();
        }
    }
        public void free() {
        if (m_analog != null && m_channelAllocated) {
            m_analog.free();
        }
        m_analog = null;
    }

            
                public static void Time(final double seconds) {
        try {
            Thread.sleep((long) (seconds * 1e3));
        } catch (final InterruptedException e) {
        }
    }
            
        
        
        
}
