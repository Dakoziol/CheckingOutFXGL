package com.kozi.checking_out_FXGL;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


import static com.almasb.fxgl.dsl.FXGL.*;

public class GameApp extends GameApplication {
    Entity player;
    Entity dude;
    PlayerComponent playerComponent;
    DudeComponent dudeComponent;
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("First Rodeo");
        settings.setVersion("0.1");

        //settings.setAppIcon();
        settings.setApplicationMode(ApplicationMode.DEVELOPER);

        //Display Settings
        settings.setWidth(1080);
        //settings.setHeight(720);
        settings.setHeightFromRatio(16.0/9.0);

        //settings.setFullScreenFromStart(true);
        //settings.setFullScreenAllowed(true);
    }

    @Override
    protected void initInput()
    {
        Input input = getInput();

        UserAction move = new UserAction("Move") {
            @Override
            protected void onAction() {playerComponent.move(Point2D.ZERO);}
        };


        UserAction moveUp = new UserAction("Move Up") {
            @Override
            protected void onAction() {playerComponent.moveUp();}
        };
        UserAction moveDown = new UserAction("Move Down") {
            @Override
            protected void onAction() {playerComponent.moveDown();}
        };
        UserAction moveLeft = new UserAction("Move Left") {
            @Override
            protected void onAction() {playerComponent.moveLeft();}
        };
        UserAction moveRight = new UserAction("Move Right") {
            @Override
            protected void onAction() {playerComponent.moveRight();}
        };

        UserAction drag = new UserAction("Drag") {
            boolean dragging = false;
            @Override
            protected void onActionBegin() {
                if(getInput().getMousePositionWorld().distance(dude.getCenter()) < 0.5 * 40)
                    dragging = true;
            }

            @Override
            protected void onAction() {
                if(dragging)
                    dudeComponent.moveToPos(getInput().getMousePositionWorld());
                    //dude.setPosition(getInput().getMousePositionWorld());
            }

            @Override
            protected void onActionEnd() {
                dragging = false;
                // Check if the tile that the mouse is positioned over is placeable.

                // If so, move dude position to the center of that tile.

                // If the tile isn't placeable, or if the mouse isn't positioned over any tile,
                // move the dude's position back to its initial position in the menu bar or whatever.
            }
        };


        input.addAction(moveUp, KeyCode.W);
        input.addAction(moveDown, KeyCode.S);
        input.addAction(moveLeft, KeyCode.A);
        input.addAction(moveRight, KeyCode.D);

        input.addAction(drag, MouseButton.PRIMARY);

        onKeyDown(KeyCode.P, () ->{
            getNotificationService().pushNotification("Word");
        });
    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new Factory());
        //spawn("player",MyMath.Lerp(Point2D.ZERO, new Point2D(getAppWidth(), getAppHeight()), 0.25f));
        playerComponent = new PlayerComponent();
        player = entityBuilder()
                .at(getAppCenter())
                .view(new Rectangle(40,40, Color.ORANGE))
                .zIndex(998)
                .with(playerComponent)
                .buildAndAttach();

        dudeComponent = new DudeComponent();
        dude = entityBuilder()
                .anchorFromCenter()
                .at(0.5*getAppWidth(), 0.75*getAppHeight())
                .view(new Circle(20, Color.GREEN))
                .zIndex(999)
                .with(dudeComponent)
                .buildAndAttach();

        //randomly spawn useless enemies
        run(() -> {
            Rectangle2D spawnBounds = new Rectangle2D(getAppWidth()+40,0, 0, getAppHeight());
            Point2D spawnPoint = FXGLMath.randomPoint(spawnBounds);
            spawn("enemy", spawnPoint);
        }, Duration.seconds((double)1/2));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
