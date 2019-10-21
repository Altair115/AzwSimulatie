package com.nhlstenden.amazonsimulatie.models;

/*
 * Deze class wordt gebruikt om informatie van het model aan de view te kunnen geven. Dit
 * gaat via het zogenaamde proxy design pattern. Een proxy pattern is een manier om alleen
 * de getters van een object open te stellen voor andere objecten, maar de setters niet.
 * Hieronder zie je dat ook gebeuren. De class ProxyObject3D implementeerd wel de Object3D
 * interface, maar verwijsd door naar een Object3D dat hij binnen in zich houdt. Dit
 * Object3D, met de naam object (zie code hier direct onder), kan in principe van alles zijn.
 * Dat object kan ook setters hebben of een updatemethode, etc. Deze methoden mag de view niet
 * aanroepen, omdat de view dan direct het model veranderd. Dat is niet toegestaan binnen onze
 * implementatie van MVC. Op deze manier beschermen we daartegen, omdat de view alleen maar ProxyObject3D
 * objecten krijgt. Hiermee garanderen we dat de view dus alleen objecten krijgt die alleen maar getters
 * hebben. Zouden we dit niet doen, en bijvoorbeeld een Robot object aan de view geven, dan zouden er
 * mogelijkheden kunnen zijn zodat de view toch bij de updatemethode van de robot kan komen. Deze mag
 * alleen de World class aanroepen, dus dat zou onveilige software betekenen.
 */
public class ProxyRobot3D implements Object3D {
  private RobotPOJO rob;

  public ProxyRobot3D(RobotPOJO rob) {
    this.rob = rob;
  }

  public String getRackUUID() {
    return this.rob.getRackUUID();
  }

  @Override
  public String getUUID() {
    return this.rob.getUUID();
  }

  @Override
  public String getType() {
    return Robot.class.getSimpleName().toLowerCase();
  }

  @Override
  public int getX() {
    return this.rob.getX();
  }

  @Override
  public int getY() {
    return this.rob.getY();
  }

  @Override
  public int getZ() {
    return this.rob.getZ();
  }

  @Override
  public int getRotationX() {
    return 0;
  }

  @Override
  public int getRotationY() {
    return 0;
  }

  @Override
  public int getRotationZ() {
    return 0;
  }


}