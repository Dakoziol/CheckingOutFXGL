package com.kozi.checking_out_FXGL;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Factory implements EntityFactory
{
    // I could be wrong but it's my understanding that the EntityFactory isn't necessary,
    // it just makes it easier to spawn a number of the same entities with the same components.
    // Maybe it's just optimized for that.

    @Spawns("enemy")
    public Entity newEnemy(SpawnData data) {
        return FXGL.entityBuilder(data)
                .view(new Rectangle(40, 40, Color.BLUEVIOLET))
                //.view("textureName.png");
                .with(new ProjectileComponent(new Point2D(-1, 0), 150))
                .build();
    }

    /*@Spawns("player")
    public Entity newPlayer(SpawnData data)
    {
        return FXGL.entityBuilder(data)
                .view(new Rectangle(40,40, Color.ORANGE))
                .with(new PlayerComponent())
                //.view("textureName.png");
                //.with(new ProjectileComponent(new Point2D(1,0),150))
                .build();
    }*/

}
