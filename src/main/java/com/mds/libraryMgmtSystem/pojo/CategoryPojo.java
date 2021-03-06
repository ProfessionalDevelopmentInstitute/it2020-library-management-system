package com.mds.libraryMgmtSystem.pojo;

import javax.validation.constraints.NotEmpty;

public class CategoryPojo {
    private Long id;
    @NotEmpty
    private String type;
    @NotEmpty
    private String description;

    public CategoryPojo(Long id, String type, String description) {
        this.id = id;
        this.type = type;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
