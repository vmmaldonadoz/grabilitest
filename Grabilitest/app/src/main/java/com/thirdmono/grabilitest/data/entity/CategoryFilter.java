package com.thirdmono.grabilitest.data.entity;

/**
 * Representation of a category name and its identifier.
 *
 * @author <a href="mailto:vmmzn20@gmail.com">Victor Maldonado</a>
 * @since 1.0
 */
public class CategoryFilter {

    private String id;
    private String name;

    public CategoryFilter() {
        this.id = "All";
        this.name = "All";
    }

    public CategoryFilter(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
