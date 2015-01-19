package org.bin2.geomatching.space.impl1;

/**
 * lets assume the cost by minute to the shop would be 20 cents
 * we want a 45 days duration but for a shop less than 5 minutes we can go for a 30 days duration
 * the ideal price would be 20 euro
 *
 * the first step will be to find the weight of each axis, we can assume we can transform here every value
 * in euro/cents
 * one timeToGoInMinutes would be 20 cents
 * one day of duration would be  5*20/15 =>0.666666
 * one euro 100 cents
 * Created by benoitroger on 17/10/14.
 */
public class Offer {
    private final int timeToGoInMinutes;
    private final int offerDurationInDays;
    private final int price;

    public Offer(int timeToGoInMinutes, int offerDurationInDays, int price) {
        this.timeToGoInMinutes = timeToGoInMinutes;
        this.offerDurationInDays = offerDurationInDays;
        this.price = price;
    }

    public int getTimeToGoInMinutes() {
        return timeToGoInMinutes;
    }

    public int getOfferDurationInDays() {
        return offerDurationInDays;
    }

    public int getPrice() {
        return price;
    }
}
