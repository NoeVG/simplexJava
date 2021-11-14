/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package noevg.github.io.simplex.computerVision;

import com.github.sarxos.webcam.Webcam;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author ziusudra
 */
public class ComputerVision implements Runnable  {
    public static final boolean GET_FROM_CAM = true;
    public static final boolean GET_FROM_FILE = false;

    
    public static final boolean SHOW_COLOR = true;
    public static final boolean SHOW_GRAY = false;

    
    private CountDownLatch latch;
    
    private boolean getImageFrom;
    private boolean showImage;

    private String pathFileImage;
    
    Webcam webcam;
    Mat frameModel;
    Mat frameOriginal;

    

    public ComputerVision(CountDownLatch latch){
        this.latch = latch;
        webcam = Webcam.getDefault();
        webcam.setViewSize(new Dimension(640, 480));
    }
    
    public void run(){
        captureModel();
        latch.countDown();
    }
    
    public void setOriginModel(boolean selectOrigin){
        this.getImageFrom = selectOrigin;
    }
    
    public void setPathFileModel(String path){
        this.pathFileImage = path;
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
        
        Imgproc.GaussianBlur(this.frameModel, frame, new Size(1, 1), 0, 0);
        this.frameModel = frame;
        
        //Imgproc.equalizeHist( this.frameModel, frame );
        //this.frameModel = frame;

        
        Imgproc.threshold(this.frameModel, frame, 125, 255, 1);    
        this.frameModel = frame;

        
        Mat element = Imgproc.getStructuringElement(Imgproc.CV_SHAPE_RECT, new Size(3,1));
        Imgproc.erode(this.frameModel, frame, element,new Point(0, 0));
        this.frameModel = frame;
        
        element = Imgproc.getStructuringElement(Imgproc.CV_SHAPE_RECT, new Size(3,3));
        Imgproc.erode(this.frameModel, frame, element,new Point(0, 0));
        this.frameModel = frame;
        
        //element = Imgproc.getStructuringElement(Imgproc.CV_SHAPE_RECT, new Size(4,4));
        element = Imgproc.getStructuringElement(Imgproc.CV_SHAPE_RECT, new Size(4,4));

        Imgproc.dilate(this.frameModel, frame, element,new Point(0, 0));
        this.frameModel = frame;
        

        this.showImage = ComputerVision.SHOW_GRAY;
        
        //Imgproc.threshold(this.frameModel, frame, 125, 255, 1);    
        //this.frameModel = frame;
        
    }
    
    private void segmentation(){
        
        Mat frame = this.frameModel.clone();
        
        
        Imgproc.Canny(this.frameModel, frame, 125, 125 *3, 3, false);
        
        // Find total markers
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(this.frameModel, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
        
        // Create the marker image for the watershed algorithm
        //Mat markers = Mat.zeros(this.frameModel.size(), CvType.CV_8U);
        
        // Draw the foreground markers
        //for (int i = 0; i < contours.size(); i++) {
        //    Imgproc.drawContours(this.frameOriginal, contours, i, new Scalar(0,0,255), 1);
        //}

        
        MatOfPoint2f[] contoursPoly  = new MatOfPoint2f[contours.size()];
        Rect[] boundRect = new Rect[contours.size()];
        Point[] centers = new Point[contours.size()];
        float[][] radius = new float[contours.size()][1];
        
        for (int i = 0; i < contours.size(); i++) {
            contoursPoly[i] = new MatOfPoint2f();
            Imgproc.approxPolyDP(new MatOfPoint2f(contours.get(i).toArray()), contoursPoly[i], 3, true);
            boundRect[i] = Imgproc.boundingRect(new MatOfPoint(contoursPoly[i].toArray()));
            centers[i] = new Point();
            Imgproc.minEnclosingCircle(contoursPoly[i], centers[i], radius[i]);
        }
        
        List<MatOfPoint> contoursPolyList = new ArrayList<>(contoursPoly.length);
        for (MatOfPoint2f poly : contoursPoly) {
            contoursPolyList.add(new MatOfPoint(poly.toArray()));
        }
        for (int i = 0; i < contours.size(); i++) {
            Scalar color = new Scalar(0, 0, 255);
            //Imgproc.drawContours(this.frameOriginal, contoursPolyList, i, color);
            Imgproc.rectangle(this.frameOriginal, boundRect[i].tl(), boundRect[i].br(), color, 2);
            //Imgproc.circle(this.frameOriginal, centers[i], (int) radius[i][0], color, 2);
        }
        
        // Perform the watershed algorithm
        //Imgproc.watershed(frame, this.frameModel);
        
        this.frameModel = this.frameOriginal;
        this.showImage = ComputerVision.SHOW_COLOR;
        
        
            //this.frameModel = frame;
            //this.showImage = ComputerVision.SHOW_GRAY;
        
        
    }
    
    /**
     * Convert the frame OpenCV to BufferedImageto display in the GUI interface
     * @return BufferedImage to display in the GUI Interface
     */
    public BufferedImage getImage(){
        BufferedImage image = null;
        if(this.showImage == ComputerVision.SHOW_COLOR){
            image = new BufferedImage(this.frameModel.width(), this.frameModel.height(), BufferedImage.TYPE_3BYTE_BGR);
        }else if(this.showImage == ComputerVision.SHOW_GRAY ){
            image = new BufferedImage(this.frameModel.width(), this.frameModel.height(), BufferedImage.TYPE_BYTE_GRAY);
        }

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
        try{
            if(this.getImageFrom == GET_FROM_CAM){
                webcam.open();
                Thread.sleep(2000);                
                //webcam.getImage();
                
                this.frameModel = bufferedImageToMat(webcam.getImage());        
                webcam.close();
            }else if(this.getImageFrom == GET_FROM_FILE){
                this.frameModel = Imgcodecs.imread(this.pathFileImage);
            }
        }catch(Exception e){
            this.frameModel = Imgcodecs.imread("/home/ziusudra/Desktop/modelo.jpeg");
        }
        this.frameOriginal = this.frameModel.clone();

        procesingFrame();
        
        segmentation();
        
        this.frameModel = resizeFrame();     
        
    }
}
