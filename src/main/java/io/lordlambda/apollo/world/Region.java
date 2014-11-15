package io.lordlambda.apollo.world;

import org.spongepowered.api.entity.LivingEntity;

import java.util.List;
import java.util.UUID;

/**
 * Creator: LordLambda
 * Date: 11/12/14.
 * Project: Apollo
 * Usage: Base Region interface
 */
public interface Region {

    /**
     * Each region is assigned an ID. This returns a particulars region ID.
     * @return
     *  Get this regions ID.
     */
    UUID getRegionID();

    /**
     * Get a list of entities allowed in this region.
     * @return
     *  The list of entities.
     * </p>
     * If this list contains null it is supposed to be assumed nothing is allowed.
     */
    List<LivingEntity> entitiesAllowed();

    /**
     * Returns if a living entity is allowed.
     * @param entity
     *  The living entity to check.
     * @return
     *  If the entity is supported.
     */
    boolean livingEntityAllowed(LivingEntity entity);

    /**
     * Get a list of all the tag names. All Tags this region has.
     * @return
     *  The names of tags this region has.
     */
    List<String> getTagNames();

    /**
     * Adds a tag with it's data.
     * @param tagName
     *  The tag name to add.
     * @param tagData
     *  The tagData to add.
     * @throws IllegalArgumentException
     *  If this tag name already exists. Instead use {@link #setTagData(String, java.util.List) setTagData}.
     */
    void addTag(String tagName, List<String> tagData) throws IllegalArgumentException;

    /**
     * Removes a tag
     * @param tagName
     *  The tag name to remove.
     * @throws IllegalArgumentException
     *  Thrown if the tag does not exist in the list.
     */
    void remTag(String tagName) throws IllegalArgumentException;

    /**
     * Get tag data for a tag.
     * @param tagName
     *  The tag name to get data for.
     * @return
     *  The tag data.
     * @throws IllegalArgumentException
     *  If the tag does not exist in the registered tags.
     */
    List<String> getTagData(String tagName) throws IllegalArgumentException;

    /**
     * Sets a tag data.
     * @param tagName
     *  The tag name to modify it's data.
     * @param tagData
     *  The tagData to set.
     */
    void setTagData(String tagName, List<String> tagData);
}