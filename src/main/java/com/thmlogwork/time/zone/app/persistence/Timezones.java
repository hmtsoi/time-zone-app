package com.thmlogwork.time.zone.app.persistence;

import com.vividsolutions.jts.geom.Geometry;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "timezones" )
public class Timezones implements Serializable {

    @Id
    @GeneratedValue
    private Long gid;

    private Integer objectid;
    private Integer scalerank;
    private String featurecla;
    private String name;
    private Integer map_color6;
    private Integer map_color8;
    private String note;
    private Double zone;
    private String places;
    private String iso_8601;
    private String dst_places;
    private Integer tz_namesum;
    private String utc_format;
    private String tz_name1st;
    private String time_zone;
    private Geometry geom;

    public Timezones() {
    }

    public Long getGid() {
        return gid;
    }

    public void setGid( Long gid ) {
        this.gid = gid;
    }

    public Integer getObjectid() {
        return objectid;
    }

    public void setObjectid( Integer objectid ) {
        this.objectid = objectid;
    }

    public Integer getScalerank() {
        return scalerank;
    }

    public void setScalerank( Integer scalerank ) {
        this.scalerank = scalerank;
    }

    public String getFeaturecla() {
        return featurecla;
    }

    public void setFeaturecla( String featurecla ) {
        this.featurecla = featurecla;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public Integer getMap_color6() {
        return map_color6;
    }

    public void setMap_color6( Integer map_color6 ) {
        this.map_color6 = map_color6;
    }

    public Integer getMap_color8() {
        return map_color8;
    }

    public void setMap_color8( Integer map_color8 ) {
        this.map_color8 = map_color8;
    }

    public String getNote() {
        return note;
    }

    public void setNote( String note ) {
        this.note = note;
    }

    public Double getZone() {
        return zone;
    }

    public void setZone( Double zone ) {
        this.zone = zone;
    }

    public String getPlaces() {
        return places;
    }

    public void setPlaces( String places ) {
        this.places = places;
    }

    public String getIso_8601() {
        return iso_8601;
    }

    public void setIso_8601( String iso_8601 ) {
        this.iso_8601 = iso_8601;
    }

    public String getDst_places() {
        return dst_places;
    }

    public void setDst_places( String dst_places ) {
        this.dst_places = dst_places;
    }

    public Integer getTz_namesum() {
        return tz_namesum;
    }

    public void setTz_namesum( Integer tz_namesum ) {
        this.tz_namesum = tz_namesum;
    }

    public String getUtc_format() {
        return utc_format;
    }

    public void setUtc_format( String utc_format ) {
        this.utc_format = utc_format;
    }

    public String getTz_name1st() {
        return tz_name1st;
    }

    public void setTz_name1st( String tz_name1st ) {
        this.tz_name1st = tz_name1st;
    }

    public String getTime_zone() {
        return time_zone;
    }

    public void setTime_zone( String time_zone ) {
        this.time_zone = time_zone;
    }

    public Geometry getGeom() {
        return geom;
    }

    public void setGeom( Geometry geom ) {
        this.geom = geom;
    }
}
