package com.kozi.checking_out_FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.TransformComponent;
import javafx.geometry.Point2D;

public class DudeComponent extends Component
{
    private TransformComponent transformComponent;

    @Override
    public void onUpdate(double tpf) {
        //transformComponent.setPosition(0,0);
    }
    
    public void moveToPos(Point2D posInWorldSpace)
    {
        transformComponent.setPosition(posInWorldSpace);
    }
}
