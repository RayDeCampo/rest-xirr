package org.decampo.rest.xirr;

/**
 * Simple result object for the xirr REST service.
 */
public class XirrResult {

    private double xirr;

    public XirrResult(double xirr) {
        this.xirr = xirr;
    }

    public double getXirr() {
        return xirr;
    }

    public void setXirr(double xirr) {
        this.xirr = xirr;
    }

}
