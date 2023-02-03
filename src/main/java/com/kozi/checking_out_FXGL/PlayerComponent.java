package com.kozi.checking_out_FXGL;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.TransformComponent;
import javafx.geometry.Point2D;

public class PlayerComponent extends Component {
    private TransformComponent transformComponent;
    private double frameRateScaler;
    private double SPEED = 5;

    @Override
    public void onUpdate(double tpf) {//tpf = time per frame? = deltaTime?
        frameRateScaler = tpf * 60;
    }

    public void move(Point2D directionVec2)
    {
        //.translateTowards(directionVec2, SPEED * frameRateScaler);
        transformComponent.translate(directionVec2.normalize().multiply(SPEED * frameRateScaler));
    }
    public void moveUp()
    {
        transformComponent.translateY(-SPEED * frameRateScaler);
    }
    public void moveDown()
    {
        transformComponent.translateY(SPEED * frameRateScaler);
    }
    public void moveLeft()
    {
        transformComponent.translateX(-SPEED * frameRateScaler);
    }
    public void moveRight()
    {
        transformComponent.translateX(SPEED * frameRateScaler);
    }
}
