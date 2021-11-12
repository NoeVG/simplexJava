/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package noevg.github.io.simplex.computerVision;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

/**
 *
 * @author ziusudra
 */
public class ComputerVision {
    Mat frameModel;
    
    public BufferedImage captureModel(){
        
        this.frameModel = Imgcodecs.imread("/home/ziusudra/Desktop/modelo.jpeg");
        
        BufferedImage image = new BufferedImage(this.frameModel.width(), this.frameModel.height(), BufferedImage.TYPE_3BYTE_BGR);
        WritableRaster raster = image.getRaster();
        DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
        byte[] data = dataBuffer.getData();
        this.frameModel.get(0, 0, data);

        return image;
        
        //return null;
    }
    
    /**
     * @param mat
     * @return 
     */
    public BufferedImage getImage() {
    
    //int type = 0;
    /*
    if (mat.channels() == 1) {
        type = BufferedImage.TYPE_BYTE_GRAY;
    } else if (mat.channels() == 3) {
        type = BufferedImage.TYPE_3BYTE_BGR;
    } else {
        return null;
    }
    */
    //type = BufferedImage.TYPE_3BYTE_BGR;
    
    BufferedImage image = new BufferedImage(this.frameModel.width(), this.frameModel.height(), BufferedImage.TYPE_3BYTE_BGR);
    WritableRaster raster = image.getRaster();
    DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
    byte[] data = dataBuffer.getData();
    this.frameModel.get(0, 0, data);
    
    return image;
}
    
}
