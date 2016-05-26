package com.miguelangeljulvez.easyredsys.server.ws.axis;

public interface InotificacionSISService extends javax.xml.rpc.Service {
    public String getInotificacionSISAddress();

    public InotificacionSISPortType getInotificacionSIS() throws javax.xml.rpc.ServiceException;

    public InotificacionSISPortType getInotificacionSIS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
