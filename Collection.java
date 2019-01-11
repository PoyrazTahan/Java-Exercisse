package com.company;

import java.util.ArrayList;

/**
 * @author Doga poyraz tahan
 * @since 05/04/2018
 *
 * This Collection stores a series of coordinates as dataset
 *
 *
 */
public class Collection {

    public ArrayList<Coordinate> cList;
    public double meanX;
    public double meanY;
    public int minX;
    public int minY;
    public int maxX;
    public int maxY;
    public double coeff = 0;
    public double slope = 0;
    public ArrayList<Coordinate> symetricList= new ArrayList<>();
    public double midPoint;

    public Collection(ArrayList < Coordinate > aList) {
        this.cList = aList;
        setInfo();
        getSymetry();
    }

    private void setInfo () {
        double sumX = 0;
        double sumY = 0;

        for (int i = 0; i < cList.size(); i++) {
            sumX += cList.get(i).x;
            sumY += cList.get(i).y;

            if (minX > cList.get(i).x)
                minX = cList.get(i).x;

            if (minY > cList.get(i).y)
                minY = cList.get(i).y;

            if (maxX < cList.get(i).x)
                maxX = cList.get(i).x;

            if (maxY < cList.get(i).y)
                maxY = cList.get(i).y;

        }
        this.meanX = (sumX / cList.size());
        this.meanY = (sumY / cList.size());

        double denominator = 0;
        double numerator = 0;

        for (int i = 0; i < cList.size(); ++i) {
            denominator += (cList.get(i).x - meanX) * (cList.get(i).y - meanY);
            numerator += (cList.get(i).x - meanX) * (cList.get(i).x - meanY);
        }

        this.midPoint = (double) ((this.maxY-this.minY))/2;
        this.slope = denominator / numerator;
        this.coeff = this.meanY - this.slope * this.meanX;
    }

    /**
     * forms the dataset to make a scatter plot
     */
    private void getSymetry(){

        for(int i =0; i< cList.size();++i) {
            int yCoord = (int) ((int) this.midPoint-(cList.get(i).y-this.midPoint));
            Coordinate c = new Coordinate(cList.get(i).x , yCoord);
            symetricList.add(c);

        }
    }
}
