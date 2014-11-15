package io.lordlambda.apollo.world;

import java.util.ArrayList;
import java.util.List;

/**
 * Creator: LordLambda
 * Date: 11/13/14.
 * Project: Apollo
 * Usage: Region Management
 */
public class RegionManager {

    /////////////////////////////////////////////////////////////
    //  Variables
    //
    //      Class Variables
    //        rm - A static instance of region manager for getting.
    //        registeredRegions - A List of all registered regions.
    //
    /////////////////////////////////////////////////////////////
    static RegionManager rm;
    List<Region> registeredRegions;

    /**
     * Default constructor.
     */
    public RegionManager() {
        rm = this;
        registeredRegions = new ArrayList<Region>();
    }

    /**
     * Registers a region.
     * @param region
     *  The region to register.
     * @throws IllegalArgumentException
     *  If the region is already registered.
     * </p>
     * Not really any registering is going on here.
     * It just gets added to a list which other functions are supposed to check through things like getSpawnRegions(),
     * and such.
     */
    public void registerRegion(Region region) throws IllegalArgumentException {
        if(registeredRegions.contains(region)) {
            throw new IllegalArgumentException(String.format("Region: %s Already Exists!", region.getRegionID().toString()));
        }
        registeredRegions.add(region);
    }

    /**
     * Unregisters a region.
     * @param region
     *  The region to unregister.
     * @throws IllegalArgumentException
     *  If the region is not registered this exception will be thrown.
     * </p>
     * Again not really unregistering the region. Just removing it from a list which is the recommended way of getting
     * a region.
     */
    public void unRegisterRegion(Region region) throws IllegalArgumentException {
        if(!(registeredRegions.contains(region))) {
            throw new IllegalArgumentException(String.format("Region: %s Does not Exist!", region.getRegionID().toString()));
        }
        registeredRegions.remove(region);
    }

    /**
     * Gets a list of all regions that have the tag spawn region.
     * @return
     *  A list of spawn regions. Note this list can be empty.
     * </p>
     * Iterates through the loop of registered regions checking for the tag "SPAWNREGION"
     * If the tag exists it adds to the return list.
     */
    public List<Region> getSpawnRegions() {
        List<Region> spawnRegions = new ArrayList<Region>();
        for(Region r : registeredRegions) {
            if(r.getTagNames().contains("SPAWNREGION")) {
                spawnRegions.add(r);
            }
        }
        return spawnRegions;
    }

    /**
     * @return
     *  All registered regions.
     */
    public List<Region> getRegions() {return registeredRegions;}

    /**
     * Queries if a region is registered.
     * @param r
     *  The region to check.
     * @return
     *  If the region is registered.
     */
    public boolean regionRegistered(Region r) {
        return registeredRegions.contains(r);
    }

    /**
     * Get a version of RegionManager
     * @return
     *  The current copy of region manager.
     */
    public static RegionManager getSelf() {return rm;}
}