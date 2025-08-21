package com.huanniankj.uba.core.tools.geoip;

import cn.hutool.core.io.resource.ClassPathResource;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.AsnResponse;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.model.CountryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

/**
 * GeoIP服务
 *
 * @author happynewyear
 */
@Service
public class GeoIpService {

    private final DatabaseReader countryReader;

    private final DatabaseReader cityReader;

    private final DatabaseReader asnReader;

    @Autowired
    public GeoIpService() throws IOException {
        // 加载数据库文件
        File countryDatabase = new ClassPathResource("geodb/GeoLite2-Country.mmdb").getFile();
        File cityDatabase = new ClassPathResource("geodb/GeoLite2-City.mmdb").getFile();
        File asnDatabase = new ClassPathResource("geodb/GeoLite2-ASN.mmdb").getFile();
        this.countryReader = new DatabaseReader.Builder(countryDatabase).build();
        this.cityReader = new DatabaseReader.Builder(cityDatabase).build();
        this.asnReader = new DatabaseReader.Builder(asnDatabase).build();
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
