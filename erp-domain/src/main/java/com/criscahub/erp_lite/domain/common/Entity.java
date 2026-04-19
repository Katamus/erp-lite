package com.criscahub.erp_lite.domain.common;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * Base class for all Entities and Aggregate Roots.
 * Equality is based on identity (ID).
 */
@Getter
public abstract class Entity<ID> {

    protected final ID id;

    protected Entity(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("Entity ID cannot be null");
        }
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity<?> entity = (Entity<?>) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
