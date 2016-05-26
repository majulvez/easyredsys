package com.miguelangeljulvez.easyredsys.server.ws.axis;

public interface InotificacionSISPortType extends java.rmi.Remote {
    public String procesaNotificacionSIS(String XML) throws java.rmi.RemoteException;
}
