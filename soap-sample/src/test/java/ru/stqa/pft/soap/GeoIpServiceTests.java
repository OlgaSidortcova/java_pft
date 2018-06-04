package ru.stqa.pft.soap;

import com.buzzbuzhome.BBHLocation;
import com.buzzbuzhome.GeoIP;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

  @Test
  public void testMyIp() {

    BBHLocation geoIp = new GeoIP().getGeoIPSoap12().getUserLocation("92.100.226.166");
    assertEquals(geoIp.getCountryCode(), "RUS");

  }
  @Test
  public void testMyUS() {

    BBHLocation geoIp = new GeoIP().getGeoIPSoap12().getUserLocation("92.100.226.166");
    assertEquals(geoIp.getCountryCode(), "US");

  }
}