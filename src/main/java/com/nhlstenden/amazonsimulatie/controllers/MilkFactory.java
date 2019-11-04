package com.nhlstenden.amazonsimulatie.controllers;

import com.nhlstenden.amazonsimulatie.models.CreateWaybill;
import com.nhlstenden.amazonsimulatie.models.Data;
import com.nhlstenden.amazonsimulatie.models.Factory;
import com.nhlstenden.amazonsimulatie.models.generated.Rack;
import com.nhlstenden.amazonsimulatie.models.generated.Waybill;
import net.ravendb.client.documents.session.IDocumentSession;

import java.util.Random;
/*
 *a factory type class that sends or requests generated goods from the warehouse
 */
public class MilkFactory extends CreateWaybill implements Factory {
  private boolean flipFlop;
  private int time = 0;
  private Random random = new Random();

  @Override
  //creates waybills("vrachtbrieven") and sends them to the database
  public void update() {
    time++;
    if (time < 20)
      return;
    flipFlop = !flipFlop;
    try (IDocumentSession session = DocumentStoreHolder.getStore().openSession()) {
      int storedRacks = session.query(Rack.class).whereEquals("status", Rack.Status.STORED).count();
      if (flipFlop) {
        if (storedRacks < (Data.modules * 25))
          sendWaybill();
      } else {
        if (storedRacks > (Data.modules * 20))
          requestWaybill();
      }
    }
    time = 0;
  }

  //sends goods to the warehouse
  @Override
  public void sendWaybill() {
    int numberOfRacks = random.nextInt(5) + 5;
    this.createWaybill(numberOfRacks,"kaas", Waybill.Destination.WAREHOUSE);

  }

  //requests goods from the warehouse
  @Override
  public void requestWaybill() {
    int numberOfRacks = random.nextInt(5) + 5;
    this.createWaybill(numberOfRacks,"kaas", Waybill.Destination.MILKFACTORY);
  }
}

