package io.lordlambda.apollo.world.types.threeD;

import io.lordlambda.apollo.world.types.Region3D;
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
 * Usage: Cubiod Region
 */
public class CuboidRegion implements Region3D {

    /////////////////////////////////////////////////////////////
    //  Variables
    //
    //      Interface-Dependent Variables
    //          id - UUID of this region.
    //          tags - A LinkedHashMap of a tagName, and tagData
    //
    /////////////////////////////////////////////////////////////
    private UUID id;
    private LinkedHashMap<String, List<String>> tags;

    public CuboidRegion() {
        id = UUID.randomUUID();
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
    public boolean allPointsInRegion(List<Vector3f> points) {
        return false;
    }

    @Override
    public List<String> getTagNames() {
        return new ArrayList<String>(tags.keySet());
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