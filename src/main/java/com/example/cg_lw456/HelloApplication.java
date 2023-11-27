package com.example.cg_lw456;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static final int WIDTH=1400;
    private static final int HEIGHT=800;
    private double ancX, ancY;
    private double ancAngleX=0;
    private double ancAngleY=0;
    private final DoubleProperty angleX= new SimpleDoubleProperty(0);
    private final DoubleProperty angleY= new SimpleDoubleProperty(0);
    boolean line;
    private boolean fillEnabled = true;
    @Override
    public void start(Stage primStage) throws IOException {
        //figure
        Box supBox= supriseBox();
        Box boxC= coverBox();
        Box box1 = new Box(50, 1, 5);
        Box box2 = new Box(50, 1, 5);
        Cylinder cylinder1 = new Cylinder(20, 1);
        Cylinder cylinder2 = new Cylinder(20, 1);
        Sphere sphere = new Sphere(7);
        SmartGroup group=new SmartGroup();
        group.getChildren().add(supBox);
        group.getChildren().add(boxC);

        box1.setTranslateX(-20);
        box1.setTranslateY(-71);
        box1.setTranslateZ(20);

        box2.setTranslateX(-20);
        box2.setTranslateY(-71);
        box2.setTranslateZ(-20);

        cylinder1.setTranslateX(15);
        cylinder1.setTranslateY(-71);
        cylinder1.setTranslateZ(20);

        cylinder2.setTranslateX(15);
        cylinder2.setTranslateY(-71);
        cylinder2.setTranslateZ(-20);

        sphere.setTranslateX(2);
        sphere.setTranslateY(-75);
        sphere.setTranslateZ(1);

        box1.getTransforms().add(new Rotate(45,Rotate.Y_AXIS));
        box2.getTransforms().add(new Rotate(-45,Rotate.Y_AXIS));

        cylinder1.getTransforms().add(new Rotate(180, Rotate.Z_AXIS));
        cylinder2.getTransforms().add(new Rotate(180, Rotate.X_AXIS));

        PhongMaterial material = new PhongMaterial(Color.YELLOW);
        box1.setMaterial(material);
        box2.setMaterial(material);
        cylinder1.setMaterial(material);
        cylinder2.setMaterial(material);
        sphere.setMaterial(material);
        box1.setDrawMode(fillEnabled ?DrawMode.FILL:DrawMode.LINE);
        box2.setDrawMode(fillEnabled ?DrawMode.FILL:DrawMode.LINE);
        cylinder1.setDrawMode(fillEnabled ?DrawMode.FILL:DrawMode.LINE);
        cylinder2.setDrawMode(fillEnabled ?DrawMode.FILL:DrawMode.LINE);
        sphere.setDrawMode(fillEnabled ?DrawMode.FILL:DrawMode.LINE);
        group.getChildren().addAll(box1, box2, cylinder1, cylinder2, sphere);

        //scena
        Camera camera= new PerspectiveCamera();
        Scene scene = new Scene(group, WIDTH, HEIGHT, true);
            scene.setFill(Color.DARKGREEN);
        scene.setCamera(camera);
        //rospodil
        boxC.setTranslateX(0); boxC.setTranslateY(-55);
        group.translateXProperty().set(1400/2);
        group.translateYProperty().set(800/2);
        group.translateZProperty().set(-1000);
        initMouseControl(group,scene,primStage);
        Transform transform= new Rotate(65,new Point3D(0,1,0));
        group.getTransforms().add(transform);
        scene.isDepthBuffer();
        primStage.addEventHandler(KeyEvent.KEY_PRESSED,event -> {
            switch (event.getCode()){
                case A -> group.translateZProperty().set(group.getTranslateZ()+100);
                case S -> group.translateZProperty().set(group.getTranslateZ()-100);
                case Q -> group.rotateX(10);
                case W -> group.rotateX(-10);
                case Z -> group.rotateY(10);
                case X -> group.rotateY(-10);
                case E -> {
                    fillEnabled=!fillEnabled;
                    for (var node : group.getChildren())
                       if (node instanceof Box ){((Box) node).setDrawMode(fillEnabled ? DrawMode.FILL :DrawMode.LINE);}
                    for (var node : group.getChildren())
                        if (node instanceof Cylinder ){((Cylinder) node).setDrawMode(fillEnabled ? DrawMode.FILL :DrawMode.LINE);}
                    for (var node : group.getChildren())
                        if (node instanceof Sphere ){((Sphere) node).setDrawMode(fillEnabled ? DrawMode.FILL :DrawMode.LINE);}
                }
            }
        });
        primStage.setScene(scene);
        primStage.show();
    }

    private Box coverBox() {
        Image imageBox= new Image("merry.jpg");
        PhongMaterial material=new PhongMaterial();
        material.setDiffuseMap(imageBox);
        Box box = new Box(160,30,230);
        box.setMaterial(material);
        box.setDrawMode(fillEnabled ?DrawMode.FILL:DrawMode.LINE);
        return box;
    }

    private Box supriseBox() {
        Image imageBox= new Image("crismas.jpg");
        PhongMaterial material=new PhongMaterial();
        material.setDiffuseMap(imageBox);
        Box box = new Box(150,100,200);
        box.setMaterial(material);
        box.setDrawMode(fillEnabled ?DrawMode.FILL:DrawMode.LINE);
        return box;
    }
    private void initMouseControl(SmartGroup group, Scene scene, Stage stage) {
        Rotate rotateX;
        Rotate rotateY;
        group.getTransforms().addAll(rotateX=new Rotate(0, Rotate.X_AXIS), rotateY=new Rotate(0,Rotate.Y_AXIS));
        rotateX.angleProperty().bind(angleX);
        rotateY.angleProperty().bind(angleY);
        scene.setOnMousePressed(event -> {
            ancX=event.getSceneX();
            ancY=event.getSceneY();
            ancAngleX=angleX.get();
            ancAngleY=angleY.get();
        });
        scene.setOnMouseDragged(event -> {
            angleX.set(ancAngleX-(ancY-event.getSceneY()));
            angleY.set(ancAngleY+ancX-event.getSceneX());
        });
        //zoom figure
        stage.addEventHandler(ScrollEvent.SCROLL, event -> {
            double move=event.getDeltaY();
            group.translateZProperty().set(group.getTranslateZ()+move);
        });
    }
    public static void main(String[] args) {
        launch();
    }
    class SmartGroup extends Group {
        Rotate R;
        Transform T = new Rotate();
        void rotateX(int ang) {
            R = new Rotate(ang, Rotate.X_AXIS);
            T = T.createConcatenation(R);
            this.getTransforms().clear();
            this.getTransforms().addAll(T);
        }
        void rotateY(int ang) {
            R = new Rotate(ang, Rotate.Y_AXIS);
            T = T.createConcatenation(R);
            this.getTransforms().clear();
            this.getTransforms().addAll(T);
        }
    }
}