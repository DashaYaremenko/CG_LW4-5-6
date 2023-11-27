//package com.example.cg_lw456;
//
//import javafx.scene.Group;
//import javafx.scene.paint.Color;
//import javafx.scene.paint.PhongMaterial;
//import javafx.scene.shape.*;
//import javafx.scene.transform.Rotate;
//
//public class Ribbon extends Group {
//
//    public Ribbon(){ribbon3D();}
//    private boolean fillEnabled = true;
//    private void ribbon3D() {
//        Box box1 = new Box(50, 1, 5);
//        Box box2 = new Box(50, 1, 5);
//        Cylinder cylinder1 = new Cylinder(20, 1);
//        Cylinder cylinder2 = new Cylinder(20, 1);
//        Sphere sphere = new Sphere(7);
//
//
//        box1.setTranslateX(-20);
//        box1.setTranslateY(-71);
//        box1.setTranslateZ(20);
//
//        box2.setTranslateX(-20);
//        box2.setTranslateY(-71);
//        box2.setTranslateZ(-20);
//
//        cylinder1.setTranslateX(15);
//        cylinder1.setTranslateY(-71);
//        cylinder1.setTranslateZ(20);
//
//        cylinder2.setTranslateX(15);
//        cylinder2.setTranslateY(-71);
//        cylinder2.setTranslateZ(-20);
//
//        sphere.setTranslateX(2);
//        sphere.setTranslateY(-75);
//        sphere.setTranslateZ(1);
//
//        box1.getTransforms().add(new Rotate(45,Rotate.Y_AXIS));
//        box2.getTransforms().add(new Rotate(-45,Rotate.Y_AXIS));
//
//        cylinder1.getTransforms().add(new Rotate(180, Rotate.Z_AXIS));
//        cylinder2.getTransforms().add(new Rotate(180, Rotate.X_AXIS));
//
//        PhongMaterial material = new PhongMaterial(Color.YELLOW);
//        box1.setMaterial(material);
//        box2.setMaterial(material);
//        cylinder1.setMaterial(material);
//        cylinder2.setMaterial(material);
//        sphere.setMaterial(material);
//        box1.setDrawMode(fillEnabled ?DrawMode.FILL:DrawMode.LINE);
//        box2.setDrawMode(fillEnabled ?DrawMode.FILL:DrawMode.LINE);
//        cylinder1.setDrawMode(fillEnabled ?DrawMode.FILL:DrawMode.LINE);
//        cylinder2.setDrawMode(fillEnabled ?DrawMode.FILL:DrawMode.LINE);
//        sphere.setDrawMode(fillEnabled ?DrawMode.FILL:DrawMode.LINE);
//        getChildren().addAll(box1, box2, cylinder1, cylinder2, sphere);
//    }
//}