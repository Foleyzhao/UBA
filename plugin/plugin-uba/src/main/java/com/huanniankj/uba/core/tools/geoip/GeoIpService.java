package com.huanniankj.uba.core.tools.geoip;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.AsnResponse;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.model.CountryResponse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;

/**
 * GeoIP服务
 *
 * @author happynewyear
 */
@Data
@Service
public class GeoIpService {

    private final DatabaseReader countryReader;

    private final DatabaseReader cityReader;

    private final DatabaseReader asnReader;


    @Autowired
    public GeoIpService(ResourceLoader resourceLoader) throws IOException {
        Resource countryResource = resourceLoader.getResource("classpath:geodb/GeoLite2-Country.mmdb");
        try (InputStream inputStream = countryResource.getInputStream()) {
            countryReader = new DatabaseReader.Builder(inputStream).build();
        }

        Resource cityResource = resourceLoader.getResource("classpath:geodb/GeoLite2-City.mmdb");
        try (InputStream inputStream = cityResource.getInputStream()) {
            cityReader = new DatabaseReader.Builder(inputStream).build();
        }

        Resource asnResource = resourceLoader.getResource("classpath:geodb/GeoLite2-ASN.mmdb");
        try (InputStream inputStream = asnResource.getInputStream()) {
            asnReader = new DatabaseReader.Builder(inputStream).build();
        }
    }

    /**
     * 查询国家信息
     *
     * @param ip IP
     * @return 国家信息
     * @throws GeoIp2Exception GeoIp2异常
     * @throws IOException     IO异常
     */
    public CountryResponse getCountryInfo(String ip) throws GeoIp2Exception, IOException {
        return cityReader.country(InetAddress.getByName(ip));
    }

    /**
     * 查询城市信息
     *
     * @param ip IP
     * @return 城市信息
     * @throws GeoIp2Exception GeoIp2异常
     * @throws IOException     IO异常
     */
    public CityResponse getCityInfo(String ip) throws GeoIp2Exception, IOException {
        return cityReader.city(InetAddress.getByName(ip));
    }

    /**
     * 查询 ASN 信息
     *
     * @param ip IP
     * @return ASN信息
     * @throws GeoIp2Exception GeoIp2异常
     * @throws IOException     IO异常
     */
    public AsnResponse getAsnInfo(String ip) throws GeoIp2Exception, IOException {
        return asnReader.asn(InetAddress.getByName(ip));
    }

}
