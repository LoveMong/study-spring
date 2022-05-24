//package com.fastcampus.ch3;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.GenericXmlApplicationContext;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//
//@Component
//class Engine{}
//@Component class Door{}
//@Component class Car{
//    @Value("red") String color;
//    @Value("100") int oil;
//   @Autowired
//   Engine engine;
//
//    @Autowired Door[] doors;
//
//    public void setColor(String color) {
//        this.color = color;
//    }
//
//    public void setOil(int oil) {
//        this.oil = oil;
//    }
//
//    public void setEngine(Engine engine) {
//        this.engine = engine;
//    }
//
//    public void setDoors(Door[] doors) {
//        this.doors = doors;
//    }
//    @Override
//    public String toString() {
//        return "Car{" +
//                "color='" + color + '\'' +
//                ", oil=" + oil +
//                ", engine=" + engine +
//                ", doors=" + Arrays.toString(doors) +
//                '}';
//    }
//}
//
//public class SpringDiTest {
//    public static void main(String[] args) {
//        ApplicationContext ac = new GenericXmlApplicationContext("config.xml");
////        Car car = (Car) ac.getBean("car"); // byName 아래와 같은 문장
//        Car car = ac.getBean("car", Car.class); // byName
//        Car car2 = (Car) ac.getBean(Car.class); // byType
//
//        Engine engine = (Engine) ac.getBean("engine");
//        Door door = (Door) ac.getBean("door");
//
//        car.setColor("red");
//        car.setOil(100);
//        car.setEngine(engine);
//        car.setDoors(new Door[]{ac.getBean("door", Door.class), (Door) ac.getBean("door")});
//
//        System.out.println("car = " + car);
//    }
//}
