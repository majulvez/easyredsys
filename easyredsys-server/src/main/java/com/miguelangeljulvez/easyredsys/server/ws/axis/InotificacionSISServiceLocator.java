package com.miguelangeljulvez.easyredsys.server.ws.axis;

public class InotificacionSISServiceLocator extends org.apache.axis.client.Service implements InotificacionSISService {

    public InotificacionSISServiceLocator() {
    }

    public InotificacionSISServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public InotificacionSISServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    private String InotificacionSIS_address = "http://localhost/WebServiceSIS/InotificacionSIS.asmx";

    public String getInotificacionSISAddress() {
        return InotificacionSIS_address;
    }

    private String InotificacionSISWSDDServiceName = "InotificacionSIS";

    public String getInotificacionSISWSDDServiceName() {
        return InotificacionSISWSDDServiceName;
    }

    public void setInotificacionSISWSDDServiceName(String name) {
        InotificacionSISWSDDServiceName = name;
    }

    public InotificacionSISPortType getInotificacionSIS() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(InotificacionSIS_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getInotificacionSIS(endpoint);
    }

    public InotificacionSISPortType getInotificacionSIS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            InotificacionSISBindingStub _stub = new InotificacionSISBindingStub(portAddress, this);
            _stub.setPortName(getInotificacionSISWSDDServiceName());
            return _stub;
        } catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setInotificacionSISEndpointAddress(String address) {
        InotificacionSIS_address = address;
    }


    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (InotificacionSISPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                InotificacionSISBindingStub _stub = new InotificacionSISBindingStub(new java.net.URL(InotificacionSIS_address), this);
                _stub.setPortName(getInotificacionSISWSDDServiceName());
                return _stub;
            }
        } catch (Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }


    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("InotificacionSIS".equals(inputPortName)) {
            return getInotificacionSIS();
        } else {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("https://sis.SERMEPA.es/sis/InotificacionSIS.wsdl", "InotificacionSISService");
    }

    private java.util.HashSet<javax.xml.namespace.QName> ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet<>();
            ports.add(new javax.xml.namespace.QName("https://sis.SERMEPA.es/sis/InotificacionSIS.wsdl", "InotificacionSIS"));
        }
        return ports.iterator();
    }


    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {

        if ("InotificacionSIS".equals(portName)) {
            setInotificacionSISEndpointAddress(address);
        } else {
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
