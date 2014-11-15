package io.lordlambda.apollo.world.types.twoD;

import io.lordlambda.apollo.world.types.Region2D;
import org.spongepowered.api.entity.LivingEntity;
import org.spongepowered.api.math.Vector2f;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

/**
 * Creator: LordLambda
 * Date: 11/14/14.
 * Project: Apollo
 * Usage: A square region
 */
public class SquareRegion implements Region2D {

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

    /**
     * Default constructor
     */
    public SquareRegion() {
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
    public Vector2f getXY() {return null;}

    @Override
    public boolean pointInRegion(Vector2f point) {
        return false;
    }

    @Override
    public boolean allPointsInRegion(List<Vector2f> points) {
        return false;
    }

    @Override
    public boolean livingEntityAllowed(LivingEntity entity) {
        return false;
    }

    @Override
    public void setTagData(String tagName, List<String> tagData) {

    }
}