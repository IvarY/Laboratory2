import acm.graphics.GCompound;
import acm.graphics.GPolygon;

import java.awt.*;

public class XPoint extends GCompound {
    public XPoint(double width, double height){
        GPolygon X1 = createX1(width/2, height/2);
        X1.setColor(Color.BLACK);
        X1.setFilled(true);
        add(X1,width/2,height/2);
        GPolygon X2 = createX2(width/2, height/2);
        X2.setColor(Color.BLACK);
        X2.setFilled(true);
        add(X2, width/2,height/2);

    }
    private GPolygon createX1(double width, double height){
        GPolygon X1 = new GPolygon();
        X1.addVertex(-width, -height+height*2/9);
        X1.addVertex(-width+width*2/9, -height);
        X1.addVertex(width, height*7/9);
        X1.addVertex(width*7/9, height);
        return X1;
    }

    private GPolygon createX2(double width, double height){
        GPolygon X2 = new GPolygon();
        X2.addVertex(-width, height*7/9);
        X2.addVertex(-width*7/9, height);
        X2.addVertex(width, -height*7/9);
        X2.addVertex(width*7/9, -height);
        return X2;
    }
}