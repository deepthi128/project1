//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.07.31 at 08:56:27 PM EDT 
//


package com.ipivot.matka.mock.bind.packages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="destinationArrivalTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="destinationDepartureTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sourceArrivalTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sourceDepartureTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "destinationArrivalTime",
    "destinationDepartureTime",
    "name",
    "sourceArrivalTime",
    "sourceDepartureTime",
    "type"
})
@XmlRootElement(name = "flight")
public class Flight {

    @XmlElement(required = true)
    protected String destinationArrivalTime;
    @XmlElement(required = true)
    protected String destinationDepartureTime;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String sourceArrivalTime;
    @XmlElement(required = true)
    protected String sourceDepartureTime;
    @XmlElement(required = true)
    protected String type;

    /**
     * Gets the value of the destinationArrivalTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationArrivalTime() {
        return destinationArrivalTime;
    }

    /**
     * Sets the value of the destinationArrivalTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationArrivalTime(String value) {
        this.destinationArrivalTime = value;
    }

    /**
     * Gets the value of the destinationDepartureTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationDepartureTime() {
        return destinationDepartureTime;
    }

    /**
     * Sets the value of the destinationDepartureTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationDepartureTime(String value) {
        this.destinationDepartureTime = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the sourceArrivalTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceArrivalTime() {
        return sourceArrivalTime;
    }

    /**
     * Sets the value of the sourceArrivalTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceArrivalTime(String value) {
        this.sourceArrivalTime = value;
    }

    /**
     * Gets the value of the sourceDepartureTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceDepartureTime() {
        return sourceDepartureTime;
    }

    /**
     * Sets the value of the sourceDepartureTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceDepartureTime(String value) {
        this.sourceDepartureTime = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

}
