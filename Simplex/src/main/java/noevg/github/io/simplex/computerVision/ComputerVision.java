/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package noevg.github.io.simplex.computerVision;

import com.github.sarxos.webcam.Webcam;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.util.concurrent.CountDownLatch;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author ziusudra
 */
public class ComputerVision implements Runnable  {
    private CountDownLatch latch;
    Webcam webcam;
    Mat frameModel;
    

    public ComputerVision(CountDownLatch latch){
        this.latch = latch;
        webcam = Webcam.getDefault();
    }
    
    public void run(){
        captureModel();
        latch.countDown();
    }
    
    /**
     * Resize the size of the frame
     * @param frame
     * @return frame resize
     */
    private Mat resizeFrame(){
        Mat frameResize = new Mat();
        // Scaling the Image using Resize function
        Imgproc.resize(this.frameModel, frameResize, new Size(400, 400), 0.1, 0.1,
                       Imgproc.INTER_AREA);
        
        return frameResize;
    }
    
    
    private void procesingFrame(){    
        Mat frame = new Mat();
        Imgproc.cvtColor(this.frameModel, frame, Imgproc.COLOR_BGR2GRAY);
        this.frameModel = frame;
        
        //Imgproc.equalizeHist( this.frameModel, frame );
        //this.frameModel = frame;
        
        //Imgproc.blur(this.frameModel, frame, new Size(1, 1), new Point(-1, -1));
        //this.frameModel = frame;
        
        //Imgproc.GaussianBlur(this.frameModel, frame, new Size(3, 3), 0, 0);
        //this.frameModel = frame;

        
        Imgproc.equalizeHist( this.frameModel, frame );
        this.frameModel = frame;
        
        Imgproc.GaussianBlur(this.frameModel, frame, new Size(1, 1), 0, 0);
        this.frameModel = frame;



        /*
        Mat element = Imgproc.getStructuringElement(Imgproc.CV_SHAPE_RECT, new Size(3, 1),
        new Point(3, 1));
        */
        
        Imgproc.threshold(this.frameModel, frame, 125, 255, 0);    
        this.frameModel = frame;
        /*
        int kernelSize = 3;        
        Mat element = Imgproc.getStructuringElement(Imgproc.CV_SHAPE_RECT, new Size(2 * kernelSize + 1, 2 * kernelSize + 1),
        new Point(kernelSize, kernelSize));

        Imgproc.erode(this.frameModel, frame, element);
        this.frameModel = frame;
        */
        
        //Imgproc.equalizeHist( this.frameModel, frame );
        //this.frameModel = frame;
        /*
        Imgproc.Canny(this.frameModel, frame, 0, 0 * 3, 3, false);
        this.frameModel = frame;
        */
    }
    
    /**
     * Convert the frame OpenCV to BufferedImageto display in the GUI interface
     * @return BufferedImage to display in the GUI Interface
     */
    public BufferedImage getImage(){
        //BufferedImage image = new BufferedImage(this.frameModel.width(), this.frameModel.height(), BufferedImage.TYPE_3BYTE_BGR);
        BufferedImage image = new BufferedImage(this.frameModel.width(), this.frameModel.height(), BufferedImage.TYPE_BYTE_GRAY);

        WritableRaster raster = image.getRaster();
        DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
        byte[] data = dataBuffer.getData();
        this.frameModel.get(0, 0, data);
        return image;
    }
    
    public static Mat bufferedImageToMat(BufferedImage bi) {
        Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC3);
        byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
        mat.put(0, 0, data);
        return mat;
    }
    /**
     * Capture image model 
     * @return BufferedImage to display in the GUI Interface
     */
    private void captureModel(){
        
        //this.frameModel = Imgcodecs.imread("/home/ziusudra/Desktop/modelo.jpeg");
        //procesingFrame();
        try{
            webcam.open();
            this.frameModel = bufferedImageToMat(webcam.getImage());
        
        }catch(Exception e){
            this.frameModel = Imgcodecs.imread("/home/ziusudra/Desktop/modelo.jpeg");
        }
        

        procesingFrame();
        this.frameModel = resizeFrame();     
        

        //this.image = this.getImage();

    }
}
