package com.kozi.checking_out_FXGL;

import com.almasb.fxgl.core.math.FXGLMath;
import javafx.geometry.Point2D;

public class MyMath
{
    public final static javafx.geometry.Point2D UP = new javafx.geometry.Point2D(0,-1);
    public final static javafx.geometry.Point2D DOWN = new javafx.geometry.Point2D(0,1);
    public final static javafx.geometry.Point2D LEFT = new javafx.geometry.Point2D(-1,0);
    public final static javafx.geometry.Point2D RIGHT = new javafx.geometry.Point2D(1,0);

    static Point2D Lerp(Point2D a, Point2D b, float t)
    {
        Point2D c = a.interpolate(b, t);
        return c;
    }
    
    static Point2D GetPositionAlongGridPath(float timeElapsed, float moveInterval, Point2D[] pathArray)
    {
        //For moving enemies
        //NOT TESTED!!

        int pathPosIndex = FXGLMath.floor(timeElapsed / moveInterval);
        double t = (timeElapsed % moveInterval) / moveInterval; // 0 <= t < 1
        //t = Math.IEEEremainder(timeElapsed, moveInterval) / moveInterval; //alternative, have to see which is more accurate

        Point2D currentTile = pathArray[pathPosIndex];
        Point2D nextTile = pathArray[pathPosIndex+1];

        return currentTile.interpolate(nextTile, t);
    }
}
