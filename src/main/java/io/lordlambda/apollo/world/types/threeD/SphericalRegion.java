package io.lordlambda.apollo.world.types.threeD;

import io.lordlambda.apollo.Apollo;
import io.lordlambda.apollo.world.types.Region3D;
import org.apache.log4j.Level;
import org.spongepowered.api.entity.LivingEntity;
import org.spongepowered.api.math.Vector3f;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

/**
 * Creator: LordLambda
 * Date: 11/12/14.
 * Project: Apollo
 * Usage: A Spherical Region
 */
public class SphericalRegion implements Region3D {

    /////////////////////////////////////////////////////////////
    //  Variables
    //
    //      Interface-Dependent Variables
    //          id - UUID of this region.
    //          tags - A LinkedHashMap of a tagName, and tagData
    //          entitiesAllowed - A List of entities allowed.
    //
    //      Class Variables
    //          XYZ - an array of the XStart, and YStart.
    //
    /////////////////////////////////////////////////////////////
    private UUID id;
    Vector3f[] XYZ;
    List<LivingEntity> entitiesAllowed;
    private LinkedHashMap<String, List<String>> tags;

    public SphericalRegion() {
        id = UUID.randomUUID();
        XYZ = new Vector3f[2];
        entitiesAllowed = new ArrayList<LivingEntity>();
        tags = new LinkedHashMap<String, List<String>>();
    }

    public SphericalRegion(String id) {
        this.id = UUID.fromString(id);
        XYZ =  new Vector3f[2];
        entitiesAllowed = new ArrayList<LivingEntity>();
        tags = new LinkedHashMap<String, List<String>>();
    }

    public SphericalRegion(Vector3f[] xyz) {
        id = UUID.randomUUID();
        if(xyz.length != 2) {
            Apollo.getApollo().log(Level.ERROR, "XYZ supplied for spherical region is incorrect size!");
            XYZ = new Vector3f[2];
        }else {
            XYZ = xyz;
        }
        entitiesAllowed = new ArrayList<LivingEntity>();
        tags = new LinkedHashMap<String, List<String>>();
    }

    public SphericalRegion(String id, Vector3f[] xyz) {
        this.id = UUID.fromString(id);
        if(xyz.length != 2) {
            Apollo.getApollo().log(Level.ERROR, "XYZ supplied for spherical region is incorrect size!");
            XYZ = new Vector3f[2];
        }else {
            XYZ = xyz;
        }
        entitiesAllowed = new ArrayList<LivingEntity>();
        tags = new LinkedHashMap<String, List<String>>();
    }

    public SphericalRegion(List<LivingEntity> entity) {
        this.id = UUID.randomUUID();
        XYZ = new Vector3f[2];
        entitiesAllowed = entity;
        tags = new LinkedHashMap<String, List<String>>();
    }

    @Override
    public UUID getRegionID() {
        return id;
    }

    @Override
    public List<LivingEntity> entitiesAllowed() {
        return null;
    }

    @Override
    public Vector3f getXYZ() {
        return null;
    }

    @Override
    public boolean pointInRegion(Vector3f point) {
        return false;
    }

    @Override
    public List<String> getTagNames() {
        return null;
    }

    @Override
    public boolean allPointsInRegion(List<Vector3f> points) {
        return false;
    }

    @Override
    public void addTag(String tagName, List<String> tagData) throws IllegalArgumentException {
        if(!(tags.keySet().contains(tagName))) {
            tags.put(tagName, tagData);
        }
        throw new IllegalArgumentException("Tag doesn't exist.");
    }

    @Override
    public void remTag(String tagName) throws IllegalArgumentException {
        if(tags.keySet().contains(tagName)) {
            tags.remove(tagName);
        }
        throw new IllegalArgumentException("Tag isn't in values!");
    }

    @Override
    public List<String> getTagData(String tagName) throws IllegalArgumentException {
        return null;
    }

    @Override
    public boolean livingEntityAllowed(LivingEntity entity) {
        return false;
    }

    @Override
    public void setTagData(String tagName, List<String> tagData) {

    }
}